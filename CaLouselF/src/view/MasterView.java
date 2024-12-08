package view;

import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import session.Session;

public abstract class MasterView {

	protected abstract ScrollPane initBody();

	protected HBox createNavbar() {

		// Navbar Buyer: Home, Wishlist, History,
		// Navbar Seller: Home, Offers, Add items
		// Navbar Admin: Home, Requests

		Hyperlink homeButton, logoutButton;

		HBox navbar = new HBox(10);
		navbar.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(0), null)));
		navbar.setPadding(new Insets(15, 100, 15, 100));

		homeButton = createLink("Home");
		homeButton.setOnAction(event -> goToHome());

		logoutButton = createLink("Logout");
		logoutButton.setOnAction(event -> logout());

		if (Session.user.getRole().toLowerCase().equals("buyer")) {
			Hyperlink wishlistButton = createLink("Wishlists");
			Hyperlink historyButton = createLink("History");

			navbar.getChildren().addAll(homeButton, wishlistButton, historyButton, logoutButton);

		} else if (Session.user.getRole().toLowerCase().equals("seller")) {
			Hyperlink offersButton = createLink("Offers");
			Hyperlink addItemsButton = createLink("Add Items");

			navbar.getChildren().addAll(homeButton, offersButton, addItemsButton, logoutButton);
		} else {
			Hyperlink requestsButton = createLink("Requests");

			navbar.getChildren().addAll(homeButton, requestsButton, logoutButton);
		}

		return navbar;

	}

	protected Hyperlink createLink(String text) {
		Hyperlink link = new Hyperlink(text);
		link.setTextFill(Color.WHITE);
		link.setFont(Font.font("Poppins", FontWeight.BOLD, 20));
		link.setUnderline(false);
		link.setBorder(Border.EMPTY);
		return link;
	}

	// NavBar Buttons
	protected void goToHome() {
		new HomeView();
	}

	protected void logout() {
		Session.user = null;
		new LoginView();
	}

	// Window Width
	protected Rectangle2D getWindowSize() {
		return Screen.getPrimary().getVisualBounds();
	}

	protected double getWindowWidth() {
		return getWindowSize().getWidth();
	}

	protected double getWindowHeight() {
		return getWindowSize().getHeight();
	}

	// create Body
	protected BorderPane createRoot() {
		HBox navbar = createNavbar();

		BorderPane root = new BorderPane();
		root.setTop(navbar);
		root.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, new CornerRadii(0), null)));

		return root;
	}

	protected VBox createBody() {
		VBox body = new VBox(20);
		body.setMaxWidth(1300);
		return body;
	}

	protected Label createTitle(String text, int size) {
		Label label = new Label(text);
		label.setFont(Font.font("Poppins", FontWeight.BOLD, size));
		return label;
	}

	protected Label createDesc(String text, int size, Color color) {
		Label label = new Label(text);
		label.setFont(Font.font("Poppins", FontWeight.MEDIUM, size));
		label.setTextFill(color);
		return label;
	}

	// Create Item Card
	protected VBox createItemCard(String category, String name, String size, String price) {
		VBox vbox = new VBox(5);
		vbox.setPadding(new Insets(10, 10, 10, 10));
		vbox.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), null)));
		vbox.setEffect(createDropShadow());
		vbox.setMaxWidth(300);
		vbox.setPrefWidth(300);

		Label categoryLabel = createDesc(category + " | " + size, 14, Color.GRAY);
		Label nameLabel = createTitle(name, 20);
		Label priceLabel = createDesc("Rp " + price, 16, Color.RED);

		vbox.getChildren().addAll(categoryLabel, nameLabel, priceLabel);

		return vbox;
	}

	protected Button createButtonCard(String text, Color color) {
		Button button = new Button(text);

		button.setFont(Font.font("Poppins", FontWeight.BOLD, 16));
		button.setPadding(new Insets(5, 15, 5, 15));
		button.setPrefWidth(300);
		button.setTextFill(Color.WHITE);
		button.setBackground(new Background(new BackgroundFill(color, new CornerRadii(10), Insets.EMPTY)));

		return button;
	}

	protected DropShadow createDropShadow() {

		DropShadow shadow = new DropShadow();
		shadow.setRadius(10);
		shadow.setOffsetX(0);
		shadow.setOffsetX(0);
		shadow.setColor(Color.GRAY);

		return shadow;
	}

	protected GridPane createGridPane() {
		GridPane gridPane = new GridPane();
		gridPane.setMaxWidth(1300);
		gridPane.setHgap(33);
		gridPane.setVgap(30);
		return gridPane;
	}

	protected ScrollPane createScrollPane() {
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setFitToWidth(true);
		scrollPane.setPannable(true);
		scrollPane.setPadding(new Insets(1, 0, 0, 0));
		return scrollPane;
	}

}
