package component;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CustomButton extends Button { // Create a custom button for the app
	public CustomButton(String text, Color color) {
		super(text);
		setFont(Font.font("Poppins", FontWeight.BOLD, 16));
		setPadding(new Insets(5, 15, 5, 15));
		setPrefWidth(300);
		setTextFill(Color.WHITE);
		setBackground(new Background(new BackgroundFill(color, new CornerRadii(10), Insets.EMPTY)));

	}
}
