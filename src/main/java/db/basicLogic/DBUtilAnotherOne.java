package db.basicLogic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBUtilAnotherOne {
	public Connection getConnection() throws SQLException {
		ResourceBundle resource = ResourceBundle.getBundle("database");
		String url = resource.getString("url");
		String driver = resource.getString("driver");
		String user = resource.getString("user");
		String password = resource.getString("password");

		try {
			Class.forName(driver).newInstance();
		} catch (ClassNotFoundException e) {
			throw new SQLException("Driver not loaded!");
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return DriverManager.getConnection(url, user, password);
	}
}
