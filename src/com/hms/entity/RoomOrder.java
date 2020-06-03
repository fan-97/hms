package com.hms.entity;

import java.time.LocalDateTime;
import java.util.Date;

public class RoomOrder {
    private int id;
    private String customerName;
    private String roomType;
    private String roomNumber;
    private LocalDateTime orderTime;
    private LocalDateTime liveInTime;
    private LocalDateTime leaveTime;

    public RoomOrder(String customerName, String roomType, String roomNumber, LocalDateTime orderTime, LocalDateTime liveInTime, LocalDateTime leaveTime) {
        this.customerName = customerName;
        this.roomType = roomType;
        this.roomNumber = roomNumber;
        this.orderTime = orderTime;
        this.liveInTime = liveInTime;
        this.leaveTime = leaveTime;
    }

    public RoomOrder() {
    }

    @Override
    public String toString() {
        return "RoomOrder{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", roomType='" + roomType + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", orderTime=" + orderTime +
                ", liveInTime=" + liveInTime +
                ", leaveTime=" + leaveTime +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public LocalDateTime getLiveInTime() {
        return liveInTime;
    }

    public void setLiveInTime(LocalDateTime liveInTime) {
        this.liveInTime = liveInTime;
    }

    public LocalDateTime getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(LocalDateTime leaveTime) {
        this.leaveTime = leaveTime;
    }
}
