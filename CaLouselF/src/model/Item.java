package model;

public class Item {
	private String id, name, price, size, category, status, wishlist, offer_status, seller_id, offer_price,
			offer_user_id;

	public Item(String id, String name, String price, String size, String category, String seller_id) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.size = size;
		this.category = category;
		this.seller_id = seller_id;

		this.status = "Pending"; // available, pending, unavailable
		this.wishlist = "Available"; // available or unavailable
		this.offer_status = "Unlisted"; // Unlisted, pending, accepted
		this.offer_price = "0";
		this.offer_user_id = null;
	}

	public Item(String id, String name, String price, String size, String category, String status, String wishlist,
			String offer_status, String seller_id, String offer_price, String offer_user_id) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.size = size;
		this.category = category;
		this.status = status;
		this.wishlist = wishlist;
		this.offer_status = offer_status;
		this.seller_id = seller_id;
		this.offer_price = offer_price;
		this.offer_user_id = offer_user_id;
	}

	public String getOffer_price() {
		return offer_price;
	}

	public void setOffer_price(String offer_price) {
		this.offer_price = offer_price;
	}

	public String getOffer_user_id() {
		return offer_user_id;
	}

	public void setOffer_user_id(String offer_user_id) {
		this.offer_user_id = offer_user_id;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWishlist() {
		return wishlist;
	}

	public void setWishlist(String wishlist) {
		this.wishlist = wishlist;
	}

	public String getOffer_status() {
		return offer_status;
	}

	public void setOffer_status(String offer_status) {
		this.offer_status = offer_status;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", price=" + price + ", size=" + size + ", category=" + category
				+ ", status=" + status + ", wishlist=" + wishlist + ", offer_status=" + offer_status + ", seller_id="
				+ seller_id + ", offer_price=" + offer_price + ", offer_user_id=" + offer_user_id + "]";
	}

}
