package controller;

import java.util.ArrayList;
import java.util.List;

import database.ItemDAO;
import model.Item;
import session.Session;

public class ItemController {

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

	public boolean uploadItem(String name, String category, String size, String price) {
		if (!checkItemValidation(name, category, size, price))
			return false;

		Item item = new Item(generateID(), name, price, size, category, Session.user.getUser_id());
		itemDAO.insertItem(item);
		return true;
	}

	private boolean checkItemValidation(String name, String category, String size, String price) {
		if (!checkInputEmpty(name) || name.length() < 3)
			return false;

		if (!checkInputEmpty(category) || category.length() < 3)
			return false;

		if (!checkInputEmpty(size))
			return false;

		if (!checkInputEmpty(price))
			return false;

		for (int i = 0; i < price.length(); i++) {
			if (!Character.isDigit(price.charAt(i)))
				return false;
		}

		if (Double.parseDouble(price) <= 0)
			return false;

		return true;
	}

	private boolean checkInputEmpty(String str) {
		if (str == null || str.isEmpty() || str.isBlank() || str.length() <= 0)
			return false;

		return true;
	}

	private String generateID() {
		String latestID = itemDAO.getMaxUserID();

		if (!checkInputEmpty(latestID)) {
			return "IT001";
		}

		int number = Integer.parseInt(latestID.substring(2)) + 1;

		return String.format("IT%03d", number);
	}

	public Item getItemByID(String item_id) {
		return itemDAO.getItemByID(item_id);
	}

}
