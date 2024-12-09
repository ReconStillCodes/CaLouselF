package view;

import component.CustomDropShadow;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;

public abstract class AuthenticationView {

	protected abstract void init();

	protected abstract void initPage();

	protected VBox createContainer() {

		VBox vbox = new VBox(10);
		vbox.setMaxHeight(600);
		vbox.setMaxWidth(500);
		vbox.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), null)));
		vbox.setEffect(new CustomDropShadow());
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(20, 40, 20, 40));

		return vbox;
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
