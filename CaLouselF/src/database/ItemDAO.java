package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Item;

public class ItemDAO {

	private final Connect connect = Connect.getInstance(); // get connection

	public List<Item> getAllAvailableItems() { // get all available item from database
		String query = "SELECT * FROM item WHERE Item_status LIKE \"Available\" ";
		List<Item> items = new ArrayList<Item>();

		try (PreparedStatement ps = connect.preparedStatement(query)) {

			try (ResultSet rs = ps.executeQuery()) {

				while (rs != null && rs.next()) {
					String item_id = rs.getString("Item_id");
					String name = rs.getString("Item_name");
					String size = rs.getString("Item_size");
					String price = rs.getString("Item_price");
					String category = rs.getString("Item_category");
					String status = rs.getString("Item_status");
					String wishlist = rs.getString("Item_wishlist");
					String offer_status = rs.getString("Item_offer_status");
					String seller_id = rs.getString("Seller_ID");
					String offer_price = rs.getString("Item_offer_price");
					String offer_user_id = rs.getString("Item_offer_user_id");

					Item item = new Item(item_id, name, price, size, category, status, wishlist, offer_status,
							seller_id, offer_price, offer_user_id);
					items.add(item);
				}

			}

		} catch (SQLException e) {
			System.out.println("No Item found");
		}
		return items;
	}

	public List<Item> getAllSellerItems(String sellerId) { // Get seller's item
		String query = "SELECT * FROM item WHERE Seller_ID LIKE ? ";
		List<Item> items = new ArrayList<Item>();

		try (PreparedStatement ps = connect.preparedStatement(query)) {
			ps.setString(1, sellerId);
			try (ResultSet rs = ps.executeQuery()) {

				while (rs != null && rs.next()) {
					String item_id = rs.getString("Item_id");
					String name = rs.getString("Item_name");
					String size = rs.getString("Item_size");
					String price = rs.getString("Item_price");
					String category = rs.getString("Item_category");
					String status = rs.getString("Item_status");
					String wishlist = rs.getString("Item_wishlist");
					String offer_status = rs.getString("Item_offer_status");
					String seller_id = rs.getString("Seller_ID");
					String offer_price = rs.getString("Item_offer_price");
					String offer_user_id = rs.getString("Item_offer_user_id");

					Item item = new Item(item_id, name, price, size, category, status, wishlist, offer_status,
							seller_id, offer_price, offer_user_id);
					items.add(item);
				}

			}

		} catch (SQLException e) {
			System.out.println("No Item found");
		}
		return items;
	}

	public List<Item> browseAvailableItems(String item_name) { // Search for available items
		String query = "SELECT * FROM item WHERE Item_status LIKE \"Available\" AND Item_name LIKE ?";
		List<Item> items = new ArrayList<Item>();

		try (PreparedStatement ps = connect.preparedStatement(query)) {
			ps.setString(1, "%" + item_name + "%");
			try (ResultSet rs = ps.executeQuery()) {

				while (rs != null && rs.next()) {
					String item_id = rs.getString("Item_id");
					String name = rs.getString("Item_name");
					String size = rs.getString("Item_size");
					String price = rs.getString("Item_price");
					String category = rs.getString("Item_category");
					String status = rs.getString("Item_status");
					String wishlist = rs.getString("Item_wishlist");
					String offer_status = rs.getString("Item_offer_status");
					String seller_id = rs.getString("Seller_ID");
					String offer_price = rs.getString("Item_offer_price");
					String offer_user_id = rs.getString("Item_offer_user_id");

					Item item = new Item(item_id, name, price, size, category, status, wishlist, offer_status,
							seller_id, offer_price, offer_user_id);
					items.add(item);
				}

			}

		} catch (SQLException e) {
			System.out.println("No Item found");
		}
		return items;
	}

