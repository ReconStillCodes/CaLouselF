package main;

import javafx.application.Application;
import javafx.stage.Stage;
import view.RegisterView;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		RegisterView registerView = new RegisterView();
		registerView.start(primaryStage);

		primaryStage.show();
	}

}
