package model;

public class Wishlist {

	private String id, item_id, user_id;

	public Wishlist(String id, String item_id, String user_id) {
		super();
		this.id = id;
		this.item_id = item_id;
		this.user_id = user_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

}
