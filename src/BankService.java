import java.sql.*;

public class BankService {

    public void createAccount(int acc, String email, double bal) {
        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO accounts VALUES (?, ?, ?)");
            ps.setInt(1, acc);
            ps.setString(2, email);
            ps.setDouble(3, bal);

            ps.executeUpdate();
            System.out.println("Account Created Successfully");

        } catch (Exception e) {
            System.out.println("Account already exists");
        }
    }

    public void deposit(int acc, double amt) {
        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "UPDATE accounts SET balance = balance + ? WHERE account_no=?");
            ps.setDouble(1, amt);
            ps.setInt(2, acc);

            if (ps.executeUpdate() > 0) {
                log(con, acc, "DEPOSIT", amt);
                System.out.println("Deposit Successful");
                show(acc);
            } else {
                System.out.println("Account not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void withdraw(int acc, double amt) {
        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "SELECT balance FROM accounts WHERE account_no=?");
            ps.setInt(1, acc);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("balance");

                if (balance >= amt) {

                    PreparedStatement upd = con.prepareStatement(
                        "UPDATE accounts SET balance = balance - ? WHERE account_no=?");
                    upd.setDouble(1, amt);
                    upd.setInt(2, acc);
                    upd.executeUpdate();

                    log(con, acc, "WITHDRAW", amt);

                    System.out.println("Withdraw Successful");
                    show(acc);

                } else {
                    System.out.println("Insufficient Balance");
                }

            } else {
                System.out.println("Account not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void transfer(int from, int to, double amt) {
        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "SELECT balance FROM accounts WHERE account_no=?");
            ps.setInt(1, from);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("balance");

                if (balance >= amt) {

                    PreparedStatement d = con.prepareStatement(
                        "UPDATE accounts SET balance = balance - ? WHERE account_no=?");
                    d.setDouble(1, amt);
                    d.setInt(2, from);
                    d.executeUpdate();

                    PreparedStatement a = con.prepareStatement(
                        "UPDATE accounts SET balance = balance + ? WHERE account_no=?");
                    a.setDouble(1, amt);
                    a.setInt(2, to);
                    a.executeUpdate();

                    log(con, from, "TRANSFER_OUT", amt);
                    log(con, to, "TRANSFER_IN", amt);

                    System.out.println("Transfer Successful");
                    show(from);

                } else {
                    System.out.println("Insufficient Balance");
                }

            } else {
                System.out.println("Sender account not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void show(int acc) {
        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "SELECT balance FROM accounts WHERE account_no=?");
            ps.setInt(1, acc);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("\nCurrent Balance: Rs " + rs.getDouble("balance"));
            }

            PreparedStatement t = con.prepareStatement(
                "SELECT * FROM transactions WHERE account_no=? ORDER BY id DESC LIMIT 5");
            t.setInt(1, acc);

            ResultSet tr = t.executeQuery();

            System.out.println("\nRecent Transactions:");
            System.out.println("----------------------------");

            while (tr.next()) {
                System.out.println(
                    tr.getString("type") + "   Rs " + tr.getDouble("amount"));
            }

            System.out.println("----------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void log(Connection con, int acc, String type, double amt) throws Exception {
        PreparedStatement ps = con.prepareStatement(
            "INSERT INTO transactions(account_no, type, amount) VALUES (?, ?, ?)");
        ps.setInt(1, acc);
        ps.setString(2, type);
        ps.setDouble(3, amt);
        ps.executeUpdate();
    }
}