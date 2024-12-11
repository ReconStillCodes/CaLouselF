package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Transaction;

public class TransactionDAO {

	private final Connect connect = Connect.getInstance(); // get a connection

	public void insertTransaction(Transaction transaction) { // insert a transaction
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

	public String getMaxUserID() { // get max id to create a new id
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

	public List<Transaction> getTransactionByUserID(String userId) { // get transaction by user id
		String query = "SELECT * FROM transaction WHERE user_id LIKE ?";

		List<Transaction> transactions = new ArrayList<Transaction>();

		try (PreparedStatement ps = connect.preparedStatement(query)) {
			ps.setString(1, userId);

			try (ResultSet rs = ps.executeQuery()) {

				while (rs != null && rs.next()) {
					String transaction_id = rs.getString("transaction_id");
					String user_id = rs.getString("user_id");
					String item_id = rs.getString("Item_id");
					transactions.add(new Transaction(transaction_id, user_id, item_id));
				}

			}
		} catch (SQLException e) {
			System.out.println("No Transaction found");
		}
		return transactions;
	}
}
