package view;

import java.util.List;

import component.CustomButton;
import component.ItemCard;
import controller.ItemController;
import controller.TransactionController;
import controller.WishlistController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.Item;
import model.User;
import session.Session;

public class HomeView extends MasterView {

	private ItemController itemController = new ItemController();
	private WishlistController wishlistController = new WishlistController();
	private TransactionController transactionController = new TransactionController();
	private TextField searchField;
	private Button searchButton;
	private GridPane browseContainer;

	public HomeView() {
		Session.getSession();
		Session.user = new User("UD002", "John Doe", "12341234", "+62123456789", "America", "Buyer");

		if (!isSessionValid()) {
			new LoginView();
			return;
		}

		initPage();
	}

	@Override
	protected Boolean isSessionValid() {
		if (Session.user == null)
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

		initItemCards(itemController.viewItem());

		Label title;
		if (Session.user.getRole().toLowerCase().equals("seller")) {
			title = createTitle("Seller's Items", 30);
		} else {
			title = createTitle("Available Items", 30);
		}

		// Search Bar
		HBox searchContainer = createSearchContainer();

		body.getChildren().addAll(title, searchContainer, browseContainer);

		StackPane wrapper = createWrapper();
		wrapper.getChildren().add(body);

		ScrollPane scrollPane = createScrollPane();
		scrollPane.setContent(wrapper);

		return scrollPane;
	}

	private HBox createSearchContainer() {
		HBox searchContainer = new HBox(10);
		searchField = new TextField();
		searchField.setPromptText("Search Items");
		searchField.setFont(Font.font("Poppins", 16));
		searchField.setPadding(new Insets(5, 10, 5, 10));
		searchField.setPrefWidth(1300);

		searchButton = new CustomButton("Search", Color.BLACK);
		searchButton.setMinWidth(100);
		searchButton.setOnAction(event -> search(searchField.getText()));

		searchContainer.getChildren().addAll(searchField, searchButton);
		return searchContainer;
	}

	private void search(String text) {

		initItemCards(itemController.browseItem(text));

	}

	private void initItemCards(List<Item> items) {
		if (browseContainer != null) {
			browseContainer.getChildren().clear();
		} else {
			browseContainer = createGridPane();
		}

		int columns = 4;
		for (int i = 0; i < items.size(); i++) {
			int row = i / columns;
			int col = i % columns;

			ItemCard itemCard = new ItemCard(items.get(i).getCategory(), items.get(i).getName(), items.get(i).getSize(),
					items.get(i).getPrice());

			if (Session.user.getRole().toLowerCase().equals("buyer")) {
				itemCard = createBuyerItemCard(itemCard, items.get(i).getId());
			} else if (Session.user.getRole().toLowerCase().equals("seller")) {
				itemCard = createSellerItemCard(itemCard, items.get(i).getId());
			}

			browseContainer.add(itemCard, col, row);
		}

	}

	private ItemCard createBuyerItemCard(ItemCard itemCard, String item_id) {
		Button buyButton = new CustomButton("Buy", Color.BLACK);
		Button wishButton = new CustomButton("Wish", Color.CORNFLOWERBLUE);
		Button offerButton = new CustomButton("Offer", Color.BLACK);

		buyButton.setOnAction(event -> purchaseHandler(item_id));
		wishButton.setOnAction(event -> wishlistHandler(item_id));

		HBox buttonContainer = new HBox(5);
		buttonContainer.setMaxWidth(300);
		buttonContainer.getChildren().addAll(buyButton, wishButton);

		TextField offerField = new TextField();
		offerField.setPromptText("Enter your Offer");

		itemCard.addHBox(buttonContainer);
		itemCard.addTextField(offerField);
		itemCard.addButton(offerButton);

		itemCard.setMargin(buttonContainer, new Insets(30, 0, 20, 0));
		return itemCard;
	}

	private ItemCard createSellerItemCard(ItemCard itemCard, String item_id) {
		Button editButton = new CustomButton("Edit", Color.BLACK);
		Button deleteButton = new CustomButton("Delete", Color.RED);

		editButton.setOnAction(event -> editHandler(item_id));
		deleteButton.setOnAction(event -> deleteHandler(item_id));

		HBox buttonContainer = new HBox(5);
		buttonContainer.setMaxWidth(300);
		buttonContainer.getChildren().addAll(editButton, deleteButton);

		itemCard.addHBox(buttonContainer);
		itemCard.setMargin(buttonContainer, new Insets(30, 0, 0, 0));
		return itemCard;
	}

	private void editHandler(String item_id) {
		new EditItemView(item_id);
	}

	private void deleteHandler(String item_id) {
		itemController.deleteItem(item_id);
		initItemCards(itemController.viewItem());
	}

	private void wishlistHandler(String item_id) {
		wishlistController.addWishlist(item_id, Session.user.getUser_id());
	}

	private void purchaseHandler(String item_id) {
		transactionController.purchaseItem(Session.user.getUser_id(), item_id);
		itemController.updatePurchaseItem(item_id);

		initItemCards(itemController.viewItem());
	}

}
