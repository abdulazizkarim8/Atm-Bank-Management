package bank.management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.Date;

public class Withdrawl extends JFrame implements ActionListener {

    JTextField amount;
    JButton withdrawl, back;
    String pinnumber;

    Withdrawl(String pinnumber) {

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
        JLabel text = new JLabel("Enter the amount you want to withdraw");
        text.setBounds(170, 300, 400, 20);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        // Amount Field
        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 22));
        amount.setBounds(170, 350, 320, 30);
        image.add(amount);

        // Withdraw Button
        withdrawl = new JButton("Withdraw");
        withdrawl.setBounds(170, 400, 150, 30);
        withdrawl.addActionListener(this);
        image.add(withdrawl);

        // Back Button
        back = new JButton("Back");
        back.setBounds(340, 400, 150, 30);
        back.addActionListener(this);
        image.add(back);

        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == withdrawl) {

            String number = amount.getText();
            Date date = new Date();

            if (number.equals("")) {

                JOptionPane.showMessageDialog(null,
                        "Please enter the amount");

            } else {

                try {

                    Conn conn = new Conn();

                    // Check Balance
                    ResultSet rs = conn.s.executeQuery(
                            "select * from bank where pin = '" + pinnumber + "'");

                    int balance = 0;

                    while (rs.next()) {

                        if (rs.getString("type").equals("Deposit")) {
                            balance += Integer.parseInt(rs.getString("amount"));
                        } else {
                            balance -= Integer.parseInt(rs.getString("amount"));
                        }
                    }

                    int withdrawAmount = Integer.parseInt(number);

                    // Insufficient Balance
                    if (balance < withdrawAmount) {

                        JOptionPane.showMessageDialog(null,
                                "Insufficient Balance");

                        return;
                    }

                    // Insert Withdrawal
                    String query = "insert into bank values('"
                            + pinnumber + "', '"
                            + date + "', 'Withdraw', '"
                            + number + "')";

                    conn.s.executeUpdate(query);

                    JOptionPane.showMessageDialog(null,
                            "Rs " + number + " Withdraw Successfully");

                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);

                } catch (Exception e) {
                    System.out.println(e);
                }
            }

        } else if (ae.getSource() == back) {

            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Withdrawl("");
    }
}