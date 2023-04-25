package db.dao.impl;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

// In this class we create interface to using Pool of Connections
public class DataSource {
	private static final HikariDataSource dataSource;
	private DataSource() { /* empty */ }

	static {
		try {
			// Getting properties from XML file
			Properties properties = ConfigurationManager.getInstance().getProperties();

			// I don't know is it really needed to me
			Class.forName(properties.getProperty("driver"));

			// Initialise hikari configuration
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(properties.getProperty("url"));
			config.setUsername(properties.getProperty("username"));
			config.setPassword(properties.getProperty("password"));

			// Create new instance of general class
			dataSource = new HikariDataSource(config);
		} catch (Throwable ex) {
			// If we have some troubles with either configuration
			// or reading properties or access to database or sth else
			// then this exception will be generated
			System.err.println("Initial DataSource creation failed.\n" + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static Connection getConnection() {
		try {
			// return connection from pool
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}