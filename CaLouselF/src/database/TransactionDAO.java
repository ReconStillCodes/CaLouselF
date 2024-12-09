package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Transaction;

public class TransactionDAO {

	private final Connect connect = Connect.getInstance();

	public void insertTransaction(Transaction transaction) {
		String query = "INSERT INTO transaction (transaction_id, user_id, item_id) VALUES (?, ?, ?)";
		PreparedStatement ps = connect.preparedStatement(query);

		try {
			ps.setString(1, transaction.getId());
			ps.setString(2, transaction.getUser_id());
			ps.setString(3, transaction.getItem_id());

			ps.executeUpdate();
		} catch (SQLException e) {

			System.out.println("Fail Insert Transaction");
		}
	}

	public String getMaxUserID() {
		String query = "SELECT MAX(transaction_id) FROM transaction";
		ResultSet rs = connect.execQuery(query);

		try {
			if (rs != null && rs.next()) {
				String id = rs.getString(1);
				return id;
			}
		} catch (SQLException e) {
			System.out.println("No Transaction Found");
		}

		return null;
	}
}