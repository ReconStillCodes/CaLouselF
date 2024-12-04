package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class LoginPage {

	private VBox vbox;
	private TextField usernameField, phoneField;
	private PasswordField passwordField;
	private Button loginButton;
	private Scene scene;

	public LoginPage(Stage stage) {
		vbox = new VBox(10);
		vbox.setMaxHeight(600);
		vbox.setMaxWidth(500);
		vbox.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), null)));

		vbox.setEffect(createDropShadow());

		Label titleLabel = createTitleLabel("Login");
		vbox.getChildren().add(titleLabel);

		usernameField = new TextField("Username");

		vbox.getChildren().add(usernameField);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(20, 20, 20, 20));

		StackPane stackPane = new StackPane();
		stackPane.getChildren().add(vbox);

		scene = new Scene(stackPane, 1000, 600);
		scene.setFill(Color.WHITESMOKE);

		stage.setScene(scene);
		stage.setTitle("Login Page");
		stage.show();
	}

	private DropShadow createDropShadow() {

		DropShadow shadow = new DropShadow();
		shadow.setRadius(10);
		shadow.setOffsetX(0);
		shadow.setOffsetX(0);
		shadow.setColor(Color.GRAY);

		return shadow;
	}

	private Label createTitleLabel(String text) {
		Label label = new Label(text);
		label.setFont(Font.font("Poppins", FontWeight.BOLD, 40));
		label.setTextAlignment(TextAlignment.CENTER);
		return label;
	}
}
