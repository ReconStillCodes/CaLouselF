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

	public List<Item> browseItem(String name) {

		List<Item> items = new ArrayList<Item>();

		if (name.isBlank() || name.isEmpty() || name.length() <= 0) {
			items = viewItem();
		} else if (Session.user.getRole().toString().equals("seller")) {
			items = itemDAO.browseSellerItems(Session.user.getUser_id(), name);
		} else {
			items = itemDAO.browseAvailableItems(name);
		}

		return items;
	}

	public void deleteItem(String id) {
		itemDAO.deleteItem(id);
	}

}
