package bank.management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conn {

    Connection c;
    Statement s;

    public Conn() {
        try {

            // Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Database Connection
            c = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bankmanagement",
                    "root",
                    "abdulaziz@123"
            );

            s = c.createStatement();

            System.out.println("Database Connected Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}