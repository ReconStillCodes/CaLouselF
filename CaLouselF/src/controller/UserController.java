package controller;

import database.UserDAO;
import model.User;
import session.Session;

public class UserController {

	private final UserDAO userDAO = new UserDAO();

	public Boolean login(String username, String password) {
		if (!checkInputEmpty(username) || !checkInputEmpty(password))
			return false;

		User user = userDAO.getUserByUsernameAndPassword(username, password);
		if (user == null)
			return false;

		Session.getSession();
		Session.user = user;
		return true;
	}

	public Boolean register(String username, String password, String phone, String address, String role) {
		boolean isValid = checkAccountValidation(username, password, phone, address, role);

		if (!isValid) {

			return false;
		}

		User user = new User(generateID(), username, password, phone, address, role);
		System.out.println(user.toString());
		userDAO.insertUser(user);
		return true;
	}

	public boolean checkAccountValidation(String username, String password, String phone, String address, String role) {
		if (!checkUsernameValidation(username))
			return false;

		if (!checkPasswordValidation(password))
			return false;

		if (!checkPhoneValidation(phone))
			return false;

		if (!checkInputEmpty(address))
			return false;

		if (!checkRoleValidation(role))
			return false;

		return true;
	}

	private boolean checkUsernameValidation(String username) {
		if (!checkInputEmpty(username))
			return false;

		if (username.length() < 3)
			return false;

		User user = userDAO.getUserByUsername(username);

		if (user != null) {
			return false;
		}

		// Redirect
		// Buat Session

		return true;
	}

	private boolean checkPasswordValidation(String password) {
		if (!checkInputEmpty(password))
			return false;

		if (password.length() < 8)
			return false;

		if (!password.matches(".*[!@#$%^&*].*"))
			return false;

		return true;
	}

	private boolean checkPhoneValidation(String phone) {
		if (!checkInputEmpty(phone) || phone.length() != 12)
			return false;

		if (!phone.contains("+62"))
			return false;

		for (int i = 3; i < 12; i++) {
			if (!Character.isDigit(phone.charAt(i)))
				return false;
		}

		return true;
	}

	private boolean checkRoleValidation(String role) {
		if (!checkInputEmpty(role))
			return false;

		System.out.println(role);

		if (!role.toLowerCase().equals("seller") && !role.toLowerCase().equals("buyer"))
			return false;

		System.out.println(role);

		return true;
	}

	private boolean checkInputEmpty(String str) {
		if (str == null || str.isEmpty() || str.isBlank() || str.length() <= 0)
			return false;

		return true;
	}

	private String generateID() {
		String latestID = userDAO.getMaxUserID();

		if (!checkInputEmpty(latestID)) {
			return "UD001";
		}

		int number = Integer.parseInt(latestID.substring(2)) + 1;

		return String.format("UD%03d", number);
	}
}
