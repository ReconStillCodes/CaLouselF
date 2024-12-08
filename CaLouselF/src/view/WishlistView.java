package view;

import java.util.List;

import component.CustomButton;
import component.ItemCard;
import controller.ItemController;
import controller.WishlistController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Item;
import model.Wishlist;
import session.Session;

public class WishlistView extends MasterView {

	private WishlistController wishlistController = new WishlistController();
	private ItemController itemController = new ItemController();
	private GridPane itemContainer;

	public WishlistView() {
		if (!isSessionValid()) {
			new LoginView();
			return;
		}

		initPage();
	}

	@Override
	protected Boolean isSessionValid() {
		if (Session.user == null || !Session.user.getRole().toLowerCase().equals("buyer"))
			return false;
		return true;
	}

	@Override
	protected void initPage() {
		ScrollPane body = initBody();

		root = createRoot();
		root.setCenter(body);

		scene = new Scene(root, WindowWidth, WindowHeight);
		Session.stage.setScene(scene);
		Session.stage.setTitle("Home");

	}

	@Override
	protected ScrollPane initBody() {
		VBox body = createBody(1300);

		initItemCards(wishlistController.viewWishlist(Session.user.getUser_id()));

		Label title;
		if (Session.user.getRole().toLowerCase().equals("seller")) {
			title = createTitle("Seller's Items", 30);
		} else {
			title = createTitle("Available Items", 30);
		}

		body.getChildren().addAll(title, itemContainer);

		StackPane wrapper = createWrapper();
		wrapper.getChildren().add(body);

		ScrollPane scrollPane = createScrollPane();
		scrollPane.setContent(wrapper);

		return scrollPane;
	}

	private void initItemCards(List<Wishlist> wishlists) {
		if (itemContainer != null) {
			itemContainer.getChildren().clear();
		} else {
			itemContainer = createGridPane();
		}

		int columns = 4;
		for (int i = 0; i < wishlists.size(); i++) {
			int row = i / columns;
			int col = i % columns;

			Item item = itemController.getItemByID(wishlists.get(i).getItem_id());

			ItemCard itemCard = new ItemCard(item.getCategory(), item.getName(), item.getSize(), item.getPrice());

			itemCard = createWishlistItemCard(itemCard, wishlists.get(i).getId());

			itemContainer.add(itemCard, col, row);
		}

	}

	private ItemCard createWishlistItemCard(ItemCard itemCard, String wishlist_id) {
		Button removeButton = new CustomButton("Remove", Color.RED);
		removeButton.setOnAction(event -> removeHandler(wishlist_id));

		itemCard.addButton(removeButton);
		itemCard.setMargin(removeButton, new Insets(30, 0, 20, 0));
		return itemCard;
	}

	private void removeHandler(String wishlist_id) {
		wishlistController.removeWishlist(wishlist_id);
		initItemCards(wishlistController.viewWishlist(Session.user.getUser_id()));
	}

}
