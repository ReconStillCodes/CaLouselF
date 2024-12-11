package controller;

import java.util.List;

import database.WishlistDAO;
import model.Wishlist;

public class WishlistController {

	private WishlistDAO wishlistDAO = new WishlistDAO(); // access to wishlist database

	public void addWishlist(String itemId, String userId) { // Create new wishlist
		if (isWishlistExist(itemId, userId)) {
			return;
		}

		Wishlist wishlist = new Wishlist(generateID(), itemId, userId);
		wishlistDAO.insertWishlist(wishlist);
	}

	private boolean isWishlistExist(String itemId, String userId) { // Validate if wishlist already exist or not
		Wishlist wishlist = wishlistDAO.getItemByItemIDandUserID(itemId, userId);
		if (wishlist == null)
			return false;

		return true;
	}

	private String generateID() { // create new id
		String latestID = wishlistDAO.getMaxUserID();

		if (latestID == null || latestID.isEmpty() || latestID.length() <= 0) {
			return "WH001";
		}

		int number = Integer.parseInt(latestID.substring(2)) + 1;

		return String.format("WH%03d", number);
	}

	public void removeWishlist(String id) { // remove wishlist
		wishlistDAO.deleteWishlist(id);
	}

	public void removeWishlistAfterPruchase(String itemId) { // remove wishlist when the item's is purchasedd
		wishlistDAO.deleteWishlistByItemID(itemId);
	}

	public List<Wishlist> viewWishlist(String userId) { // view all user's wishlists
		List<Wishlist> wishlists = wishlistDAO.getItemByUserID(userId);
		return wishlists;
	}

}
