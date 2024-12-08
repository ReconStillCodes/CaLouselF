package view;

import component.CustomButton;
import component.CustomDropShadow;
import component.CustomInputContainer;
import component.CustomTextField;
import controller.ItemController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import session.Session;

public class AddItemView extends MasterView {
	private TextField nameField, categoryField, sizeField, priceField;
	private Label errorLabel;
	private Button cancelButton, submitButton;
	private ItemController itemController = new ItemController();

	public AddItemView() {

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

		if (Session.user.getRole().toLowerCase().equals("seller"))
			return true;

		return false;
	}

	@Override
	protected void initPage() {
		ScrollPane body = initBody();

		root = createRoot();
		root.setCenter(body);

		scene = new Scene(root, WindowWidth, WindowHeight);
		Session.stage.setScene(scene);
		Session.stage.setTitle("Add Item");

	}

	@Override
	protected ScrollPane initBody() {

		Label title = createTitle("Add Item", 30);
		HBox buttonContainer = createButtonContainer();
		initField();

		VBox body = createBody(750);
		body.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), null)));
		body.setPadding(new Insets(15, 15, 15, 15));
		body.setEffect(new CustomDropShadow());

		body.getChildren().addAll(title, new CustomInputContainer("Item's Name", nameField),
				new CustomInputContainer("Item's Category", categoryField),
				new CustomInputContainer("Item's Size", sizeField),
				new CustomInputContainer("Item's Price", priceField), errorLabel, buttonContainer);

		StackPane wrapper = createWrapper();
		wrapper.getChildren().add(body);

		ScrollPane scrollPane = createScrollPane();
		scrollPane.setContent(wrapper);
		return scrollPane;
	}

	private void initField() {
		nameField = new CustomTextField("Enter Item's Name");
		categoryField = new CustomTextField("Enter Item's Category");
		sizeField = new CustomTextField("Enter Item's Name");
		priceField = new CustomTextField("Enter Item's Name");

		errorLabel = createTitle("", 12);
		errorLabel.setTextFill(Color.RED);
	}

	private HBox createButtonContainer() {
		HBox hbox = new HBox(5);

		cancelButton = new CustomButton("Cancel", Color.RED);
		cancelButton.setMaxWidth(150);
		cancelButton.setOnAction(event -> goToHome());

		submitButton = new CustomButton("Add Item", Color.BLACK);
		submitButton.setMaxWidth(150);
		submitButton.setOnAction(event -> uploadItem());

		hbox.setAlignment(Pos.TOP_RIGHT);
		hbox.getChildren().addAll(cancelButton, submitButton);
		return hbox;
	}

	private void goToHome() {
		new HomeView();
	}

	private void uploadItem() {

		boolean isValid = itemController.uploadItem(nameField.getText(), categoryField.getText(), sizeField.getText(),
				priceField.getText());

		if (!isValid) {
			errorLabel.setText("Item's Credential is Invalid");
		} else {
			errorLabel.setText("");
			goToHome();
		}

	}

}
