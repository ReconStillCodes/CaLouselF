package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;

public class AuthenticationView {

	protected VBox createContainer() {

		VBox vbox = new VBox(10);
		vbox.setMaxHeight(600);
		vbox.setMaxWidth(500);
		vbox.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), null)));
		vbox.setEffect(createDropShadow());
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(20, 40, 20, 40));

		return vbox;
	}

	protected DropShadow createDropShadow() {

		DropShadow shadow = new DropShadow();
		shadow.setRadius(10);
		shadow.setOffsetX(0);
		shadow.setOffsetX(0);
		shadow.setColor(Color.GRAY);

		return shadow;
	}

	protected VBox createTextFieldContainer(TextField textField, String text) {
		VBox vbox = new VBox(5);
		vbox.getChildren().addAll(createLabel(text, 14), textField);
		return vbox;
	}

	protected VBox createPasswordFieldContainer(PasswordField passwordField, String text) {
		VBox vbox = new VBox(5);
		vbox.getChildren().addAll(createLabel(text, 14), passwordField);
		return vbox;
	}

	protected TextField createTextField(String text) {
		TextField textField = new TextField();
		textField.setPromptText(text);
		textField.setFont(Font.font("Poppins", 16));
		textField.setPadding(new Insets(5, 10, 5, 10));
		return textField;
	}

	protected PasswordField createPasswordField(String text) {
		PasswordField passwordField = new PasswordField();
		passwordField.setPromptText(text);
		passwordField.setFont(Font.font("Poppins", 16));
		passwordField.setPadding(new Insets(5, 10, 5, 10));
		return passwordField;
	}

	protected Button createButton(String text) {
		Button button = new Button(text);

		button.setFont(Font.font("Poppins", FontWeight.BOLD, 16));
		button.setPadding(new Insets(5, 15, 5, 15));
		button.setPrefWidth(200);
		button.setTextFill(Color.WHITE);
		button.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(15), Insets.EMPTY)));

		return button;
	}

	protected Label createLabel(String text, int size) {
		Label label = new Label(text);
		label.setFont(Font.font("Poppins", FontWeight.BOLD, size));
		return label;
	}

	protected Hyperlink createHyperlink(String text) {
		Hyperlink link = new Hyperlink(text);
		link.setFont(Font.font("Poppins", FontWeight.BOLD, 14));
		return link;
	}

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
