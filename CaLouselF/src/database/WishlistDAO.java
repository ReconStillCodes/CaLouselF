package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Wishlist;

public class WishlistDAO {
	private final Connect connect = Connect.getInstance();

	public Wishlist getItemByItemIDandUserID(String itemId, String userId) { // get wishlist by its id and user id
		String query = "SELECT * FROM wishlist WHERE Item_id LIKE ? AND User_id LIKE ?";

		try (PreparedStatement ps = connect.preparedStatement(query)) {
			ps.setString(1, itemId);
			ps.setString(2, userId);

			try (ResultSet rs = ps.executeQuery()) {

				if (rs != null && rs.next()) {
					String wishlist_id = rs.getString("wishlist_id");
					String item_id = rs.getString("Item_id");
					String user_id = rs.getString("User_id");

					return new Wishlist(wishlist_id, item_id, user_id);
				}

			}
		} catch (SQLException e) {
			System.out.println("No Wishlist found");
		}
		return null;
	}

	public void insertWishlist(Wishlist wishlist) { // insert wishlist
		String query = "INSERT INTO wishlist (Wishlist_id, Item_id, User_id) VALUES (?, ?, ?)";
		PreparedStatement ps = connect.preparedStatement(query);

		try {
			ps.setString(1, wishlist.getId());
			ps.setString(2, wishlist.getItem_id());
			ps.setString(3, wishlist.getUser_id());

			ps.executeUpdate();
		} catch (SQLException e) {

			System.out.println("Fail Insert Wishlist");
		}
	}

	public String getMaxUserID() { // get max id by to create new id
		String query = "SELECT MAX(wishlist_id) FROM wishlist";
		ResultSet rs = connect.execQuery(query);

		try {
			if (rs != null && rs.next()) {
				String id = rs.getString(1);
				return id;
			}
		} catch (SQLException e) {
			System.out.println("No Wishlist Found");
		}

		return null;
	}

	public void deleteWishlist(String id) { // delete id
		String query = "DELETE FROM wishlist WHERE wishlist_id LIKE ?";
		PreparedStatement ps = connect.preparedStatement(query);

		try {
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {

			System.out.println("Fail to delete wishlist");
		}
	}

	public void deleteWishlistByItemID(String itemId) { // delete by item id
		String query = "DELETE FROM wishlist WHERE Item_id LIKE ?";
		PreparedStatement ps = connect.preparedStatement(query);

		try {
			ps.setString(1, itemId);
			ps.executeUpdate();
		} catch (SQLException e) {

			System.out.println("Fail to delete wishlist");
		}
	}

	public List<Wishlist> getItemByUserID(String userId) { // get the wishlist by using user Id
		String query = "SELECT * FROM wishlist WHERE User_id LIKE ?";

		List<Wishlist> wishlists = new ArrayList<Wishlist>();

		try (PreparedStatement ps = connect.preparedStatement(query)) {
			ps.setString(1, userId);

			try (ResultSet rs = ps.executeQuery()) {

				while (rs != null && rs.next()) {
					String wishlist_id = rs.getString("wishlist_id");
					String item_id = rs.getString("Item_id");
					String user_id = rs.getString("User_id");

					wishlists.add(new Wishlist(wishlist_id, item_id, user_id));
				}

			}
		} catch (SQLException e) {
			System.out.println("No Wishlist found");
		}
		return wishlists;
	}

}
