package view;

import java.util.List;

import component.CustomButton;
import component.CustomTextField;
import component.ItemCard;
import controller.ItemController;
import controller.TransactionController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Item;
import session.Session;

public class RequestView extends MasterView {

	private ItemController itemController = new ItemController();
	private TransactionController transactionController = new TransactionController();

	private GridPane itemContainer;

	public RequestView() {
		Session.getSession();

		if (!isSessionValid()) {
			new LoginView();
			return;
		}

		initPage();
	}

	@Override
	protected Boolean isSessionValid() {
		if (Session.user == null || !Session.user.getRole().toLowerCase().equals("admin"))
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
		Session.stage.setTitle("Request");

	}

	@Override
	protected ScrollPane initBody() {
		VBox body = createBody(1300);

		initItemCards(itemController.viewRequestItem());

		Label title = createTitle("Available Items", 30);

		body.getChildren().addAll(title, itemContainer);

		StackPane wrapper = createWrapper();
		wrapper.getChildren().add(body);

		ScrollPane scrollPane = createScrollPane();
		scrollPane.setContent(wrapper);

		return scrollPane;
	}

	private void initItemCards(List<Item> items) {
		if (itemContainer != null) {
			itemContainer.getChildren().clear();
		} else {
			itemContainer = createGridPane();
		}

		int columns = 4;
		for (int i = 0; i < items.size(); i++) {
			int row = i / columns;
			int col = i % columns;

			Item item = items.get(i);

			ItemCard itemCard = new ItemCard(item.getCategory(), item.getName(), item.getSize(), item.getPrice());
			itemCard = initRequestItemCard(itemCard, item.getId(), item.getSeller_id());
			itemContainer.add(itemCard, col, row);
		}

	}

	private ItemCard initRequestItemCard(ItemCard itemCard, String item_id, String seller_id) {
		Label sellerID = createTitle("Seller ID: " + seller_id, 14);
		itemCard.addLabel(sellerID);

		TextField declineField = new CustomTextField("Enter your reason to decline");
		Button acceptButton = new CustomButton("Accept", Color.BLACK);
		Button declineButton = new CustomButton("Decline", Color.RED);

		acceptButton.setOnAction(event -> acceptHandler(item_id));
		declineButton.setOnAction(event -> declineHandler(declineField.getText(), item_id));

		itemCard.addTextField(declineField);
		itemCard.addButton(declineButton);
		itemCard.addButton(acceptButton);
		itemCard.setMargin(declineField, new Insets(30, 0, 0, 0));
		return itemCard;
	}

	private void acceptHandler(String item_id) {
		itemController.acceptItem(item_id);
		initItemCards(itemController.viewRequestItem());
	}

	private void declineHandler(String reason, String item_id) {
		itemController.declineItem(reason, item_id);
		initItemCards(itemController.viewRequestItem());
	}

}
