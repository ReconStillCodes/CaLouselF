package main;

import javafx.application.Application;
import javafx.stage.Stage;
import session.Session;
import view.LoginView;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Session.getSession();
		Session.stage = primaryStage;
		new LoginView();
		primaryStage.show();
	}

}
