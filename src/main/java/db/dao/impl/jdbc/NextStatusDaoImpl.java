package db.dao.impl.jdbc;

import db.dao.NextStatusDao;
import db.dao.impl.mapper.NextStatusMapper;
import db.entity.NextStatus;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NextStatusDaoImpl implements NextStatusDao {
	private final Connection connection;
	private final String tableName = "next_statuses";

	public NextStatusDaoImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(@NotNull NextStatus entity) throws SQLException {
		String SQL_INSERT = "INSERT INTO %s (status_id, next_status_id) VALUES (%d, %d)".
				formatted(tableName, entity.getCurrentStatus().getId(), entity.getNextStatus().getId());

		DaoImplGeneral.update(connection, SQL_INSERT);
	}

	@Override
	public NextStatus findById(Long id) {
		NextStatus entity = null;
		String SQL_SELECT_BY_ID = "SELECT id, status_id, next_status_id FROM %s WHERE id = %d".formatted(tableName, id);

		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_BY_ID)) {
				if (resultSet.next()) {
					entity = new NextStatusMapper().extractFromResultSet(resultSet);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public void update(@NotNull NextStatus entity) throws SQLException {
		String SQL_UPDATE = "UPDATE %s SET status_id = %d, next_status_id = %d WHERE id = %d".
				formatted(tableName, entity.getCurrentStatus().getId(), entity.getNextStatus().getId(), entity.getId());

		DaoImplGeneral.update(connection, SQL_UPDATE);
	}

	@Override
	public void delete(@NotNull NextStatus entity) throws SQLException {
		String SQL_DELETE = "DELETE FROM %s WHERE status_id = %d AND next_status_id = %d".
				formatted(tableName, entity.getCurrentStatus().getId(), entity.getNextStatus().getId());

		DaoImplGeneral.update(connection, SQL_DELETE);
	}

	@Override
	public List<NextStatus> getAll() {
		List<NextStatus> entityList = new ArrayList<>();
		String SQL_SELECT_ALL= "SELECT status_id, next_status_id FROM %s".formatted(tableName);

		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL)) {
				while (resultSet.next()) {
					NextStatus entity = new NextStatusMapper().extractFromResultSet(resultSet);
					entityList.add(entity);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entityList;
	}

	@Override
	public List<NextStatus> findNextStatuses(Long statusId) {
		List<NextStatus> entityList = new ArrayList<>();
		String SQL_SELECT_ALL= "SELECT status_id, next_status_id FROM %s WHERE status_id = %d".formatted(tableName, statusId);

		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL)) {
				while (resultSet.next()) {
					NextStatus entity = new NextStatusMapper().extractFromResultSet(resultSet);
					entityList.add(entity);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entityList;
	}
}
