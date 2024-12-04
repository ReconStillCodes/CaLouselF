package controller;

import javafx.stage.Stage;
import view.LoginPage;

public class LoginController {

	Stage stage;
	LoginPage loginPage;

	public LoginController(Stage stage) {
		this.stage = stage;
		loginPage = new LoginPage(stage);

	}
}
