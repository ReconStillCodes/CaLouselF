package controller;

import java.util.ArrayList;
import java.util.List;

import database.ItemDAO;
import database.UserDAO;
import model.Item;
import session.Session;

public class ItemController {

	private final UserDAO userDAO = new UserDAO();
	private final ItemDAO itemDAO = new ItemDAO();

	public List<Item> viewItem() {

		List<Item> items = new ArrayList<Item>();

		if (Session.user.getRole().toString().equals("seller")) {
			items = itemDAO.getAllSellerItems(Session.user.getUser_id());
		} else {
			items = itemDAO.getAllAvailableItems();
		}

		return items;
	}

}
