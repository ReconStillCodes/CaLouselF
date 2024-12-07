package view;

import controller.UserController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import session.Session;

public class LoginView extends AuthenticationView {

	private TextField usernameField;
	private PasswordField passwordField;
	private Button loginButton;
	private Scene scene;
	private Hyperlink registerButton;
	private Label errorLabel;

	private UserController userController = new UserController();

	public LoginView() {
		init();

		VBox container = createContainer();
		container.getChildren().addAll(createLabel("Login", 40), createTextFieldContainer(usernameField, "Username"),
				createPasswordFieldContainer(passwordField, "Password"), errorLabel, loginButton,
				createLabel("Dont't have an account?", 14), registerButton);

		VBox.setMargin(loginButton, new Insets(40, 0, 20, 0));

		StackPane stackPane = new StackPane();
		stackPane.getChildren().add(container);

		scene = new Scene(stackPane, getWindowWidth(), getWindowHeight());
		scene.setFill(Color.WHITESMOKE);

		Session.stage.setScene(scene);
		Session.stage.setTitle("Login Page");

	}

	private void init() {
		usernameField = createTextField("Enter your username");
		passwordField = createPasswordField("Enter your password");
		loginButton = createButton("Login");
		registerButton = createHyperlink("Sign Up Now");
		errorLabel = createLabel("", 12);
		errorLabel.setTextFill(Color.RED);

		loginButton.setOnAction(event -> login(usernameField.getText(), passwordField.getText()));
		registerButton.setOnAction(event -> goToRegisterView());

	}

	private void login(String username, String password) {
		Boolean isValid = userController.login(username, password);

		if (!isValid) {
			errorLabel.setText("Invalid Credential");
		} else {
			errorLabel.setText("");
		}
	}

	private void goToRegisterView() {

		new RegisterView();

	}

}
