package bank.management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignupTwo extends JFrame implements ActionListener {

   
    JTextField phone,cnic;

    JSpinner dobTextField;

    JButton next;

    JRadioButton syes, sno, eyes, eno;
    JComboBox religion, category, occupation, education, income;
    String formno;

    SignupTwo(String formno) {
        
    this.formno =formno;
        setLayout(null);

        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 2");

        JLabel additionalDetails = new JLabel("Page 2: Additional Details");
        additionalDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        additionalDetails.setBounds(290, 80, 400, 30);
        add(additionalDetails);

        JLabel name = new JLabel("RELIGION:");
        name.setFont(new Font("Raleway", Font.BOLD, 14));
        name.setBounds(100, 140, 200, 30);
        add(name);
        
       String valReligion[] = {"Muslim", "Hindu", "Christian", "Other"};
religion = new JComboBox<>(valReligion);
religion.setBounds(300, 140, 400, 30);
religion.setBackground(Color.WHITE);
add(religion);

        JLabel fname = new JLabel("CATEGORY:");
        fname.setFont(new Font("Raleway", Font.BOLD, 14));
        fname.setBounds(100, 190, 200, 30);
        add(fname);

        String valcategory[] = {"General","OBC","SC","ST","Other"};
        category = new JComboBox<>(valcategory);
        category.setBounds(300,190,400,30);
        category.setBackground(Color.white);
        add(category);

     // DOB (FIXED - no external library)
        JLabel dob = new JLabel("INCOME:");
        dob.setFont(new Font("Raleway", Font.BOLD, 14));
        dob.setBounds(100, 240, 200, 30);
        add(dob);

        String valIncome[] = {"Null", "< 1,50,000", "< 2,50,000", "< 5,000,000", "Up to 10,000,000"};
        income = new JComboBox<>(valIncome);
income.setBounds(300, 240, 400, 30); // Moved down by 50px (190 + 50 = 240)
income.setBackground(Color.WHITE);
add(income);


        JLabel gender = new JLabel("EDUCATIONAL");
        gender.setFont(new Font("Raleway", Font.BOLD, 14));
        gender.setBounds(100, 290, 200, 30);
        add(gender);

        JLabel email = new JLabel("QUALIFICATION:");
        email.setFont(new Font("Raleway", Font.BOLD, 14));
        email.setBounds(100, 315, 200, 30);
        add(email);
       
        String valEducation[] = {"Non-Graduation", "Graduate", "Post-Graduate", "Doctrate", "Others"};
 education = new JComboBox<>(valEducation);
education.setBounds(300, 290, 400, 30); // Moved down by 50px (240 + 50 = 290)
education.setBackground(Color.WHITE);
add(education);

        JLabel marital = new JLabel("OCCUPATION:");
        marital.setFont(new Font("Raleway", Font.BOLD, 14));
        marital.setBounds(100, 390, 200, 30);
        add(marital);
        
        String valOccupation[] = {"Salaried", "Self-Employed", "Bussiness", "Student", "Retired", "Others"};
 occupation = new JComboBox<>(valOccupation);
occupation.setBounds(300, 390, 400, 30); // Moved down by 50px (290 + 50 = 340)
occupation.setBackground(Color.WHITE);
add(occupation);


        JLabel address = new JLabel("PHONE NUMBER:");
        address.setFont(new Font("Raleway", Font.BOLD, 14));
        address.setBounds(100, 440, 200, 30);
        add(address);

       phone = new JTextField();
phone.setFont(new Font("Raleway", Font.BOLD, 14)); // FIX: changed '=' to '.'
phone.setBounds(300, 440, 400, 30);               // FIX: changed '=' to '.'
add(phone);
        

        JLabel city = new JLabel("CNIC NUMBER:");
        city.setFont(new Font("Raleway", Font.BOLD, 14));
        city.setBounds(100, 490, 200, 30);
        add(city);

        cnic = new JTextField();
        cnic.setFont(new Font("Raleway", Font.BOLD, 14));
        cnic.setBounds(300, 490, 400, 30);
        add(cnic);

        JLabel state = new JLabel("SENIOR CITIZEN:");
        state.setFont(new Font("Raleway", Font.BOLD, 14));
        state.setBounds(100, 540, 200, 30);
        add(state);

        syes = new JRadioButton("YES");
        syes.setBounds(300, 540, 100, 30);
        syes.setBackground(Color.WHITE);
        add(syes);

        sno = new JRadioButton("NO");
        sno.setBounds(450, 540, 100, 30);
        sno.setBackground(Color.WHITE);
        add(sno);

        ButtonGroup gendergroup = new ButtonGroup();
        gendergroup.add(syes);
        gendergroup.add(sno);
        

        JLabel pincode = new JLabel("EXISTING ACCOUNT");
        pincode.setFont(new Font("Raleway", Font.BOLD, 14));
        pincode.setBounds(100, 590, 200, 30);
        add(pincode);

         eyes = new JRadioButton("YES");
        eyes.setBounds(300, 590, 60, 30);
        eyes.setBackground(Color.WHITE);
        add(eyes);

        eno = new JRadioButton("NO");
        eno.setBounds(450, 590, 120, 30);
        eno.setBackground(Color.WHITE);
        add(eno);

        ButtonGroup existingaccountgroup = new ButtonGroup();
        existingaccountgroup.add(eyes);
        existingaccountgroup.add(eno);
        

        next = new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Raleway", Font.BOLD, 14));
        next.setBounds(620, 660, 80, 30);
        next.addActionListener(this);
        add(next);

        getContentPane().setBackground(Color.WHITE);

        setSize(850, 800);
        setLocation(350, 10);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {


        String sreligion = (String) religion.getSelectedItem();
         String scategory = (String) category.getSelectedItem();
          String sincome = (String) income.getSelectedItem();
           String seducation = (String) education.getSelectedItem();
            String soccupation = (String) occupation.getSelectedItem();
           
        String seniorcitizen = null;

        if (syes.isSelected()) {
            seniorcitizen = "Yes";
        } else if (sno.isSelected()) {
            seniorcitizen = "No";
        }

     
        String existingaccount = null;

        if (eyes.isSelected()) {
            existingaccount = "Yes";
        } else if (eno.isSelected()) {
            existingaccount = "No";
        } 

        String sphone = phone.getText();
        String scnic = cnic.getText();
        

        try {

                Conn c = new Conn();

                String query =
                        "insert into signuptwo values('"
                                + formno + "','"
                                + sreligion + "','"
                                + scategory + "','"
                                + sincome + "','"
                                + seducation + "','"
                                + soccupation + "','"
                                + sphone + "','"
                                + scnic + "','"
                                + seniorcitizen + "','"
                                + existingaccount + "')";
                c.s.executeUpdate(query);
                //Signup Object
                setVisible(false);
                new SignupThree(formno).setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String args[]) {
        new SignupTwo("");
    }
}