	public List<Item> browseSellerItems(String sellerId, String item_name) { // Search for seller's items
		String query = "SELECT * FROM item WHERE Seller_ID LIKE ? AND Item_name LIKE ?  ";
		List<Item> items = new ArrayList<Item>();

		try (PreparedStatement ps = connect.preparedStatement(query)) {
			ps.setString(1, sellerId);
			ps.setString(2, "%" + item_name + "%");
			try (ResultSet rs = ps.executeQuery()) {

				while (rs != null && rs.next()) {
					String item_id = rs.getString("Item_id");
					String name = rs.getString("Item_name");
					String size = rs.getString("Item_size");
					String price = rs.getString("Item_price");
					String category = rs.getString("Item_category");
					String status = rs.getString("Item_status");
					String wishlist = rs.getString("Item_wishlist");
					String offer_status = rs.getString("Item_offer_status");
					String seller_id = rs.getString("Seller_ID");
					String offer_price = rs.getString("Item_offer_price");
					String offer_user_id = rs.getString("Item_offer_user_id");

					Item item = new Item(item_id, name, price, size, category, status, wishlist, offer_status,
							seller_id, offer_price, offer_user_id);
					items.add(item);
				}

			}

		} catch (SQLException e) {
			System.out.println("No Item found");
		}
		return items;
	}

