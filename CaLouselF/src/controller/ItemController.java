package controller;

import java.util.ArrayList;
import java.util.List;

import database.ItemDAO;
import model.Item;
import session.Session;

public class ItemController { // Controller to handle Item's logic

	private final ItemDAO itemDAO = new ItemDAO(); // Access to Item database

	public List<Item> viewItem() { // View items in homepage

		List<Item> items = new ArrayList<Item>();

		if (Session.user.getRole().toString().toLowerCase().equals("seller")) { // Seller will see his/her items only
			items = itemDAO.getAllSellerItems(Session.user.getUser_id());
		} else {
			items = itemDAO.getAllAvailableItems(); // Admin and Buyer will see all available items
		}

		return items;
	}

	public List<Item> browseItem(String name) { // Search for items

		List<Item> items = new ArrayList<Item>();

		if (name.isBlank() || name.isEmpty() || name.length() <= 0) {
			items = viewItem();
		} else if (Session.user.getRole().toString().equals("seller")) { // Search seller's items
			items = itemDAO.browseSellerItems(Session.user.getUser_id(), name);
		} else {
			items = itemDAO.browseAvailableItems(name); // Search all available items
		}

		return items;
	}

	public void deleteItem(String id) { // Delete Items
		itemDAO.deleteItem(id);
	}

	public boolean uploadItem(String name, String category, String size, String price) { // Add Item
		if (!checkItemValidation(name, category, size, price))
			return false;

		Item item = new Item(generateID(), name, price, size, category, Session.user.getUser_id());
		itemDAO.insertItem(item);
		return true;
	}

	private boolean checkItemValidation(String name, String category, String size, String price) { // To check for item
																									// information
																									// validation
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

	private boolean checkInputEmpty(String str) { // Check if string is empty or not
		if (str == null || str.isEmpty() || str.isBlank() || str.length() <= 0)
			return false;

		return true;
	}

	private String generateID() { // Generate new Id
		String latestID = itemDAO.getMaxUserID();

		if (!checkInputEmpty(latestID)) {
			return "IT001";
		}

		int number = Integer.parseInt(latestID.substring(2)) + 1;

		return String.format("IT%03d", number);
	}

	public Item getItemByID(String item_id) { // Get item by id
		return itemDAO.getItemByID(item_id);
	}

	public Boolean editItem(String name, String category, String size, String price, String item_id) { // Edit item's
																										// information
		if (!checkItemValidation(name, category, size, price))
			return false;

		itemDAO.editItem(name, category, size, price, item_id);
		return true;
	}

	public void updatePurchaseItem(String item_id) { // When purchasing items, some status must be updated so it will
														// not be available anymore
		itemDAO.updatePurchaseItem(item_id);
	}

	public List<Item> viewRequestItem() { // To get all pending items

		List<Item> items = new ArrayList<Item>();
		items = itemDAO.getAllPendingItems();
		return items;
	}

	public void acceptItem(String item_id) { // Accept a pending items and change its status
		itemDAO.acceptItem(item_id);
	}

	public void declineItem(String reason, String item_id) { // Decline a pending items and delete it
		if (!checkInputEmpty(reason)) {
			return;
		}

		itemDAO.deleteItem(item_id);
	}

	public void makeOffer(String offer, String curr_offer, String user_id, String item_id) { // Make an offer
		if (!checkInputEmpty(offer)) { // Validate offer
			return;
		}

		for (int i = 0; i < offer.length(); i++) { // Validate offer
			if (!Character.isDigit(offer.charAt(i)))
				return;
		}

		if (Double.parseDouble(offer) <= Double.parseDouble(curr_offer)) { // validate offer
			return;
		}

		itemDAO.makeOffer(offer, user_id, item_id);
	}

	public List<Item> viewOffer(String seller_id) { // Get all items that is being offered
		List<Item> items = new ArrayList<Item>();
		items = itemDAO.getAllOfferItems(seller_id);
		return items;
	}

	public void acceptOffer(String price, String item_id) { // Accept offer and create new transaction
		itemDAO.acceptOffer(price, item_id);
	}

	public void declineOffer(String reason, String item_id) { // Decline offer
		if (!checkInputEmpty(reason)) {
			return;
		}

		itemDAO.declineOffer(item_id);
	}

}
