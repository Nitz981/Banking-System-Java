import java.sql.*;

public class AuthService {

    public boolean isValidName(String name) {
        return name.matches("[a-zA-Z ]+");
    }

    public boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    public boolean isValidPhone(String phone) {
        return phone.matches("\\d{10}");
    }

    public String loginOrRegister(String name, String email, String phone) {

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement check = con.prepareStatement(
                    "SELECT name FROM users WHERE email=?");
            check.setString(1, email);

            ResultSet rs = check.executeQuery();

            if (rs.next()) {
                return rs.getString("name");
            }

            PreparedStatement insert = con.prepareStatement(
                    "INSERT INTO users VALUES (?, ?, ?)");

            insert.setString(1, email);
            insert.setString(2, name);
            insert.setString(3, phone);

            insert.executeUpdate();

            return name;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}