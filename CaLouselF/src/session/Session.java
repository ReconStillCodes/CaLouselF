package session;

import javafx.stage.Stage;
import model.User;

public class Session {
	public static User user = null;
	public static Stage stage = null;

	private static Session session = null;

	public Session() {
	}

	public static Session getSession() {
		if (session == null) {
			session = new Session();
		}

		return session;
	}

}
