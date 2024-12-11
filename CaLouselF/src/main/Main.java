package main;

import javafx.application.Application;
import javafx.stage.Stage;
import session.Session;
import view.HomeView;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Session.getSession(); // create a session
		Session.stage = primaryStage; // send primary stage to session
		new HomeView(); // open home
		primaryStage.show(); // show stage
	}

}
