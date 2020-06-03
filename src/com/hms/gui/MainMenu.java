package com.hms.gui;

import java.util.Scanner;

public class MainMenu {

    private CustomerMenu customerMenu = new CustomerMenu();
    private EmployeeMenu employeeMenu = new EmployeeMenu();
    Scanner in = new Scanner(System.in);

    public void run() {
        System.out.println("****        Hotel Manager System          *****");
        System.out.println("****          1、Customer                  *****");
        System.out.println("****          2、Employee                  *****");
        System.out.println("****          3、exit                      *****");
        System.out.println("****      Enter a number choose your role  *****");

        do {
            String op = in.nextLine();
            if ("3".equals(op)) {
                System.out.println("Thank you for using!");
                break;
            } else if ("1".equals(op)) {
                customerMenu.init();
            }else if("2".equals(op)) {
                employeeMenu.init();
            }

        } while (true);
    }

}
