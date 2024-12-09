package model;

public class Transaction {

	private String id, user_id, item_id;

	public Transaction(String id, String user_id, String item_id) {
		super();
		this.user_id = user_id;
		this.item_id = item_id;
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", user_id=" + user_id + ", item_id=" + item_id + "]";
	}

}
