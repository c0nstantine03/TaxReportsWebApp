package org.project.taxreportswebapp.db.dao.impl.jdbc;

import org.project.taxreportswebapp.db.dao.StatusDao;
import org.project.taxreportswebapp.db.dao.impl.mapper.StatusMapper;
import org.project.taxreportswebapp.db.entity.Status;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StatusDaoImpl implements StatusDao, General<Status> {
	private static final Logger logger = Logger.getLogger(StatusDaoImpl.class.getName());
	private final Connection connection;
	private final String tableName = "status_list";

	public StatusDaoImpl(@NotNull Connection connection) {
		this.connection = connection;
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			logger.log(Level.WARNING, e.getMessage());
		}
	}

	@Override
	public Status getMappedEntity(ResultSet resultSet) throws SQLException {
		return new StatusMapper().extractFromResultSet(resultSet);
	}

	@Override
	public Optional<Status> insert(@NotNull Status entity) throws SQLException {
		String SQL_INSERT = "INSERT INTO %s (code, name, closed) VALUES (?, ?, ?)".formatted(tableName);

		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT)) {
			preparedStatement.setString(1, entity.getCode());
			preparedStatement.setString(2, entity.getName());
			preparedStatement.setBoolean(3, entity.getClosed());

			preparedStatement.executeUpdate();
			connection.commit();
			return findByCode(entity.getCode());
		} catch (SQLException e) {
			logger.log(Level.WARNING, e.getMessage());
			connection.rollback();
		}
		return Optional.empty();
	}

	@Override
	public Optional<Status> findById(Long id) {
		String SQL_SELECT_BY_ID = "SELECT id, code, name, closed FROM %s WHERE id = %d".formatted(tableName, id);

		return findOneBy(connection, SQL_SELECT_BY_ID, logger);
	}

	@Override
	public void update(@NotNull Status entity) throws SQLException {
		String SQL_UPDATE = "UPDATE %s SET code = ?, name = ?, closed = ? WHERE id = %d".formatted(tableName, entity.getId());

		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
			preparedStatement.setString(1, entity.getCode());
			preparedStatement.setString(2, entity.getName());
			preparedStatement.setBoolean(3, entity.getClosed());

			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			logger.warning(e.getMessage());
			connection.rollback();
		}
	}

	@Override
	public void delete(Long id) throws SQLException {
		String SQL_DELETE = "DELETE FROM %s WHERE id = %d".formatted(tableName, id);

		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			logger.warning(e.getMessage());
			connection.rollback();
		}
	}

	@Override
	public List<Status> getAll() {
		String SQL_SELECT_ALL= "SELECT id, code, name, closed FROM %s".formatted(tableName);

		return findManyBy(connection, SQL_SELECT_ALL, logger);
	}

	@Override
	public Optional<Status> findByCode(String code) {
		String SQL_SELECT_BY_CODE = "SELECT id, code, name, closed FROM %s WHERE code = '%s'".formatted(tableName, code);

		return findOneBy(connection, SQL_SELECT_BY_CODE, logger);
	}
}
