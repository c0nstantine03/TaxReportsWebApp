package db.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public interface DAO<T> {

	//create
	void insert(T entity) throws SQLException;

	//read
	T findById(Long id);

	//statementUpdate
	void update(T entity) throws SQLException;

	//delete
	void delete(T entity) throws SQLException;

	default void statementUpdate(Connection connection, String query) throws SQLException {
		try (Statement statement = connection.createStatement()) {
			statement.executeUpdate(query);
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Rollback statement with query " + query);
			connection.rollback();
		}
	}
}
