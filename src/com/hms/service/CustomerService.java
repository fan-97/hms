package com.hms.service;

import com.hms.dao.impl.CustomerDaoImpl;
import com.hms.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public class CustomerService {

    private CustomerDaoImpl customerDao = new CustomerDaoImpl();


    public Customer queryById(Integer id) throws SQLException {
        return this.customerDao.queryById(id);
    }



    public Customer insert(Customer customer) {
        this.customerDao.insert(customer);
        return customer;
    }


    public Customer update(Customer customer) throws SQLException {
        this.customerDao.update(customer);
        return this.queryById(customer.getId());
    }


    public boolean deleteById(Integer id) {
        return this.customerDao.deleteById(id) > 0;
    }

    public List<Customer> queryAll(Customer customer) {

        return customerDao.queryAll(customer);
    }

    public Customer login(String username,String password){
        List<Customer> customers = queryAll(new Customer(username, password, null, null, null, null));
        if (customers != null && customers.size() >= 1) {
            return customers.get(0);
        } else {
            System.err.println("username or password is error!!!!");
        }
        return null;
    }

}
