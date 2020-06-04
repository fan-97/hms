package com.hms.dao.impl;

import com.hms.entity.RoomOrder;
import com.hms.util.JDBCUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RoomOrderDaoImpl {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public RoomOrder queryById(Integer id) {
        String sql = "select * from room_order where id = ?";
        Map<String, Object> map = JDBCUtil.executeQuery(sql, id);
        if (map != null && map.size() >= 1) {
            return new RoomOrder((String) map.get("customer_name"), (String) map.get("room_type"), (String) map.get("room_number"), (LocalDateTime) map.get("order_time"), (LocalDateTime) map.get("live_in_time"), (LocalDateTime) map.get("leave_time"));
        }
        return null;
    }

    public List<RoomOrder> queryAll(RoomOrder roomOrder) {
        String sql = "select * from room_order where 1=1";
        List<RoomOrder> rs = new ArrayList<>();
        List params = new ArrayList();
        if (roomOrder != null) {
            if (roomOrder.getCustomerName() != null) {
                sql += " and customer_name = ?";
                params.add(roomOrder.getCustomerName());
            }
            if (roomOrder.getRoomType() != null) {
                sql += " and room_type = ?";
                params.add(roomOrder.getRoomType());
            }
            if (roomOrder.getRoomNumber() != null) {
                sql += " and room_number = ?";
                params.add(roomOrder.getRoomNumber());
            }
            if (roomOrder.getLiveInTime() != null&&roomOrder.getLeaveTime()!=null) {
                // return null means customer can order the room
                sql += " and not (leave_time < ? or live_in_time > ?) ";
                params.add(roomOrder.getLiveInTime());
                params.add(roomOrder.getLeaveTime());
            }
            List<Map<String, Object>> list = JDBCUtil.executeQueryToList(sql, params.toArray(new Object[0]));

            if (list != null && list.size() >= 1) {
                for (Map<String, Object> map : list) {
                    if (map != null && map.size() >= 1) {
                        rs.add(new RoomOrder((String) map.get("customer_name"), (String) map.get("room_type"), (String) map.get("room_number"), LocalDateTime.parse(((String) map.get("order_time")).substring(0,19),formatter), LocalDateTime.parse(((String) map.get("live_in_time")).substring(0,19),formatter), LocalDateTime.parse(((String) map.get("leave_time")).substring(0,19),formatter)));
                    }
                }
            }
        }
        return rs;
    }

    public int insert(RoomOrder roomOrder) {
        String sql = "insert into room_order(customer_name,room_type,room_number,order_time,live_in_time,leave_time) values(?,?,?,?,?,?)";
        return JDBCUtil.update(sql, roomOrder.getCustomerName(), roomOrder.getRoomType(), roomOrder.getRoomNumber(), roomOrder.getOrderTime(), roomOrder.getLiveInTime(), roomOrder.getLeaveTime());

    }

    public int update(RoomOrder roomOrder) {
        return 0;
    }

    public int deleteById(Integer id) {
        String sql = "delete from room_order where id = ?";
        return JDBCUtil.update(sql, id);
    }
}
