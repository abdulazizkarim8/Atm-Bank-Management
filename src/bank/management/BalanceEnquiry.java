package bank.management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BalanceEnquiry extends JFrame implements ActionListener {

    String pinnumber;
    JButton back;

    BalanceEnquiry(String pinnumber) {

        this.pinnumber = pinnumber;

        setLayout(null);

        // ATM Background
        ImageIcon i1 = new ImageIcon(getClass().getResource("/icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        // Heading
        JLabel text = new JLabel("CHECK YOUR BALANCE");
        text.setBounds(170, 300, 400, 20);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        // Balance Label
        JLabel balanceLabel = new JLabel();
        balanceLabel.setBounds(170, 350, 400, 30);
        balanceLabel.setForeground(Color.WHITE);
        balanceLabel.setFont(new Font("System", Font.BOLD, 18));
        image.add(balanceLabel);

        // BACK BUTTON
        back = new JButton("Back");
        back.setBounds(170, 400, 100, 30);
        back.addActionListener(this);
        image.add(back);

        try {

            Conn conn = new Conn();

            ResultSet rs = conn.s.executeQuery(
                    "select * from bank where pin = '" + pinnumber + "'");

            int balance = 0;

            while (rs.next()) {

                String type = rs.getString("type");
                int amount = Integer.parseInt(rs.getString("amount"));

                if (type.equals("Deposit")) {
                    balance += amount;
                } else {
                    balance -= amount;
                }
            }

            balanceLabel.setText("Your Balance is Rs " + balance);

        } catch (Exception e) {
            System.out.println(e);
        }

        setSize(900, 900);
        setLocation(300, 0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        setVisible(false);
        new Transactions(pinnumber).setVisible(true);
    }

    public static void main(String[] args) {
        new BalanceEnquiry("");
    }
}