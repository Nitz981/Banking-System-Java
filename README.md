# Banking System (Java + MySQL)

This is a console-based banking system developed using Java and MySQL (JDBC).
It allows users to perform basic banking operations like deposit, withdraw, transfer, and check balance.

--------------------------------------------------

Features

- User Input (Name, Email, Phone)
- Account Creation
- Deposit Money
- Withdraw Money
- Transfer Money
- Check Balance
- Final Account Summary

--------------------------------------------------

Technologies Used

- Java (Core Java)
- JDBC
- MySQL
- VS Code

--------------------------------------------------

Concepts Covered

- Object-Oriented Programming (OOP)
- Classes and Methods
- Conditional Statements
- Loops
- Exception Handling
- Database Connectivity (JDBC)

--------------------------------------------------

Database Setup

CREATE DATABASE banking_db;
USE banking_db;

CREATE TABLE users (
    email VARCHAR(50) PRIMARY KEY,
    name VARCHAR(50),
    phone VARCHAR(10)
);

CREATE TABLE accounts (
    account_no INT PRIMARY KEY,
    email VARCHAR(50),
    balance DOUBLE
);

--------------------------------------------------

How to Run

1. Install Java (JDK 17 or above)
2. Install MySQL and start server
3. Add MySQL Connector JAR in lib folder

Compile:
cd src
javac -cp ".;../lib/mysql-connector-j-9.6.0.jar" *.java

Run:
java -cp ".;../lib/mysql-connector-j-9.6.0.jar" Main

--------------------------------------------------

Project Flow

1. User enters details
2. Account is created
3. User performs transactions
4. Balance is updated and displayed

--------------------------------------------------

Sample Output

BANKING SYSTEM

Enter Name: Nitish
Enter Email: nitish@gmail.com
Enter Phone: 9876543210

Welcome Nitish

1. Create Account
2. Deposit
3. Withdraw
4. Transfer
5. Check Balance
6. Exit

Enter choice: 1
Account Created
Account No: 101
Balance: 5000

Enter choice: 2
Enter Account No: 101
Enter Amount: 2000
Deposit Successful
Balance: 7000

Enter choice: 3
Enter Account No: 101
Enter Amount: 1000
Withdraw Successful
Balance: 6000

Enter choice: 5
Enter Account No: 101
Balance: 6000

Enter choice: 6

User Details:
Name: Nitish
Email: nitish@gmail.com
Phone: 9876543210
Final Balance: 6000

Thank You

--------------------------------------------------

Author

Nitish Kumar 
