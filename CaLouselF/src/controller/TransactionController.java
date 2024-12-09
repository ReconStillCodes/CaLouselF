package controller;

import java.util.ArrayList;
import java.util.List;

import database.TransactionDAO;
import model.Transaction;

public class TransactionController {

	TransactionDAO transactionDAO = new TransactionDAO();

	public void purchaseItem(String userId, String itemId) {
		Transaction transaction = new Transaction(generateID(), userId, itemId);
		transactionDAO.insertTransaction(transaction);
	}

	private String generateID() {
		String latestID = transactionDAO.getMaxUserID();

		if (latestID == null || latestID.isEmpty() || latestID.isBlank() || latestID.length() <= 0) {
			return "TR001";
		}

		int number = Integer.parseInt(latestID.substring(2)) + 1;

		return String.format("TR%03d", number);
	}

	public List<Transaction> viewHistory(String userId) {
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions = transactionDAO.getTransactionByUserID(userId);
		return transactions;
	}

}
