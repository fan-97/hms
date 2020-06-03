package com.hms.gui;

import com.hms.entity.Customer;
import com.hms.service.CustomerService;

import java.util.List;
import java.util.Scanner;

public class CustomerMenu {
    private CustomerService customerService = new CustomerService();
    private Customer customer;
    Scanner in = new Scanner(System.in);

    public void init() {

        do {
            System.out.println("****        Customer System               *****");
            System.out.println("****        1.sign in                     *****");
            System.out.println("****        2.sign up                     *****");
            System.out.println("****        3.exit                        *****");
            System.out.println("****       Please enter your choice       *****\n\n\n");
            String op = in.nextLine();
            if ("3".equals(op)) {
                System.out.println("Thank you for using!");
                break;
            } else if ("1".equals(op)) {
                int failureCount = 0;
                while (!login()) {
                    failureCount++;
                    if (failureCount >= 5) {
                        System.out.println("You tried to log in too many times, the system will log out soon!!!!!");
                        try {
                            Thread.sleep(1200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Thank you for using!");
                        return ;
                    }
                }

            } else if ("2".equals(op)) {
                register();
            }

        } while (true);
    }

    private void register() {
    }

    private boolean login() {
        System.out.print("input your username : ");
        String username = in.nextLine();
        System.out.print("input your password : ");
        String password = in.nextLine();
        List<Customer> customers = customerService.queryAll(new Customer(username, password, null, null, null, null));
        if (customers != null && customers.size() >= 1) {
            customer = customers.get(0);
            return true;
        } else {
            System.err.println("username or password is error!!!!");
        }
        return false;
    }
}
