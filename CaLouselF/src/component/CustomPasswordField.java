package component;

import javafx.geometry.Insets;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Font;

public class CustomPasswordField extends PasswordField { // Create custom password field

	public CustomPasswordField(String text) {
		setPromptText(text);
		setFont(Font.font("Poppins", 16));
		setPadding(new Insets(5, 10, 5, 10));
	}

}
