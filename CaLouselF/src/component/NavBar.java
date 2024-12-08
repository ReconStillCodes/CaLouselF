package component;

import javafx.geometry.Insets;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import session.Session;
import view.AddItemView;
import view.HomeView;
import view.LoginView;
import view.WishlistView;

public class NavBar extends HBox {

	Hyperlink homeButton, logoutButton;

	public NavBar() {
		// Navbar Buyer: Home, Wishlist, History,
		// Navbar Seller: Home, Offers, Add items
		// Navbar Admin: Home, Requests

		super(10);
		setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(0), null)));
		setPadding(new Insets(15, 100, 15, 100));

		homeButton = createLink("Home");
		homeButton.setOnAction(event -> goToHome());

		logoutButton = createLink("Logout");
		logoutButton.setOnAction(event -> logout());

		if (Session.user.getRole().toLowerCase().equals("buyer")) {
			navbarBuyer();
		} else if (Session.user.getRole().toLowerCase().equals("seller")) {
			navbarSeller();
		} else {
			navbarAdmin();
		}

	}

	private void navbarBuyer() {
		Hyperlink wishlistButton = createLink("Wishlists");
		Hyperlink historyButton = createLink("History");

		wishlistButton.setOnAction(event -> goToWishlist());

		getChildren().addAll(homeButton, wishlistButton, historyButton, logoutButton);
	}

	private void navbarSeller() {
		Hyperlink offersButton = createLink("Offers");
		Hyperlink addItemsButton = createLink("Add Items");

		addItemsButton.setOnAction(event -> goToAddItem());

		getChildren().addAll(homeButton, offersButton, addItemsButton, logoutButton);
	}

	private void navbarAdmin() {
		Hyperlink requestsButton = createLink("Requests");

		getChildren().addAll(homeButton, requestsButton, logoutButton);
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

	protected void goToAddItem() {
		new AddItemView();
	}

	protected void goToWishlist() {
		new WishlistView();
	}
}
