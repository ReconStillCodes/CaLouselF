package session;

import javafx.stage.Stage;
import model.User;

public class Session {
	public static User user;
	public static Stage stage;

	private static Session session = null;

	public static Session getSession() {
		if (session == null)
			session = new Session();

		return session;
	}

}
