package db.dao.impl.jdbc;

import db.dao.NextStatusDao;
import db.dao.factory.DaoFactory;
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
	public void insert(@NotNull NextStatus entity) {
		String SQL_INSERT = "INSERT INTO %s (status_id, next_status_id) VALUES (%d, %d)".
				formatted(tableName, entity.getCurrentStatus().getId(), entity.getNextStatus().getId());

		try (Statement statement = connection.createStatement()) {
			statement.executeUpdate(SQL_INSERT);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public NextStatus findById(Long id) {
		NextStatus entity = null;
		String SQL_SELECT_BY_ID = "SELECT status_id, next_status_id FROM %s WHERE status_id = %d".formatted(tableName, id);

		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_BY_ID)) {
				if (resultSet.next()) {
					entity = new NextStatus(
							DaoFactory.getInstance().createStatusDao().findById(resultSet.getLong("status_id")),
							DaoFactory.getInstance().createStatusDao().findById(resultSet.getLong("next_status_id"))
					);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public void update(NextStatus entity) {
		throw new UnsupportedOperationException("Can't define update operation in this case");
	}

	@Override
	public void delete(@NotNull NextStatus entity) {
		String SQL_DELETE = "DELETE FROM %s WHERE status_id = %d AND next_status_id = %d".
				formatted(tableName, entity.getCurrentStatus().getId(), entity.getNextStatus().getId());

		try (Statement statement = connection.createStatement()) {
			statement.executeUpdate(SQL_DELETE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<NextStatus> getAll() {
		List<NextStatus> entityList = new ArrayList<>();
		String SQL_SELECT_ALL= "SELECT status_id, next_status_id FROM %s".formatted(tableName);

		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL)) {
				while (resultSet.next()) {
					NextStatus entity = new NextStatus(
							DaoFactory.getInstance().createStatusDao().findById(resultSet.getLong("status_id")),
							DaoFactory.getInstance().createStatusDao().findById(resultSet.getLong("next_status_id"))
					);
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
					NextStatus entity = new NextStatus(
							DaoFactory.getInstance().createStatusDao().findById(resultSet.getLong("status_id")),
							DaoFactory.getInstance().createStatusDao().findById(resultSet.getLong("next_status_id"))
					);
					entityList.add(entity);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entityList;
	}
}
