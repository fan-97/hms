package com.hms.service;

import com.hms.dao.impl.EmployeeDaoImpl;
import com.hms.entity.Employee;

import java.util.List;

public class EmployeeService {

    private EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();


    public Employee queryById(Integer id) {
        return this.employeeDao.queryById(id);
    }


    public Employee insert(Employee employee) {
        this.employeeDao.insert(employee);
        return employee;
    }


    public Employee update(Employee employee) {
        this.employeeDao.update(employee);
        return this.queryById(employee.getId());
    }


    public boolean deleteById(Integer id) {
        return this.employeeDao.deleteById(id) > 0;
    }

    public List<Employee> queryAll(Employee employee) {
        return employeeDao.queryAll(employee);
    }
}