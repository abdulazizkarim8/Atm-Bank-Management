package bank.management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class MiniStatement extends JFrame implements ActionListener {

    JTextArea mini;
    JButton back;

    MiniStatement(String pinnumber) {

        setTitle("Mini Statement");
        setLayout(null);

        // Bank Name
        JLabel bank = new JLabel("ABC BANK");
        bank.setBounds(130, 20, 200, 30);
        bank.setFont(new Font("Raleway", Font.BOLD, 28));
        bank.setForeground(new Color(0, 102, 204));
        add(bank);

        // Card Number
        JLabel card = new JLabel();
        card.setBounds(20, 70, 350, 20);
        card.setFont(new Font("System", Font.BOLD, 14));
        add(card);

        // Heading
        JLabel heading = new JLabel("Mini Statement");
        heading.setBounds(120, 100, 200, 30);
        heading.setFont(new Font("System", Font.BOLD, 22));
        add(heading);

        // Text Area
        mini = new JTextArea();
        mini.setFont(new Font("Monospaced", Font.PLAIN, 14));
        mini.setEditable(false);

        JScrollPane scroll = new JScrollPane(mini);
        scroll.setBounds(20, 150, 340, 280);
        add(scroll);

        // Balance Label
        JLabel balance = new JLabel();
        balance.setBounds(20, 450, 350, 30);
        balance.setFont(new Font("System", Font.BOLD, 16));
        balance.setForeground(new Color(0, 153, 0));
        add(balance);

        // Back Button
        back = new JButton("Back");
        back.setBounds(130, 500, 120, 35);
        back.setFont(new Font("System", Font.BOLD, 16));
        back.addActionListener(this);
        add(back);

        try {

            Conn conn = new Conn();

            // Fetch Card Number
            ResultSet rs1 = conn.s.executeQuery(
                    "select * from login where pin = '" + pinnumber + "'");

            while (rs1.next()) {

                String cardno = rs1.getString("cardnumber");

                card.setText(
                        "Card Number : "
                        + cardno.substring(0, 4)
                        + " XXXX XXXX "
                        + cardno.substring(12));
            }

            // Fetch Transactions
            ResultSet rs2 = conn.s.executeQuery(
                    "select * from bank where pin = '"
                    + pinnumber + "'");

            int bal = 0;

            mini.append("Date\t\tType\tAmount\n");
            mini.append("--------------------------------------------------\n");

            while (rs2.next()) {

                String date = rs2.getString("date");
                String type = rs2.getString("type");
                String amount = rs2.getString("amount");

                mini.append(
                        date + "\t"
                        + type + "\tRs "
                        + amount + "\n");

                if (type.equals("Deposit")) {

                    bal += Integer.parseInt(amount);

                } else {

                    bal -= Integer.parseInt(amount);
                }
            }

            balance.setText("Current Balance : Rs " + bal);

        } catch (Exception e) {

            System.out.println(e);
        }

        getContentPane().setBackground(Color.WHITE);

        setSize(400, 620);
        setLocation(450, 100);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        setVisible(false);
    }

    public static void main(String[] args) {

        new MiniStatement("");
    }
}