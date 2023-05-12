package org.project.taxreportswebapp.db.dao.impl.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

interface General<T> {

	T getMappedEntity(ResultSet resultSet) throws SQLException;

	default Optional<T> findOneBy(Connection connection, String query, Logger logger) {
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					return Optional.of(getMappedEntity(resultSet));
				}
			}
		} catch (SQLException e) {
			logger.log(Level.WARNING, e.getMessage());
		}
		return Optional.empty();
	}

	default List<T> findManyBy(Connection connection, String query, Logger logger) {
		List<T> entityList = new ArrayList<>();
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					entityList.add(getMappedEntity(resultSet));
				}
			}
		} catch (SQLException e) {
			logger.log(Level.WARNING, e.getMessage());
		}
		return entityList;
	}
}
