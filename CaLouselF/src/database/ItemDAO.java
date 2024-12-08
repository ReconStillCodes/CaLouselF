package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Item;

public class ItemDAO {

	private final Connect connect = Connect.getInstance();

	public List<Item> getAllAvailableItems() {
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

					Item item = new Item(item_id, name, price, size, category, status, wishlist, offer_status,
							seller_id);
					items.add(item);
				}

			}

		} catch (SQLException e) {
			System.out.println("No Item found");
		}
		return items;
	}

	public List<Item> getAllSellerItems(String sellerId) {
		String query = "SELECT * FROM item WHERE Seller_ID LIKE \"?\" ";
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

					Item item = new Item(item_id, name, price, size, category, status, wishlist, offer_status,
							seller_id);
					items.add(item);
				}

			}

		} catch (SQLException e) {
			System.out.println("No Item found");
		}
		return items;
	}

	public List<Item> browseAvailableItems(String item_name) {
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

					Item item = new Item(item_id, name, price, size, category, status, wishlist, offer_status,
							seller_id);
					items.add(item);
				}

			}

		} catch (SQLException e) {
			System.out.println("No Item found");
		}
		return items;
	}

	public List<Item> browseSellerItems(String sellerId, String item_name) {
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

					Item item = new Item(item_id, name, price, size, category, status, wishlist, offer_status,
							seller_id);
					items.add(item);
				}

			}

		} catch (SQLException e) {
			System.out.println("No Item found");
		}
		return items;
	}

	public void deleteItem(String id) {
		String query = "DELETE FROM item WHERE item_id LIKE ?";
		PreparedStatement ps = connect.preparedStatement(query);

		try {
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {

			System.out.println("Fail to delete item");
		}
	}

	public void insertItem(Item item) {
		String query = "INSERT INTO item (Item_id, Item_name, Item_size, Item_price, Item_category, Item_status, Item_wishlist, Item_offer_status, Seller_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
			ps.executeUpdate();
		} catch (SQLException e) {

			System.out.println("Fail Insert Item");
		}
	}

	public String getMaxUserID() {
		String query = "SELECT MAX(Item_id) FROM item";
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
