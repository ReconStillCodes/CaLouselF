package main;

import controller.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setMaximized(true);

		new LoginController(primaryStage);

	}

}
