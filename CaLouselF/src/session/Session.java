package session;

import javafx.stage.Stage;
import model.User;

public class Session {
	public static User user = null; // store login user
	public static Stage stage = null; // store stage

	private static Session session = null;

	public Session() {
	}

	public static Session getSession() { // create a singleton for session
		if (session == null) {
			session = new Session();
		}

		return session;
	}

}
