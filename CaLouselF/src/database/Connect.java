package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
	private final String USERNAME = "root";
	private final String PASSWORD = "";
	private final String DATABASE = "calouselif";
	private final String HOST = "localhost:3306";

	private final String CONNECTION = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);

	private static Connect connect = null;

	public ResultSet rs;
	public ResultSetMetaData rsm;

	private Connection con;
	private Statement st;

	private Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
			st = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connect getInstance() { // Singleton for connection
		if (connect == null)
			connect = new Connect();
		return connect;
	}

	public ResultSet execQuery(String Query) { // Select from database
		rs = null;
		try {
			rs = st.executeQuery(Query);
			rsm = rs.getMetaData();
		} catch (SQLException e) {
			System.out.println("No Data Selected");
		}

		return rs;
	}

	public void execUpdate(String Query) { // Modified database by insert, update, or delete
		try {
			st.executeUpdate(Query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public PreparedStatement preparedStatement(String query) {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(query);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return ps;
	}
}
