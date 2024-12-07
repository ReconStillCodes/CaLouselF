package model;

public class Item {
	private String id, name, price, category, status, wishlist, offer_status, seller_id;

	public Item(String id, String name, String price, String category, String seller_id) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;

		this.status = "Available"; // available or unavailable
		this.wishlist = "Available"; // available or unavailable
		this.offer_status = "Idle"; // idle, pending, accepted
	}

	public Item(String id, String name, String price, String category, String status, String wishlist,
			String offer_status, String seller_id) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
		this.status = status;
		this.wishlist = wishlist;
		this.offer_status = offer_status;
		this.seller_id = seller_id;
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
		return "Item [id=" + id + ", name=" + name + ", price=" + price + ", category=" + category + ", status="
				+ status + ", wishlist=" + wishlist + ", offer_status=" + offer_status + ", seller_id=" + seller_id
				+ "]";
	}

}
