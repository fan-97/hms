package com.hms.dao.impl;

import com.hms.entity.Customer;
import com.hms.util.JDBCUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CustomerDaoImpl {

    private Connection conn = JDBCUtil.getConn();

    public Customer queryById(Integer id) throws SQLException {
        String sql = "select * from customer where id = ?";
        Map<String, Object> map = JDBCUtil.executeQuery(sql, id);
        if (map != null && map.size() >= 1) {
            return new Customer((String) map.get("username")
                    , (String) map.get("password")
                    , (String) map.get("passport")
                    , (String) map.get("name")
                    , (String) map.get("phone")
                    , (String) map.get("email"));
        }
        return null;
    }

    public List<Customer> queryAll(Customer customer) {
        String sql = "select * from customer where 1=1";
        List<Object> params = new ArrayList<>();
        if (customer != null) {
            if (customer.getUsername() != null) {
                sql += " and username = ?";
                params.add(customer.getUsername());
            }
            if (customer.getPassword() != null) {
                sql += " and password = ?";
                params.add(customer.getPassword());
            }
            if (customer.getName() != null) {
                sql += " and name = ?";
                params.add(customer.getName());
            }
            if (customer.getPhone() != null) {
                sql += " and phone = ?";
                params.add(customer.getPhone());
            }
            if (customer.getEmail() != null) {
                sql += " and email = ?";
                params.add(customer.getEmail());
            }
            if (customer.getPassport() != null) {
                sql += " and passport = ?";
                params.add(customer.getPassport());
            }
        }
        List<Map<String, Object>> list = JDBCUtil.executeQueryToList(sql, params.toArray(new Object[0]));
        List<Customer> result = new ArrayList<>();
        if (list != null && list.size() >= 1) {
            for (Map<String, Object> map : list) {
                if (map != null && map.size() >= 1) {
                    result.add(new Customer((String) map.get("username")
                            , (String) map.get("password")
                            , (String) map.get("passport")
                            , (String) map.get("name")
                            , (String) map.get("phone")
                            , (String) map.get("email")));
                }
            }
        }
        return result;
    }

    public int insert(Customer customer) {
        String sql = "insert into customer(username,password,passport,name,phone,email) values(?,?,?,?,?,?)";
        return JDBCUtil.update(sql, customer.getUsername(), customer.getPassword(), customer.getPassport(), customer.getName(), customer.getPhone(), customer.getEmail());
    }

    public int update(Customer customer) {
        return 0;
    }

    public int deleteById(Integer id) {
        String sql = "delete from customer where id = ?";
        return JDBCUtil.update(sql,id);
    }
}
