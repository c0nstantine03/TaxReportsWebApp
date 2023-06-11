package org.project.trwa.db.dao.impl.conn;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

// In this class we create interface to using Pool of Connections
public class DataSource {
	private static volatile DataSource instance;
	private final HikariDataSource dataSource;

	private DataSource() {
		try {
			// Initialise hikari configuration
			HikariConfig config = new HikariConfig("src/main/resources/hikari.properties");

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

	// Double-Checked Instantiation
	public static DataSource getInstance() {
		DataSource localInstance = instance;
		if (localInstance == null) {
			synchronized (DataSource.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new DataSource();
				}
			}
		}
		return localInstance;
	}

	public Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}