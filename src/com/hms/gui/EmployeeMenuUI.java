package com.hms.gui;

import com.hms.entity.Employee;
import com.hms.entity.Room;
import com.hms.entity.RoomOrder;
import com.hms.service.CustomerService;
import com.hms.service.EmployeeService;
import com.hms.service.RoomOrderService;
import com.hms.util.DataUtil;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author fanjie
 * @date 2020/6/3 19:01
 */
public class EmployeeMenuUI extends JFrame {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private JButton signIn = new JButton("login");
    private JButton reset = new JButton("reset");
    private JTextField usernameTF = new JTextField(16);
    private JPasswordField passwordTF = new JPasswordField(16);
    private JPanel panel = new JPanel();
    private EmployeeService employeeService = new EmployeeService();
    private RoomOrderService roomOrderService = new RoomOrderService();

    public EmployeeMenuUI() {
        panel.setLayout(new GridLayout(4, 2, 10, 30));
        panel.add(new JLabel("username:"));
        panel.add(usernameTF);
        panel.add(new JLabel("password:"));
        panel.add(passwordTF);
        panel.add(signIn);
        panel.add(reset);

        signIn.addActionListener(e->{
            String username = usernameTF.getText();
            String password = new String(passwordTF.getPassword());
            if(username==null || "".equals(username.trim())||password == null || "".equals(password)) {
                JOptionPane.showMessageDialog(panel,"username or password can not be empty!!","error",JOptionPane.ERROR_MESSAGE);
                return ;
            }

            Employee login = employeeService.login(username, password);
            if(login==null) {
                JOptionPane.showMessageDialog(panel,"username or password is error!!","error",JOptionPane.ERROR_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(panel,"login successfully!","success",JOptionPane.INFORMATION_MESSAGE);
                showRooms();
            }

        });

        reset.addActionListener(e->{
            usernameTF.setText("");
            passwordTF.setText("");
        });


        this.setLayout(new FlowLayout());
        this.add(panel);
        this.setSize(400, 400);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void showRooms() {
        JDialog bookedRoom = new JDialog(new JFrame());

        JPanel p2 = new JPanel();
        JScrollPane scrollPane = new JScrollPane();
        RoomOrder roomOrder = new RoomOrder();
        List<RoomOrder> roomOrders = roomOrderService.queryAll(roomOrder);
        p2.setLayout(new GridLayout(roomOrders.size() + 1, 7));

        p2.add(new JLabel("room id"));
        p2.add(new JLabel("customer name"));
        p2.add(new JLabel("room nubmer"));
        p2.add(new JLabel("room type"));
        p2.add(new JLabel("order time"));
        p2.add(new JLabel("live in time"));
        p2.add(new JLabel("leave time"));


        for (RoomOrder order : roomOrders) {
            p2.add(new JLabel(order.getId() + ""));
            p2.add(new JLabel(order.getCustomerName()));
            p2.add(new JLabel(order.getRoomNumber()));
            p2.add(new JLabel(order.getRoomType()));
            p2.add(new JLabel(formatter.format(order.getOrderTime())+" "));
            p2.add(new JLabel(formatter.format(order.getLiveInTime())+" "));
            p2.add(new JLabel(formatter.format(order.getLeaveTime())+" "));
        }
        scrollPane.setViewportView(p2);
        scrollPane.getViewport().setMinimumSize(new Dimension(400, 300));
        scrollPane.getViewport().setPreferredSize(new Dimension(400, 300));
        bookedRoom.add(scrollPane);
        bookedRoom.setLocationRelativeTo(panel);
        bookedRoom.setSize(450, 350);
        bookedRoom.setVisible(true);
        bookedRoom.setDefaultCloseOperation(HIDE_ON_CLOSE);
    }
}
