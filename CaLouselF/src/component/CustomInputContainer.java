package component;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CustomInputContainer extends VBox { // Create An input container

	public CustomInputContainer(String text, TextField input) { // Input container for text Field
		super(5);
		getChildren().addAll(createLabel(text), input);
	}

	public CustomInputContainer(String text, PasswordField input) { // Input container for password field
		super(5);
		getChildren().addAll(createLabel(text), input);
	}

	public CustomInputContainer(String text, TextArea input) {// Input container for text area
		super(5);
		getChildren().addAll(createLabel(text), input);
	}

	private Label createLabel(String text) { // Create custom label
		Label label = new Label(text);
		label.setFont(Font.font("Poppins", FontWeight.BOLD, 16));
		return label;
	}

}
