package com.hms.gui;

import com.hms.entity.Chef;
import com.hms.entity.Customer;
import com.hms.entity.Room;
import com.hms.entity.RoomOrder;
import com.hms.service.CustomerService;
import com.hms.service.RoomOrderService;
import com.hms.util.DataUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.hms.util.DataUtil.FLOOR;

/**
 * @author fanjie
 * @date 2020/6/3 19:01
 */
public class CustomerMenuUI extends JFrame {
    private JButton signIn;
    private JButton signUp;
    private JTextField usernameTF;
    private JPasswordField passwordTF;
    private JPanel panel;
    private CustomerService customerService = new CustomerService();
    private RoomOrderService roomOrderService = new RoomOrderService();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public CustomerMenuUI() {
        createUIComponents();
        panel.setLayout(new GridLayout(4, 2, 10, 30));

        panel.add(new Panel());
        panel.add(new Panel());
        panel.add(new JLabel("username:"));
        panel.add(usernameTF);
        panel.add(new JLabel("password:"));
        panel.add(passwordTF);
        panel.add(signIn);
        panel.add(signUp);
        this.setLayout(new FlowLayout());
        this.add(panel);

        signIn.addActionListener(e -> {
            String username = usernameTF.getText();
            String password = new String(passwordTF.getPassword());
            Customer customer = customerService.login(username, password);
            if (customer == null) {
                JOptionPane.showMessageDialog(panel, "username or password is error!!!", "error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(panel, "login success!", "success", JOptionPane.INFORMATION_MESSAGE);
                // pop the customer dialog
                JDialog registerDialog = new JDialog(new JFrame());
                registerDialog.setLayout(new FlowLayout());
                JPanel panel2 = new JPanel();
                panel2.setLayout(new GridLayout(3, 1));
                JButton cancelRoom = new JButton("cancel room");
                JButton bookRoom = new JButton("book room");
                JButton bookFood = new JButton("book food");

                panel2.add(cancelRoom);
                panel2.add(bookFood);
                panel2.add(bookRoom);

                // cancel room
                cancelRoom.addActionListener(e1 -> {

                    JDialog cancelRoomDialog = new JDialog();
                    cancelRoomDialog.setLayout(new FlowLayout());
                    cancelRoomDialog.setVisible(true);
                    JTextField roomId = new JTextField();
                    JButton cancelBtn = new JButton("cancel");
                    JButton queryBtb = new JButton("show my booked room");
                    JPanel p = new JPanel();
                    p.setLayout(new GridLayout(2, 2));

                    p.add(new JLabel("room number:"));
                    p.add(roomId);
                    p.add(cancelBtn);
                    p.add(queryBtb);

                    queryBtb.addActionListener(e2 -> {
                        JDialog bookedRoom = new JDialog(new JFrame());
                        JScrollPane scrollPane = new JScrollPane();
                        JPanel p2 = new JPanel();
                        RoomOrder roomOrder = new RoomOrder();
                        roomOrder.setCustomerName(customer.getName());
                        List<RoomOrder> roomOrders = roomOrderService.queryAll(roomOrder);
                        p2.setLayout(new GridLayout(roomOrders.size() + 1, 6));

                        p2.add(new JLabel("room id"));
                        p2.add(new JLabel("room nubmer"));
                        p2.add(new JLabel("room type"));
                        p2.add(new JLabel("order time"));
                        p2.add(new JLabel("live in time"));
                        p2.add(new JLabel("leave time"));


                        for (RoomOrder order : roomOrders) {
                            p2.add(new JLabel(order.getId() + ""));
                            p2.add(new JLabel(order.getRoomNumber()));
                            p2.add(new JLabel(order.getRoomType()));
                            p2.add(new JLabel(formatter.format(order.getOrderTime())+" "));
                            p2.add(new JLabel(formatter.format(order.getLiveInTime())+" "));
                            p2.add(new JLabel(formatter.format(order.getLeaveTime())+" "));
                        }
                        scrollPane.setViewportView(p2);
                        scrollPane.getViewport().setMinimumSize(new Dimension(300, 200));
                        scrollPane.getViewport().setPreferredSize(new Dimension(300, 200));
                        bookedRoom.add(scrollPane);
                        bookedRoom.setLocationRelativeTo(panel);
                        bookedRoom.setSize(350, 250);
                        bookedRoom.setVisible(true);
                        bookedRoom.setDefaultCloseOperation(HIDE_ON_CLOSE);
                    });
                    cancelBtn.addActionListener(e2 -> {
                        boolean b = roomOrderService.deleteByNumberAndCustomerName(Integer.parseInt(roomId.getText()),customer.getName());
                        if (b) {
                            JOptionPane.showMessageDialog(panel2, "cancel room success!!");
                        } else {
                            JOptionPane.showMessageDialog(panel2, "cancel room failed!!","error",JOptionPane.ERROR_MESSAGE);
                        }
                    });

                    cancelRoomDialog.setLayout(new FlowLayout());
                    cancelRoomDialog.add(p);
                    cancelRoomDialog.setSize(400, 200);
                    cancelRoomDialog.setLocationRelativeTo(null);

                });
                // book room
                bookRoom.addActionListener(e1 -> {

                    JDialog bookRoomDialog = new JDialog();
                    bookRoomDialog.setVisible(true);
                    JTextField roomId = new JTextField();
                    JTextField liveInTimeTF = new JTextField();
                    JTextField leaveTimeTF = new JTextField();
                    JButton bookBtn = new JButton("book");
                    JButton queryBtb = new JButton("show all room");
                    JPanel p = new JPanel();
                    p.setLayout(new GridLayout(4, 2));

                    p.add(new JLabel("room number:"));
                    p.add(roomId);
                    p.add(new JLabel("live in time:"));
                    p.add(liveInTimeTF);
                    p.add(new JLabel("leave time:"));
                    p.add(leaveTimeTF);
                    p.add(bookBtn);
                    p.add(queryBtb);

                    queryBtb.addActionListener(e2 -> {
                        JDialog bookedRoom = new JDialog(new JFrame());
                        bookedRoom.setLayout(new FlowLayout());
                        JPanel p2 = new JPanel();
                        JScrollPane scrollPane = new JScrollPane();
                        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                        scrollPane.validate();
                        p2.setLayout(new GridLayout(DataUtil.getRooms().length * FLOOR + 1, 2));

                        p2.add(new JLabel("room nubmer"));
                        p2.add(new JLabel("room type"));
                        RoomOrder roomOrder = new RoomOrder();
                        roomOrder.setCustomerName(customer.getName());
                        Room[] rooms = DataUtil.getRooms();
                        for (int i = 1; i < FLOOR; i++) {
                            for (Room room : rooms) {
                                RoomOrder roomOrder1 = new RoomOrder();
                                roomOrder1.setRoomNumber(String.format(room.getNumber(), i));
                                List<RoomOrder> roomOrders = roomOrderService.queryAll(roomOrder1);
                                if (roomOrders != null && roomOrders.size() >= 1) {
                                    continue;
                                }
                                p2.add(new JLabel(String.format(room.getNumber(), i)));
                                p2.add(new JLabel(room.getType()));
                            }
                        }

                        scrollPane.setViewportView(p2);
                        scrollPane.getViewport().setMinimumSize(new Dimension(300, 200));
                        scrollPane.getViewport().setPreferredSize(new Dimension(300, 200));
                        bookedRoom.add(scrollPane);
                        bookedRoom.setLocationRelativeTo(panel);
                        bookedRoom.setSize(400, 200);
                        bookedRoom.setVisible(true);
                        bookedRoom.setDefaultCloseOperation(HIDE_ON_CLOSE);
                    });
                    bookBtn.addActionListener(e2 -> {
                        LocalDateTime liveIntime = LocalDateTime.now();
                        LocalDateTime leaveTime = LocalDateTime.now();
                        try {
                            String time = " 00:00:00";
                            liveIntime = LocalDateTime.parse(liveInTimeTF.getText()+time,formatter);
                            leaveTime = LocalDateTime.parse(leaveTimeTF.getText()+time,formatter);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(panel2, "date format is error ,please input yyyy-MM-dd!!", "error", JOptionPane.ERROR_MESSAGE);
                            System.out.println(ex);
                            return;
                        }


                        Room room = DataUtil.getRoom(roomId.getText());
                        if (room == null ) {
                            JOptionPane.showMessageDialog(panel2, "The room has been booked !!", "error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        List<RoomOrder> roomOrders = roomOrderService.queryAll(new RoomOrder(null, null,  roomId.getText(), null, liveIntime, leaveTime));
                        if (roomOrders != null && roomOrders.size() >= 1) {
                            JOptionPane.showMessageDialog(panel2, "The room has been booked  !!", "error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        RoomOrder insert = roomOrderService.insert(new RoomOrder(customer.getName(), room.getType(), roomId.getText(), LocalDateTime.now(), liveIntime, leaveTime));
                        if (insert != null) {
                            JOptionPane.showMessageDialog(panel2, "book room success!!");
                        } else {
                            JOptionPane.showMessageDialog(panel2, "book room failed!!");
                        }
                    });

                    bookRoomDialog.setLayout(new FlowLayout());
                    bookRoomDialog.add(p);
                    bookRoomDialog.setSize(400, 200);
                    bookRoomDialog.setLocationRelativeTo(null);

                });
                // book food
                bookFood.addActionListener(e1 -> {
                    JDialog bookRoomDialog = new JDialog();
                    bookRoomDialog.setVisible(true);
                    JTextField c1 = new JTextField();
                    JTextField c2 = new JTextField();
                    JTextField c3 = new JTextField();
                    JTextField c4 = new JTextField();
                    JTextField c5 = new JTextField();
                    JButton bookBtn = new JButton("book");
                    JButton queryBtb = new JButton("show all food");
                    JPanel p = new JPanel();
                    p.setLayout(new GridLayout(6, 2));

                    p.add(new JLabel("Karen Adam:"));
                    p.add(c1);
                    p.add(new JLabel("Hari Philip:"));
                    p.add(c2);
                    p.add(new JLabel("Thalia Hensley:"));
                    p.add(c3);
                    p.add(new JLabel("Nisha Moss:"));
                    p.add(c4);
                    p.add(new JLabel("Witch time:"));
                    p.add(c5);
                    p.add(bookBtn);
                    p.add(queryBtb);
                    queryBtb.addActionListener(e2 -> {
                        String witchTime = c5.getText();
                        LocalDate bookTime;
                        try {
                            bookTime = LocalDate.parse(witchTime);
                        }catch (Exception ex) {
                            JOptionPane.showMessageDialog(panel2,"date formatter must 'yyyy-MM-dd'");
                            return ;
                        }
                        Chef[] chefs = DataUtil.getChefs(bookTime.getDayOfWeek().getValue() + 2);
                        JDialog bookedRoom = new JDialog(new JFrame());
                        bookedRoom.setLayout(new FlowLayout());
                        JPanel p2 = new JPanel();
                        JScrollPane scrollPane = new JScrollPane();
                        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                        scrollPane.validate();

                        int rows = 0;
                        for (Chef chef : chefs) {
                            for (String menu : chef.getMenu()) {
                                rows++;
                            }
                        }

                        p2.setLayout(new GridLayout(rows + 1, 2, 20, 20));
                        p2.add(new JLabel("chef name"));
                        p2.add(new JLabel("food name"));
                        RoomOrder roomOrder = new RoomOrder();
                        roomOrder.setCustomerName(customer.getName());

                        for (Chef chef : chefs) {
                            for (String menu : chef.getMenu()) {
                                p2.add(new JLabel(chef.getName()));
                                p2.add(new JLabel(menu));
                            }
                        }

                        scrollPane.setViewportView(p2);
                        scrollPane.getViewport().setMinimumSize(new Dimension(300, 200));
                        scrollPane.getViewport().setPreferredSize(new Dimension(300, 200));
                        bookedRoom.add(scrollPane);
                        bookedRoom.setLocationRelativeTo(panel);
                        bookedRoom.setSize(400, 350);
                        bookedRoom.setVisible(true);
                        bookedRoom.setDefaultCloseOperation(HIDE_ON_CLOSE);
                    });
                    bookBtn.addActionListener(e2 -> {
                        String witchTime = c5.getText();
                        LocalDate bookTime;
                        try {
                            bookTime = LocalDate.parse(witchTime);
                        }catch (Exception ex) {
                            JOptionPane.showMessageDialog(panel2,"date formatter must 'yyyy-MM-dd'");
                            return ;
                        }
                        Chef[] chefs = DataUtil.getChefs(bookTime.getDayOfWeek().getValue() + 2);

                        if (c1.getText() != null && "".equals(c1.getText().trim())) {
                            boolean flag = false;
                            for (Chef chef : chefs) {
                                if (chef.getName().equals("Karen Adam")) {
                                    flag = true;
                                    break;
                                }
                                if (flag) {
                                    JOptionPane.showMessageDialog(panel2, "Karen Adam today is not worked!!don't book his food!");
                                    return;
                                }
                            }
                        }

                        if (c2.getText() != null && "".equals(c2.getText().trim())) {
                            boolean flag = false;
                            for (Chef chef : chefs) {
                                if (chef.getName().equals("Hari Philip")) {
                                    flag = true;
                                    break;
                                }
                                if (flag) {
                                    JOptionPane.showMessageDialog(panel2, "Hari Philip today is not worked!!don't book his food!");
                                    return;
                                }
                            }
                        }

                        if (c3.getText() != null && "".equals(c3.getText().trim())) {
                            boolean flag = false;
                            for (Chef chef : chefs) {
                                if (chef.getName().equals("Thalia Hensley")) {
                                    flag = true;
                                    break;
                                }
                                if (flag) {
                                    JOptionPane.showMessageDialog(panel2, "Thalia Hensley today is not worked!!don't book his food!");
                                    return;
                                }
                            }
                        }

                        if (c4.getText() != null && "".equals(c4.getText().trim())) {
                            boolean flag = false;
                            for (Chef chef : chefs) {
                                if (chef.getName().equals("Nisha Moss")) {
                                    flag = true;
                                    break;
                                }
                                if (flag) {
                                    JOptionPane.showMessageDialog(panel2, "Nisha Moss today is not worked!!don't book his food!");
                                    return;
                                }
                            }
                        }
                        JOptionPane.showMessageDialog(panel2, "book success!!");
                    });

                    bookRoomDialog.setLayout(new FlowLayout());
                    bookRoomDialog.add(p);
                    bookRoomDialog.setSize(400, 200);
                    bookRoomDialog.setLocationRelativeTo(null);
                });

                registerDialog.add(panel2);
                registerDialog.setLocationRelativeTo(panel2);
                registerDialog.setSize(200, 200);
                registerDialog.setVisible(true);
                registerDialog.setDefaultCloseOperation(HIDE_ON_CLOSE);
            }
        });

        signUp.addActionListener(e -> {
            // pop register frame
            JDialog registerDialog = new JDialog(new JFrame());
            registerDialog.setLayout(new FlowLayout());
            JPanel panel2 = new JPanel();
            JButton signUP2 = new JButton("sign up");
            JButton reset = new JButton("reset");
            JTextField tf[] = new JTextField[5];
            for (int i = 0; i < tf.length; i++) {
                tf[i] = new JTextField();
            }
            JPasswordField passwordField = new JPasswordField();
            JPasswordField confirm = new JPasswordField();
            panel2.setLayout(new GridLayout(8, 2, 10, 30));
            panel2.add(new JLabel("username:"));
            panel2.add(tf[0]);
            panel2.add(new JLabel("password:"));
            panel2.add(passwordField);
            panel2.add(new JLabel("confirm password:"));
            panel2.add(confirm);
            panel2.add(new JLabel("name:"));
            panel2.add(tf[1]);
            panel2.add(new JLabel("passport:"));
            panel2.add(tf[2]);
            panel2.add(new JLabel("phone:"));
            panel2.add(tf[3]);
            panel2.add(new JLabel("email:"));
            panel2.add(tf[4]);

            panel2.add(signUP2);
            panel2.add(reset);

            reset.addActionListener(e1 -> {
                for (int i = 0; i < tf.length; i++) {
                    tf[i].setText("");
                }
            });

            signUP2.addActionListener(e1 -> {
                for (int i = 0; i < tf.length; i++) {
                    if (tf[i].getText() == null || tf[i].getText().trim().equals("")) {
                        JOptionPane.showMessageDialog(panel2, "info can not be empty!!", "error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                String username = tf[0].getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirm.getPassword());
                String name = tf[1].getText();
                String passport = tf[2].getText();
                String phone = tf[3].getText();
                String email = tf[4].getText();
                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(panel2, "The password entered twice is different!!", "error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Customer insert = customerService.insert(new Customer(username, password, passport, name, phone, email));
                if (insert != null) {
                    JOptionPane.showMessageDialog(panel2, "register success!", "success", JOptionPane.INFORMATION_MESSAGE);
                    registerDialog.setVisible(false);
                }

            });
            registerDialog.add(panel2);
            registerDialog.setLocationRelativeTo(panel2);
            registerDialog.setSize(400, 500);
            registerDialog.setVisible(true);
            registerDialog.setDefaultCloseOperation(HIDE_ON_CLOSE);
        });

        this.setSize(400, 400);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createUIComponents() {
        signIn = new JButton("sign in");
        signUp = new JButton("sign up");
        usernameTF = new JTextField(10);
        passwordTF = new JPasswordField(16);
        panel = new JPanel();
    }

    class RoomActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(e.getActionCommand());
            System.out.println(e.getModifiers());
            System.out.println(e.getSource());
        }
    }

}
