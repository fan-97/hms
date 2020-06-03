package com.hms.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCUtil {

    /** Driver name */
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    /** database connect url */
    private static final String url = "jdbc:mysql://127.0.0.1:3306/hms";
    /** username */
    private static final String userName = "root";
    /** password */
    private static final String password = "326543";

    /** connection */
    private static Connection conn;
    /** exe sql  */
    private static PreparedStatement stmt;
    /** result set */
    private static ResultSet rs;

    /** init database connection */
    static {
        try {
            Class.forName(DRIVER_NAME);
            conn = DriverManager.getConnection(url, userName, password);
        } catch (ClassNotFoundException e) {
            System.err.println("load driver failed");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("database connection failed");
            e.printStackTrace();
        }
    }

    public static Connection getConn() {
        return conn;
    }

    /** release resource */
    public static void close() {
        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
            if (stmt != null) {
                stmt.close();
                stmt = null;
            }

            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            System.err.println("release resource failed");
        }
    }


    public static List<String> getAllTableName(String dbNm) {
        List<String> result = new ArrayList<String>();
        Statement st = null;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES  WHERE TABLE_SCHEMA='" + dbNm + "'");
            while (rs.next()) {
                result.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            close();
        }
        return result;
    }

    public static int update(String sql,Object... args) {
        try {
            stmt = conn.prepareStatement(sql);
            if (null != args && args.length != 0) {
                for (int i = 0; i < args.length; i++) {
                    stmt.setObject(i + 1, args[i]);
                }
            }

            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private static ResultSet executeSql(String sql, Object... args) {
        try {
            stmt = conn.prepareStatement(sql);
            if (null != args && args.length != 0) {
                for (int i = 0; i < args.length; i++) {
                    stmt.setObject(i + 1, args[i]);
                }
            }

             rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }


    public static Map<String, Object> executeQuery(/*Class<T> klass, */String sql, Object... args) {
        try {
            rs = executeSql(sql, args);
            ResultSetMetaData metaData = rs.getMetaData();

            Map<String, Object> resultMap = new HashMap<>();
            if (rs.next()) {
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    String columnname = metaData.getColumnLabel(i);
                    Object obj = rs.getObject(i);
                    resultMap.put(columnname, obj);
                }
            }
            return resultMap;
//            return JSON.parseObject(JSON.toJSONString(resultMap), klass);
        } catch (Exception e) {
            System.err.println("数据查询异常");
            e.printStackTrace();
        }
//        return JSON.toJavaObject(new JSONObject(), klass);
        return null;
    }


    public static List<Map<String, Object>> executeQueryToList(/*Class<T> klass, */String sql, Object... args) {
        try {
            rs = executeSql(sql, args);
            List<Map<String, Object>> resultList = new ArrayList<>();
            Map<String, Object> resultMap = new HashMap<>();
            while (rs.next()) {
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    resultMap.put(metaData.getColumnName(i), rs.getString(i));
                }
                resultList.add(resultMap);
            }
            return resultList;
//            return JSON.parseArray(JSON.toJSONString(resultList), klass);
        } catch (Exception e) {
            System.err.println("数据查询异常");
            e.printStackTrace();
        }
//        return JSON.parseArray("[]", klass);
        return new ArrayList<>();
    }



}
