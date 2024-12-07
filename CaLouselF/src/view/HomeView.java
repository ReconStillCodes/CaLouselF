package view;

import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import model.User;
import session.Session;

public class HomeView extends MasterView {
	private Scene scene;

	private Hyperlink homeButton;
	private Hyperlink wishlistButton, historyButton;

	public HomeView() {
		Session.getSession();
		Session.user = new User("UD999", "John Doe", "12341234", "+62123456789", "America", "buyer");
//		if (Session.user == null) {
//			new LoginView();
//			return;
//		}

		init();

		HBox navbar = createNavbar();

		BorderPane root = new BorderPane();
		root.setTop(navbar);

		scene = new Scene(root, getWindowWidth(), getWindowHeight());
		Session.stage.setScene(scene);
		Session.stage.setTitle("Home");
	}

	private void init() {
		homeButton = createLink("CaLouselF");
		wishlistButton = createLink("Whishlists");
		historyButton = createLink("History");
	}

	// Nanti di refactor

}
