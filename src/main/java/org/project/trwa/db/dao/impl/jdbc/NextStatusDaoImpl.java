package org.project.trwa.db.dao.impl.jdbc;

import org.project.trwa.db.dao.NextStatusDao;
import org.project.trwa.db.dao.impl.mapper.NextStatusMapper;
import org.project.trwa.db.entity.NextStatus;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NextStatusDaoImpl implements NextStatusDao, General<NextStatus> {
	private static final Logger logger = Logger.getLogger(NextStatusDaoImpl.class.getName());
	private final Connection connection;
	private final String tableName = "next_statuses";

	public NextStatusDaoImpl(@NotNull Connection connection) {
		this.connection = connection;
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			logger.log(Level.WARNING, e.getMessage());
		}
	}

	@Override
	public void finalize() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			logger.log(Level.WARNING, e.getMessage());
		}
	}

	@Override
	public NextStatus getMappedEntity(ResultSet resultSet) throws SQLException {
		return new NextStatusMapper().extractFromResultSet(resultSet);
	}

	@Override
	public Optional<NextStatus> insert(@NotNull NextStatus entity) throws SQLException {
		String SQL_INSERT = "INSERT INTO %s (status_id, next_status_id) VALUES (%d, %d)".
				formatted(tableName, entity.getCurrentStatus().getId(), entity.getNextStatus().getId());

		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT)) {
			preparedStatement.executeUpdate();
			connection.commit();
			return findByStatuses(entity);
		} catch (SQLException e) {
			logger.log(Level.WARNING, e.getMessage());
			connection.rollback();
		}
		return Optional.empty();
	}

	@Override
	public Optional<NextStatus> findById(Long id) {
		String SQL_SELECT_BY_ID = """
				SELECT id, status_id, next_status_id
				FROM %s WHERE id = %d""".formatted(tableName, id);

		return findOneBy(connection, SQL_SELECT_BY_ID, logger);
	}

	@Override
	public void update(@NotNull NextStatus entity) throws SQLException {
		String SQL_UPDATE = "UPDATE %s SET status_id = %d, next_status_id = %d WHERE id = %d".
				formatted(tableName, entity.getCurrentStatus().getId(), entity.getNextStatus().getId(), entity.getId());

		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
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
	public List<NextStatus> getAll() {
		String SQL_SELECT_ALL= "SELECT id, status_id, next_status_id FROM %s".formatted(tableName);

		return findManyBy(connection, SQL_SELECT_ALL, logger);
	}

	@Override
	public List<NextStatus> findNextStatuses(Long statusId) {
		String SQL_SELECT_ALL= """
				SELECT id, status_id, next_status_id
				FROM %s WHERE status_id = %d""".formatted(tableName, statusId);

		return findManyBy(connection, SQL_SELECT_ALL, logger);
	}

	@Override
	public Optional<NextStatus> findByStatuses(@NotNull NextStatus entity) {
		String SQL_SELECT_BY_STATUSES = """
				SELECT id, status_id, next_status_id
				FROM %s WHERE status_id = %d AND next_status_id = %d""".
				formatted(tableName, entity.getCurrentStatus().getId(), entity.getNextStatus().getId());

		return findOneBy(connection, SQL_SELECT_BY_STATUSES, logger);
	}
}
