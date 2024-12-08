package view;

import component.NavBar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;

public abstract class MasterView {

	protected final double WindowWidth = Screen.getPrimary().getVisualBounds().getWidth();
	protected final double WindowHeight = Screen.getPrimary().getVisualBounds().getHeight();

	protected BorderPane root;
	protected Scene scene;

	protected abstract Boolean isSessionValid();

	protected abstract void initPage();

	protected abstract ScrollPane initBody();

	// create Body
	protected BorderPane createRoot() {
		HBox navbar = new NavBar();

		BorderPane root = new BorderPane();
		root.setTop(navbar);
		root.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, new CornerRadii(0), null)));

		return root;
	}

	protected VBox createBody(int maxWidth) {
		VBox body = new VBox(20);
		body.setMaxWidth(maxWidth);
		return body;
	}

	protected Label createTitle(String text, int size) {
		Label label = new Label(text);
		label.setFont(Font.font("Poppins", FontWeight.BOLD, size));
		return label;
	}

	protected GridPane createGridPane() {
		GridPane gridPane = new GridPane();
		gridPane.setMaxWidth(1300);
		gridPane.setHgap(33);
		gridPane.setVgap(30);
		return gridPane;
	}

	protected ScrollPane createScrollPane() {
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setFitToWidth(true);
		scrollPane.setPannable(true);
		scrollPane.setPadding(new Insets(1, 0, 0, 0));
		return scrollPane;
	}

	protected StackPane createWrapper() {
		StackPane wrapper = new StackPane();
		wrapper.setPadding(new Insets(20, 0, 50, 0));
		wrapper.setAlignment(Pos.TOP_CENTER);
		return wrapper;
	}

}
