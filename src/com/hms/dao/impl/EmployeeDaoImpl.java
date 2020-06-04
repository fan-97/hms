package com.hms.dao.impl;

import com.hms.entity.Employee;
import com.hms.util.JDBCUtil;

import java.util.List;
import java.util.Map;

public class EmployeeDaoImpl {


    public Employee queryById(Integer id) {
        return null;
    }

    public List<Employee> queryAll(Employee employee) {
        return null;
    }

    public int insert(Employee employee) {
        return 0;
    }

    public int update(Employee employee) {
        return 0;
    }

    public int deleteById(Integer id) {
        return 0;
    }

    public Employee selectEmployee(String username, String password) {
        String sql = "select * from employee where username = ? and password = ?";
        Map<String, Object> map = JDBCUtil.executeQuery(sql, username, password);
        if (map != null && map.size() >= 1) {
            return new Employee(username,"", (String) map.get("phone"));
        }
        return null;
    }
}
