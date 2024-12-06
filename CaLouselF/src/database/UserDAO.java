package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class UserDAO {
	private Connect connect = Connect.getInstance();

	public User getUserByUsername(String username) {
		String query = "SELECT * FROM user WHERE Username LIKE ?";

		try (PreparedStatement ps = connect.preparedStatement(query)) {
			ps.setString(1, username);

			try (ResultSet rs = ps.executeQuery()) {

				if (rs != null && rs.next()) {
					String user_id = rs.getString("User_id");
					String usernameStr = rs.getString("Username");
					String password = rs.getString("Password");
					String phone = rs.getString("Phone_Number");
					String address = rs.getString("Address");
					String role = rs.getString("Role");

					return new User(user_id, usernameStr, password, phone, address, role);
				}

			}
		} catch (SQLException e) {
			System.out.println("No User found");
		}
		return null;
	}

	public User getUserByUsernameAndPassword(String username, String password) {
		String query = "SELECT * FROM user WHERE Username LIKE ? AND Password LIKE ?";

		try (PreparedStatement ps = connect.preparedStatement(query)) {
			ps.setString(1, username);
			ps.setString(2, password);

			try (ResultSet rs = ps.executeQuery()) {

				if (rs != null && rs.next()) {
					String user_id = rs.getString("User_id");
					String usernameStr = rs.getString("Username");
					String passwordStr = rs.getString("Password");
					String phone = rs.getString("Phone_Number");
					String address = rs.getString("Address");
					String role = rs.getString("Role");

					return new User(user_id, usernameStr, passwordStr, phone, address, role);
				}

			}
		} catch (SQLException e) {
			System.out.println("No User found");
		}
		return null;
	}

	public void insertUser(User user) {
		String query = "INSERT INTO user (User_id, Username, Password, Phone_Number, Address, Role) VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = connect.preparedStatement(query);

		try {
			ps.setString(1, user.getUser_id());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getPhone_number());
			ps.setString(5, user.getAddress());
			ps.setString(6, user.getRole());
			ps.executeUpdate();
			System.out.println("Insert User");
		} catch (SQLException e) {

			System.out.println("Fail Insert User");
		}
	}

	public String getMaxUserID() {
		String query = "SELECT MAX(user_id) FROM User";
		ResultSet rs = connect.execQuery(query);

		try {
			if (rs != null && rs.next()) {
				String id = rs.getString(1);
				return id;
			}
		} catch (SQLException e) {
			System.out.println("No User Found");
		}

		return null;
	}
}
