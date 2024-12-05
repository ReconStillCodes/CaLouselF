package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginView extends AuthenticationView {

	private TextField usernameField;
	private PasswordField passwordField;
	private Button loginButton;
	private Scene scene;
	private Hyperlink registerButton;

	public void start(Stage stage) {
		init(stage);

		VBox container = createContainer();
		container.getChildren().addAll(createLabel("Login", 40), createTextFieldContainer(usernameField, "Username"),
				createPasswordFieldContainer(passwordField, "Password"), loginButton,
				createLabel("Dont't have an account?", 14), registerButton);

		VBox.setMargin(loginButton, new Insets(40, 0, 20, 0));

		StackPane stackPane = new StackPane();
		stackPane.getChildren().add(container);

		scene = new Scene(stackPane, getWindowWidth(), getWindowHeight());
		scene.setFill(Color.WHITESMOKE);

		stage.setScene(scene);
		stage.setTitle("Login Page");

	}

	private void init(Stage stage) {
		usernameField = createTextField("Enter your username");
		passwordField = createPasswordField("Enter your password");
		loginButton = createButton("Login");
		registerButton = createHyperlink("Sign Up Now");

		registerButton.setOnAction(event -> goToRegisterView(stage));

	}

	private void goToRegisterView(Stage stage) {

		RegisterView registerView = new RegisterView();
		registerView.start(stage);
	}

}
