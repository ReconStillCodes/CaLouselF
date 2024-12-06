package view;

import controller.UserController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class RegisterView extends AuthenticationView {
	private TextField usernameField, phoneField;
	private PasswordField passwordField;
	private TextArea addressField;
	private Button registerButton;
	private Hyperlink loginButton;
	private Scene scene;
	private ToggleGroup roleGroup;
	private RadioButton buyerRadio, sellerRadio;
	private Label errorLabel;

	private UserController userController = new UserController();

	public RegisterView(Stage stage) {

		init(stage);

		VBox container = createContainer();

		container.getChildren().addAll(createLabel("Register", 40), createTextFieldContainer(usernameField, "Username"),
				createPasswordFieldContainer(passwordField, "Password"),
				createTextFieldContainer(phoneField, "Phone Number"), createAddressContainer(addressField, "Address"),
				createRoleGroupContainer("Roles"), errorLabel, registerButton,
				createLabel("Already have an account?", 14), loginButton);

		StackPane stackPane = new StackPane();
		stackPane.getChildren().add(container);

		scene = new Scene(stackPane, getWindowWidth(), getWindowHeight());
		scene.setFill(Color.WHITESMOKE);

		stage.setScene(scene);
		stage.setTitle("Register Page");
	}

	private void init(Stage stage) {
		usernameField = createTextField("Enter your username");
		passwordField = createPasswordField("Enter your password");
		phoneField = createTextField("Enter your phone number");
		addressField = createTextArea("Enter your address");
		registerButton = createButton("Register");
		loginButton = createHyperlink("Sign In Now");
		errorLabel = createLabel("", 12);
		errorLabel.setTextFill(Color.RED);

		registerButton.setOnAction(event -> register(usernameField.getText(), passwordField.getText(),
				phoneField.getText(), addressField.getText(), getSelectedRole(), stage));
		loginButton.setOnAction(event -> goToLoginView(stage));
	}

	private void register(String username, String password, String phone, String address, String role, Stage stage) {

		boolean isValid = userController.register(username, password, phone, address, role);

		if (!isValid) {
			errorLabel.setText("Invalid Credential!");
		} else {
			errorLabel.setText("");
			new LoginView(stage);
		}
	}

	private void goToLoginView(Stage stage) {
		new LoginView(stage);
	}

	private String getSelectedRole() {
		Toggle selectedToggle = roleGroup.getSelectedToggle();

		if (selectedToggle != null) {
			RadioButton selectedRB = (RadioButton) selectedToggle;
			return selectedRB.getText();
		}

		return null;
	}

	private VBox createAddressContainer(TextArea textArea, String text) {
		VBox vbox = new VBox(5);
		vbox.getChildren().addAll(createLabel(text, 14), textArea);
		return vbox;
	}

	private TextArea createTextArea(String text) {
		TextArea textArea = new TextArea();
		textArea.setPromptText(text);
		textArea.setFont(Font.font("Poppins", 16));
		return textArea;
	}

	private VBox createRoleGroupContainer(String text) {
		VBox vbox = new VBox(5);

		HBox hbox = new HBox(20);

		roleGroup = new ToggleGroup();
		buyerRadio = new RadioButton("Buyer");
		sellerRadio = new RadioButton("Seller");
		buyerRadio.setToggleGroup(roleGroup);
		sellerRadio.setToggleGroup(roleGroup);

		hbox.getChildren().addAll(sellerRadio, buyerRadio);

		vbox.getChildren().addAll(createLabel(text, 14), hbox);

		return vbox;
	}

}
