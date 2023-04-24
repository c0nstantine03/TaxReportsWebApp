package db.dao.impl;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {
	private static final HikariDataSource dataSource;
	private DataSource() { /* empty */ }

	static {
		try {
			Properties properties = ConfigurationManager.getInstance().getProperties();

			Class.forName(properties.getProperty("driver"));

			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(properties.getProperty("url"));
			config.setUsername(properties.getProperty("username"));
			config.setPassword(properties.getProperty("password"));

			dataSource = new HikariDataSource(config);
		} catch (Throwable ex) {
			System.err.println("Initial DataSource creation failed.\n" + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}