	public void deleteItem(String id) { // delete item from database
		String query = "DELETE FROM item WHERE item_id LIKE ?";
		PreparedStatement ps = connect.preparedStatement(query);

		try {
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {

			System.out.println("Fail to delete item");
		}
	}

	public void insertItem(Item item) {// insert item to database
		String query = "INSERT INTO item (Item_id, Item_name, Item_size, Item_price, Item_category, Item_status, Item_wishlist, Item_offer_status, Seller_ID, Item_offer_price, Item_offer_user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = connect.preparedStatement(query);

		try {
			ps.setString(1, item.getId());
			ps.setString(2, item.getName());
			ps.setString(3, item.getSize());
			ps.setString(4, item.getPrice());
			ps.setString(5, item.getCategory());
			ps.setString(6, item.getStatus());
			ps.setString(7, item.getWishlist());
			ps.setString(8, item.getOffer_status());
			ps.setString(9, item.getSeller_id());
			ps.setString(10, item.getOffer_price());
			ps.setString(11, item.getOffer_user_id());
			ps.executeUpdate();
		} catch (SQLException e) {

			System.out.println("Fail Insert Item");
		}
	}

	public String getMaxUserID() { // get max id to create new id
		String query = "SELECT MAX(Item_id) FROM item";
		ResultSet rs = connect.execQuery(query);

		try {
			if (rs != null && rs.next()) {
				String id = rs.getString(1);
				return id;
			}
		} catch (SQLException e) {
			System.out.println("No Item Found");
		}

		return null;
	}

	public Item getItemByID(String itemId) { // get item it's id
		String query = "SELECT * FROM item WHERE Item_id LIKE ?";

		try (PreparedStatement ps = connect.preparedStatement(query)) {
			ps.setString(1, itemId);

			try (ResultSet rs = ps.executeQuery()) {

				if (rs != null && rs.next()) {
					String item_id = rs.getString("Item_id");
					String name = rs.getString("Item_name");
					String size = rs.getString("Item_size");
					String price = rs.getString("Item_price");
					String category = rs.getString("Item_category");
					String status = rs.getString("Item_status");
					String wishlist = rs.getString("Item_wishlist");
					String offer_status = rs.getString("Item_offer_status");
					String seller_id = rs.getString("Seller_ID");
					String offer_price = rs.getString("Item_offer_price");
					String offer_user_id = rs.getString("Item_offer_user_id");

					return new Item(item_id, name, price, size, category, status, wishlist, offer_status, seller_id,
							offer_price, offer_user_id);
				}

			}
		} catch (SQLException e) {
			System.out.println("No Item found");
		}
		return null;
	}

	public void editItem(String name, String category, String size, String price, String item_id) { // update item
		String query = "UPDATE item SET Item_name = ?, Item_category = ?, Item_size = ?, Item_price = ? WHERE Item_id = ?";
		PreparedStatement ps = connect.preparedStatement(query);

		try {
			ps.setString(1, name);
			ps.setString(2, category);
			ps.setString(3, size);
			ps.setString(4, price);
			ps.setString(5, item_id);
			ps.executeUpdate();
		} catch (SQLException e) {

			System.out.println("Fail Edit Item");
		}
	}

	public void updatePurchaseItem(String item_id) { // update items's status when purchased
		String query = "UPDATE item SET Item_status = 'Unavailable', Item_wishlist = 'Unavailable', Item_Offer_Status = 'Unavailable' WHERE Item_id = ?";
		PreparedStatement ps = connect.preparedStatement(query);

		try {
			ps.setString(1, item_id);
			ps.executeUpdate();
		} catch (SQLException e) {

			System.out.println("Fail Edit Item");
		}
	}

	public List<Item> getAllPendingItems() { // get all requested items
		String query = "SELECT * FROM item WHERE Item_status LIKE \"Pending\" ";
		List<Item> items = new ArrayList<Item>();

		try (PreparedStatement ps = connect.preparedStatement(query)) {

			try (ResultSet rs = ps.executeQuery()) {

				while (rs != null && rs.next()) {
					String item_id = rs.getString("Item_id");
					String name = rs.getString("Item_name");
					String size = rs.getString("Item_size");
					String price = rs.getString("Item_price");
					String category = rs.getString("Item_category");
					String status = rs.getString("Item_status");
					String wishlist = rs.getString("Item_wishlist");
					String offer_status = rs.getString("Item_offer_status");
					String seller_id = rs.getString("Seller_ID");
					String offer_price = rs.getString("Item_offer_price");
					String offer_user_id = rs.getString("Item_offer_user_id");

					Item item = new Item(item_id, name, price, size, category, status, wishlist, offer_status,
							seller_id, offer_price, offer_user_id);
					items.add(item);
				}

			}

		} catch (SQLException e) {
			System.out.println("No Item found");
		}
		return items;
	}

	public void acceptItem(String item_id) { // update item status when accepted
		String query = "UPDATE item SET Item_status = 'Available'  WHERE Item_id = ?";
		PreparedStatement ps = connect.preparedStatement(query);

		try {
			ps.setString(1, item_id);
			ps.executeUpdate();
		} catch (SQLException e) {

			System.out.println("Fail to AcceptItem");
		}
	}

	public void makeOffer(String offer, String user_id, String item_id) { // Update item offer when a user creates an
																			// offer
		String query = "UPDATE item SET Item_Offer_Status = 'Pending', Item_Offer_Price = ?, Item_Offer_User_id = ? WHERE Item_id = ?";
		PreparedStatement ps = connect.preparedStatement(query);

		try {
			ps.setString(1, offer);
			ps.setString(2, user_id);
			ps.setString(3, item_id);
			ps.executeUpdate();
		} catch (SQLException e) {

			System.out.println("Fail to Make Offer");
		}
	}

	public List<Item> getAllOfferItems(String sellerId) { // get all offer items
		String query = "SELECT * FROM item WHERE Item_offer_status LIKE \"Pending\" AND Seller_id = ? ";
		List<Item> items = new ArrayList<Item>();

		try (PreparedStatement ps = connect.preparedStatement(query)) {
			ps.setString(1, sellerId);

			try (ResultSet rs = ps.executeQuery()) {

				while (rs != null && rs.next()) {
					String item_id = rs.getString("Item_id");
					String name = rs.getString("Item_name");
					String size = rs.getString("Item_size");
					String price = rs.getString("Item_price");
					String category = rs.getString("Item_category");
					String status = rs.getString("Item_status");
					String wishlist = rs.getString("Item_wishlist");
					String offer_status = rs.getString("Item_offer_status");
					String seller_id = rs.getString("Seller_ID");
					String offer_price = rs.getString("Item_offer_price");
					String offer_user_id = rs.getString("Item_offer_user_id");

					Item item = new Item(item_id, name, price, size, category, status, wishlist, offer_status,
							seller_id, offer_price, offer_user_id);
					items.add(item);
				}

			}

		} catch (SQLException e) {
			System.out.println("No Item found");
		}
		return items;
	}

	public void acceptOffer(String price, String item_id) { // accept an offer and update its status
		String query = "UPDATE item SET Item_offer_status = 'Unlisted', Item_price = ?  WHERE Item_id = ?";
		PreparedStatement ps = connect.preparedStatement(query);

		try {
			ps.setString(1, price);
			ps.setString(2, item_id);
			ps.executeUpdate();
		} catch (SQLException e) {

			System.out.println("Fail to Accept Offer");
		}
	}

	public void declineOffer(String item_id) { // decline an offer and update its status
		String query = "UPDATE item SET Item_offer_status = 'Unlisted', Item_offer_price = '0', Item_offer_user_id = NULL  WHERE Item_id = ?";
		PreparedStatement ps = connect.preparedStatement(query);

		try {
			ps.setString(1, item_id);
			ps.executeUpdate();
		} catch (SQLException e) {

			System.out.println("Fail to Decline Offer");
		}
	}

}
