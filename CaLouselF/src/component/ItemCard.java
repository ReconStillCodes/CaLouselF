package component;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ItemCard extends VBox {

	public ItemCard(String category, String name, String size, String price) {
		super(5);
		setPadding(new Insets(10, 10, 10, 10));
		setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), null)));
		setEffect(new CustomDropShadow());
		setMaxWidth(300);
		setPrefWidth(300);

		Label categoryLabel = createDesc(category + " | " + size, 14, Color.GRAY);
		Label nameLabel = createTitle(name, 20);
		Label priceLabel = createDesc("Rp " + price, 16, Color.RED);

		getChildren().addAll(categoryLabel, nameLabel, priceLabel);

	}

	private Label createTitle(String text, int size) {
		Label label = new Label(text);
		label.setFont(Font.font("Poppins", FontWeight.BOLD, size));
		return label;
	}

	private Label createDesc(String text, int size, Color color) {
		Label label = new Label(text);
		label.setFont(Font.font("Poppins", FontWeight.MEDIUM, size));
		label.setTextFill(color);
		return label;
	}

	public void addButton(Button button) {
		getChildren().addAll(button);
	}

	public void addHBox(HBox hbox) {
		getChildren().addAll(hbox);
	}

	public void addTextField(TextField textField) {
		getChildren().addAll(textField);
	}

}
