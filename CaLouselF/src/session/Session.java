package session;

import model.User;

public class Session {
	public static User user;

	private static Session session = null;

	public static Session getSession() {
		if (session == null)
			session = new Session();

		return session;
	}

}
