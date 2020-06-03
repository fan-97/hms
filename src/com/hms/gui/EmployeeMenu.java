package com.hms.gui;

import com.hms.service.EmployeeService;

import java.util.Scanner;

public class EmployeeMenu {
    private EmployeeService employeeService = new EmployeeService();
    Scanner in = new Scanner(System.in);
    public void init(){
        System.out.println("****        Employee System               *****");
        System.out.println("****                                      *****");
        System.out.println("****    Please log in to use the system   *****\n\n\n");
        System.out.print("input your username : ");
        String username = in.nextLine();
        System.out.print("input your password : ");
        String password = in.nextLine();
    }
}
