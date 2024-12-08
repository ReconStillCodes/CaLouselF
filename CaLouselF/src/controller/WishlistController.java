package controller;

import java.util.List;

import database.WishlistDAO;
import model.Wishlist;

public class WishlistController {

	private WishlistDAO wishlistDAO = new WishlistDAO();

	public void addWishlist(String itemId, String userId) {
		if (isWishlistExist(itemId, userId)) {
			return;
		}

		Wishlist wishlist = new Wishlist(generateID(), itemId, userId);
		wishlistDAO.insertWishlist(wishlist);
	}

	private boolean isWishlistExist(String itemId, String userId) {
		Wishlist wishlist = wishlistDAO.getItemByItemIDandUserID(itemId, userId);
		if (wishlist == null)
			return false;

		return true;
	}

	private String generateID() {
		String latestID = wishlistDAO.getMaxUserID();

		if (latestID == null || latestID.isEmpty() || latestID.length() <= 0) {
			return "WH001";
		}

		int number = Integer.parseInt(latestID.substring(2)) + 1;

		return String.format("WH%03d", number);
	}

	public void removeWishlist(String id) {
		wishlistDAO.deleteItem(id);
	}

	public List<Wishlist> viewWishlist(String userId) {
		List<Wishlist> wishlists = wishlistDAO.getItemByUserID(userId);
		return wishlists;
	}

}
