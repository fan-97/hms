package com.hms.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author fanjie
 * @date 2020/6/3 18:57
 */
public class MainMenuUI extends JFrame {
    private JButton customerButton;
    private JButton employeeButton;

    public MainMenuUI() {
        createUIComponents();

        this.setLayout(new GridLayout(4, 3, 40, 40));
        this.setSize(400, 400);
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(customerButton);
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(employeeButton);
        this.add(new JLabel());
        customerButton.addActionListener(e -> {
            CustomerMenuUI customerMenu = new CustomerMenuUI();

        });

        employeeButton.addActionListener(e -> {
            EmployeeMenuUI employeeMenuUI = new EmployeeMenuUI();

        });

        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createUIComponents() {
        customerButton = new JButton("Customer");
        employeeButton = new JButton("employee");
    }
}
