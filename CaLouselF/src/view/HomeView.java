package view;

import java.util.List;

import controller.ItemController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.Item;
import model.User;
import session.Session;

public class HomeView extends MasterView {
	private Scene scene;
	private BorderPane root;
	private ItemController itemController = new ItemController();

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

		GridPane browseContainer = createGridPane();

		List<Item> items = itemController.viewItem();
		int columns = 4;
		for (int i = 0; i < items.size(); i++) {
			int row = i / columns;
			int col = i % columns;

			browseContainer.add(createItemCard(items.get(i).getCategory(), items.get(i).getName(),
					items.get(i).getSize(), items.get(i).getPrice()), col, row);
		}

		Label title;
		if (Session.user.getRole().toLowerCase().equals("seller")) {
			title = createTitle("Seller's Items", 30);
		} else {
			title = createTitle("Available Items", 30);
		}

		body.getChildren().addAll(title, browseContainer);

		StackPane wrapper = new StackPane(body);
		wrapper.setPadding(new Insets(20, 0, 50, 0));
		wrapper.setAlignment(Pos.TOP_CENTER);

		ScrollPane scrollPane = createScrollPane();
		scrollPane.setContent(wrapper);

		return scrollPane;
	}

}
