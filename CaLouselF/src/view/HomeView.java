package view;

import java.util.List;

import controller.ItemController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
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
	private Scene scene;
	private BorderPane root;
	private ItemController itemController = new ItemController();
	private TextField searchField;
	private Button searchButton;
	private GridPane browseContainer;

	public HomeView() {
		Session.getSession();
		Session.user = new User("UD001", "John Doe", "12341234", "+62123456789", "America", "Seller");

		if (Session.user == null) {
			new LoginView();
			return;
		}

		ScrollPane body = initBody();

		root = createRoot();
		root.setCenter(body);

		scene = new Scene(root, getWindowWidth(), getWindowHeight());
		Session.stage.setScene(scene);
		Session.stage.setTitle("Home");
	}

	@Override
	protected ScrollPane initBody() {
		VBox body = createBody();

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

		StackPane wrapper = new StackPane(body);
		wrapper.setPadding(new Insets(20, 0, 50, 0));
		wrapper.setAlignment(Pos.TOP_CENTER);

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

		searchButton = createButtonCard("Search", Color.BLACK);
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

			VBox itemCard = createItemCard(items.get(i).getCategory(), items.get(i).getName(), items.get(i).getSize(),
					items.get(i).getPrice());

			if (Session.user.getRole().toLowerCase().equals("buyer")) {
				itemCard = createBuyerItemCard(itemCard, items.get(i).getId());
			} else if (Session.user.getRole().toLowerCase().equals("seller")) {
				itemCard = createSellerItemCard(itemCard, items.get(i).getId());
			}

			browseContainer.add(itemCard, col, row);
		}

	}

	private VBox createBuyerItemCard(VBox vbox, String item_id) {
		Button buyButton = createButtonCard("Buy", Color.BLACK);
		Button wishButton = createButtonCard("Wish", Color.CORNFLOWERBLUE);
		Button offerButton = createButtonCard("Offer", Color.BLACK);

		HBox buttonContainer = new HBox(5);
		buttonContainer.setMaxWidth(300);
		buttonContainer.getChildren().addAll(buyButton, wishButton);

		TextField offerField = new TextField();
		offerField.setPromptText("Enter your Offer");

		vbox.getChildren().addAll(buttonContainer, offerField, offerButton);
		VBox.setMargin(buttonContainer, new Insets(30, 0, 20, 0));
		return vbox;
	}

	private VBox createSellerItemCard(VBox vbox, String item_id) {
		Button editButton = createButtonCard("Edit", Color.BLACK);
		Button deleteButton = createButtonCard("Delete", Color.RED);

		deleteButton.setOnAction(event -> deleteHandler(item_id));

		HBox buttonContainer = new HBox(5);
		buttonContainer.setMaxWidth(300);
		buttonContainer.getChildren().addAll(editButton, deleteButton);

		vbox.getChildren().addAll(buttonContainer);
		VBox.setMargin(buttonContainer, new Insets(30, 0, 0, 0));
		return vbox;
	}

	private void deleteHandler(String item_id) {
		itemController.deleteItem(item_id);
		initItemCards(itemController.viewItem());
	}

}
