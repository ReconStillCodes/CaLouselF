package component;

import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class CustomTextField extends TextField { // Create custome Text field

	public CustomTextField(String text) {
		setPromptText(text);
		setFont(Font.font("Poppins", 16));
		setPadding(new Insets(5, 10, 5, 10));
	}
}
