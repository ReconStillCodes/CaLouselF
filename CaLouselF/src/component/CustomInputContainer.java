package component;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CustomInputContainer extends VBox {

	public CustomInputContainer(String text, TextField input) {
		super(5);
		getChildren().addAll(createLabel(text), input);
	}

	private Label createLabel(String text) {
		Label label = new Label(text);
		label.setFont(Font.font("Poppins", FontWeight.BOLD, 16));
		return label;
	}
}
