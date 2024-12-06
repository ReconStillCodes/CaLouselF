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
}
