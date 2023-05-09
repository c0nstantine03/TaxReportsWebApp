package db.dao.impl.jdbc;

import org.jetbrains.annotations.NotNull;

import java.sql.*;

public class DaoImplGeneral {

	public static void update(@NotNull Connection connection, String query) throws SQLException {
		try (Statement statement = connection.createStatement()) {
			statement.executeUpdate(query);
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			connection.rollback();
		}
	}
}
