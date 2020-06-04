package com.hms.service;

import com.hms.dao.impl.RoomOrderDaoImpl;
import com.hms.entity.RoomOrder;

import java.util.List;

public class RoomOrderService {

    private RoomOrderDaoImpl roomOrderDao = new RoomOrderDaoImpl();

    public RoomOrder queryById(Integer id) {
        return this.roomOrderDao.queryById(id);
    }

    public RoomOrder insert(RoomOrder roomOrder) {
        this.roomOrderDao.insert(roomOrder);
        return roomOrder;
    }

    public RoomOrder update(RoomOrder roomOrder) {
        this.roomOrderDao.update(roomOrder);
        return this.queryById(roomOrder.getId());
    }

    public boolean deleteById(Integer id) {
        return this.roomOrderDao.deleteById(id) > 0;
    }

    public List<RoomOrder> queryAll(RoomOrder roomOrder) {
        return roomOrderDao.queryAll(roomOrder);
    }

    public boolean deleteByNumberAndCustomerName(int roomNumber, String customerName) {
        return roomOrderDao.deleteByCustomer(roomNumber, customerName) >= 1;
    }

}
