package bank.management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {

    JTextField amount;
    JButton deposit, back;
    String pinnumber;

    Deposit(String pinnumber) {

        this.pinnumber = pinnumber;

        setLayout(null);

        // ATM Image
        ImageIcon i1 = new ImageIcon(getClass().getResource("/icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        // Heading
        JLabel text = new JLabel("Enter the amount you want to deposit");
        text.setBounds(170, 300, 400, 20);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        // Amount Field
        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 22));
        amount.setBounds(170, 350, 320, 30);
        image.add(amount);

        // Deposit Button
        deposit = new JButton("Deposit");
        deposit.setBounds(170, 400, 150, 30);
        deposit.addActionListener(this);
        image.add(deposit);

        // Back Button
        back = new JButton("Back");
        back.setBounds(340, 400, 150, 30);
        back.addActionListener(this);
        image.add(back);

        // Frame Settings
        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        // Deposit Button Action
        if (ae.getSource() == deposit) {

            String number = amount.getText();
            Date date = new Date();

            if (number.equals("")) {

                JOptionPane.showMessageDialog(null,
                        "Please enter the amount you want to deposit");

            } else {

                try {

                    Conn conn = new Conn();

                    String query = "INSERT INTO bank VALUES('"
                            + pinnumber + "', '"
                            + date + "', 'Deposit', '"
                            + number + "')";

                    conn.s.executeUpdate(query);

                    JOptionPane.showMessageDialog(null,
                            "Rs " + number + " Deposited Successfully");

                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);

                } catch (Exception e) {
                    System.out.println(e);
                }
            }

        }

        // Back Button Action
        else if (ae.getSource() == back) {

            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Deposit("");
    }
}
