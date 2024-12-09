package view;

import java.util.List;

import component.ItemCard;
import controller.ItemController;
import controller.TransactionController;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.Item;
import model.Transaction;
import session.Session;

public class HistoryView extends MasterView {

	private ItemController itemController = new ItemController();
	private TransactionController transactionController = new TransactionController();

	private GridPane itemContainer;

	public HistoryView() {
		Session.getSession();

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
		Session.stage.setTitle("History");

	}

	@Override
	protected ScrollPane initBody() {
		VBox body = createBody(1300);

		initItemCards(transactionController.viewHistory(Session.user.getUser_id()));

		Label title = createTitle("Available Items", 30);

		body.getChildren().addAll(title, itemContainer);

		StackPane wrapper = createWrapper();
		wrapper.getChildren().add(body);

		ScrollPane scrollPane = createScrollPane();
		scrollPane.setContent(wrapper);

		return scrollPane;
	}

	private void initItemCards(List<Transaction> transactions) {
		if (itemContainer != null) {
			itemContainer.getChildren().clear();
		} else {
			itemContainer = createGridPane();
		}

		int columns = 4;
		for (int i = 0; i < transactions.size(); i++) {
			int row = i / columns;
			int col = i % columns;

			Item item = itemController.getItemByID(transactions.get(i).getItem_id());

			ItemCard itemCard = new ItemCard(item.getCategory(), item.getName(), item.getSize(), item.getPrice());

			Label transactionID = createTitle("Transaction ID: " + transactions.get(i).getId(), 14);
			itemCard.addLabel(transactionID);
			itemContainer.add(itemCard, col, row);
		}

	}

}
