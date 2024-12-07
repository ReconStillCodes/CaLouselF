package view;

import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import session.Session;

public class MasterView {

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

}
