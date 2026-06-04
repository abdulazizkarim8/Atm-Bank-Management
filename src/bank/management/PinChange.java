package bank.management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PinChange extends JFrame implements ActionListener {

    JPasswordField oldPin, newPin, confirmPin;
    JButton change, back;
    String pinnumber;

    PinChange(String pinnumber) {

        this.pinnumber = pinnumber;

        setLayout(null);

        ImageIcon i1 = new ImageIcon(getClass().getResource("/icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(i2));
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("CHANGE YOUR PIN");
        text.setBounds(170, 300, 400, 20);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        JLabel old = new JLabel("Old PIN:");
        old.setBounds(170, 340, 100, 20);
        old.setForeground(Color.WHITE);
        image.add(old);

        oldPin = new JPasswordField();
        oldPin.setBounds(300, 340, 200, 25);
        image.add(oldPin);

        JLabel newp = new JLabel("New PIN:");
        newp.setBounds(170, 380, 100, 20);
        newp.setForeground(Color.WHITE);
        image.add(newp);

        newPin = new JPasswordField();
        newPin.setBounds(300, 380, 200, 25);
        image.add(newPin);

        JLabel conf = new JLabel("Confirm PIN:");
        conf.setBounds(170, 420, 120, 20);
        conf.setForeground(Color.WHITE);
        image.add(conf);

        confirmPin = new JPasswordField();
        confirmPin.setBounds(300, 420, 200, 25);
        image.add(confirmPin);

        change = new JButton("Change");
        change.setBounds(300, 470, 100, 30);
        change.addActionListener(this);
        image.add(change);

        back = new JButton("Back");
        back.setBounds(420, 470, 100, 30);
        back.addActionListener(this);
        image.add(back);

        setSize(900, 900);
        setLocation(300, 0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        String oldp = new String(oldPin.getPassword());
        String newp = new String(newPin.getPassword());
        String confp = new String(confirmPin.getPassword());

        try {

            Conn conn = new Conn();

            if (ae.getSource() == change) {

                if (oldp.equals("") || newp.equals("") || confp.equals("")) {

                    JOptionPane.showMessageDialog(null,
                            "Please fill all fields");

                } else if (!newp.equals(confp)) {

                    JOptionPane.showMessageDialog(null,
                            "PIN does not match");

                } else {

                    // 🔥 SAME STYLE AS WITHDRAW: SIMPLE DB CHECK
                    ResultSet rs = conn.s.executeQuery(
                            "select * from login where pin = '" + oldp + "'");

                    if (rs.next()) {

                        // update login
                        conn.s.executeUpdate(
                                "update login set pin = '" + newp + "' where pin = '" + oldp + "'");

                        // update bank
                        conn.s.executeUpdate(
                                "update bank set pin = '" + newp + "' where pin = '" + oldp + "'");

                        JOptionPane.showMessageDialog(null,
                                "PIN Changed Successfully");

                        setVisible(false);
                        new Transactions(newp).setVisible(true);

                    } else {

                        JOptionPane.showMessageDialog(null,
                                "Incorrect Old PIN");
                    }
                }

            } else if (ae.getSource() == back) {

                setVisible(false);
                new Transactions(pinnumber).setVisible(true);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
    }

    public static void main(String[] args) {
        new PinChange("");
    }
}