package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/TaskManager";
	private static final String JDBC_USER = "TaskManager";
	private static final String JDBC_PASSWORD = "ABC-11abc22";
	private static Connection instance = null;

	private DBConnection() {
	}

	public static Connection getConnection()
			throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (instance == null) {
			Class.forName("com.mysql.jdbc.Driver");
			instance = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
		}
		return instance;
	}
}