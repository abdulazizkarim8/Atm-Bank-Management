ATM and Account management system

ATM and Account management system is a desktop application simulating Automated Teller Machine (ATM) workflows. It is built as a Java Swing application backed by MySQL for data storage.

The platform includes forms to register accounts, generate card credentials, authenticate logins, and handle transactions like deposits, withdrawals, PIN updates, and statements.

Project Structure
.
└── src/
    └── bank/
        └── management/
            ├── Conn.java
            ├── Login.java
            ├── SignupOne.java
            ├── SignupTwo.java
            ├── SignupThree.java
            ├── Transactions.java
            ├── Deposit.java
            ├── Withdrawl.java
            ├── FastCash.java
            ├── PinChange.java
            ├── BalanceEnquiry.java
            └── MiniStatement.java
Registration Flow
The signup forms collect user configurations across three distinct steps:

SignupOne.java: Gathers personal details (name, father's name, DOB, gender, email, location).
SignupTwo.java: Collects demographic info, income range, education, occupation, and identification parameters.
SignupThree.java: Configures account type, requested card features, and generates the unique Card Number and PIN.
Authentication
Login.java: Serves as the application's entry screen, verifying input credentials against the MySQL database.
Transactions
Once a user successfully logs in, Transactions.java acts as the home hub for performing banking actions:

Deposit.java and Withdrawl.java: Execute cash transfers, updating the central ledger database.
FastCash.java: Provides quick pre-set withdrawal values.
PinChange.java: Updates the active PIN associated with the user's card.
BalanceEnquiry.java and MiniStatement.java: Retreive and show current balance details and transaction history.
Tech Stack
Core
Java
Java Swing & AWT
Database
MySQL
JDBC Driver (com.mysql.cj.jdbc.Driver)
Setup
Requirements
JDK 8+
MySQL Server
MySQL Connector Jar
Database
The database configuration script runs as:

CREATE DATABASE IF NOT EXISTS bankmanagementsystem;
USE bankmanagementsystem;

CREATE TABLE login (
    formno VARCHAR(20),
    cardnumber VARCHAR(25),
    pin VARCHAR(10)
);

CREATE TABLE signupthree (
    formno VARCHAR(20),
    accounttype VARCHAR(30),
    cardnumber VARCHAR(25),
    pin VARCHAR(10),
    facility VARCHAR(100)
);

CREATE TABLE bank (
    pin VARCHAR(10),
    date VARCHAR(50),
    type VARCHAR(20),
    amount VARCHAR(20)
);
Configure your local database credentials inside Conn.java:

c = DriverManager.getConnection("jdbc:mysql:///bankmanagementsystem", "YOUR_MYSQL_USERNAME", "YOUR_MYSQL_PASSWORD");
Running the App
# Compile the files
javac -d bin -cp "lib/mysql-connector-java.jar" src/bank/management/*.java

# Run the app
java -cp "bin;lib/mysql-connector-java.jar" bank.management.Login
Database Schema
Table	Purpose
login	Stores card credentials (card number, PIN, form number)
signupthree	Stores chosen account types and preferred facilities
bank	Logs transaction entries (date, action type, amount)
