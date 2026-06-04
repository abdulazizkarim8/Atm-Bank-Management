package bank.management;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JButton login, signup, clear;
    JTextField cardTextField;
    JPasswordField pinTextField;

    Login() {

        setTitle("AUTOMATED TELLER MACHINE");
        setLayout(null);

        // ===== IMAGE =====
        // Put image inside: src/images/logo.jpg

        ImageIcon i1 = new ImageIcon("src/images/logo.jpg");

        Image i2 = i1.getImage().getScaledInstance(
                100,
                100,
                Image.SCALE_DEFAULT
        );

        ImageIcon i3 = new ImageIcon(i2);

        JLabel label = new JLabel(i3);
        label.setBounds(70, 10, 100, 100);
        add(label);

        // ===== HEADING =====
        JLabel text = new JLabel("WELCOME TO ATM");
        text.setFont(new Font("Oswald", Font.BOLD, 38));
        text.setBounds(200, 40, 450, 40);
        add(text);

        // ===== CARD NUMBER =====
        JLabel cardno = new JLabel("Card No:");
        cardno.setFont(new Font("Raleway", Font.BOLD, 28));
        cardno.setBounds(125, 150, 375, 30);
        add(cardno);

        cardTextField = new JTextField();
        cardTextField.setBounds(300, 150, 230, 30);
        cardTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(cardTextField);

        // ===== PIN =====
        JLabel pin = new JLabel("PIN:");
        pin.setFont(new Font("Raleway", Font.BOLD, 28));
        pin.setBounds(125, 220, 375, 30);
        add(pin);

        pinTextField = new JPasswordField();
        pinTextField.setBounds(300, 220, 230, 30);
        pinTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(pinTextField);

        // ===== SIGN IN BUTTON =====
        login = new JButton("SIGN IN");
        login.setBounds(300, 300, 100, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);

        // ===== CLEAR BUTTON =====
        clear = new JButton("CLEAR");
        clear.setBounds(430, 300, 100, 30);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.addActionListener(this);
        add(clear);

        // ===== SIGN UP BUTTON =====
        signup = new JButton("SIGN UP");
        signup.setBounds(300, 350, 230, 30);
        signup.setBackground(Color.BLACK);
        signup.setForeground(Color.WHITE);
        signup.addActionListener(this);
        add(signup);

        // ===== FRAME SETTINGS =====
        getContentPane().setBackground(Color.WHITE);

        setSize(800, 480);
        setLocation(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // ===== BUTTON ACTIONS =====
    public void actionPerformed(ActionEvent ae) {

    if (ae.getSource() == clear) {

        cardTextField.setText("");
        pinTextField.setText("");

    } else if (ae.getSource() == login) {

        try {

            Conn conn = new Conn();

            String cardnumber = cardTextField.getText();
            String pinnumber = String.valueOf(pinTextField.getPassword());

            String query = "SELECT * FROM login WHERE cardnumber = ? AND pin = ?";

            PreparedStatement ps = conn.c.prepareStatement(query);

            ps.setString(1, cardnumber);
            ps.setString(2, pinnumber);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                setVisible(false);
                new Transactions(pinnumber).setVisible(true);

            } else {

                JOptionPane.showMessageDialog(null,
                        "Incorrect Card Number or Pin");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

    } else if (ae.getSource() == signup) {

        setVisible(false);
        new SignupOne().setVisible(true);
    }
}

public static void main(String[] args) {

    new Login();
}

    void Visible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}