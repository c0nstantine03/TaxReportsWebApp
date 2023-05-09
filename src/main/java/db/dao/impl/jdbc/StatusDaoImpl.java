package db.dao.impl.jdbc;

import db.dao.StatusDao;
import db.entity.Status;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatusDaoImpl implements StatusDao {
	private final Connection connection;
	private final String tableName = "status_list";

	public StatusDaoImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(@NotNull Status entity) {
		String SQL_INSERT = "INSERT INTO %s (code, name, closed) VALUES (%s, %s, %d)".
				formatted(tableName, entity.getCode(), entity.getName(), entity.isClosed() ? 1 : 0);

		String SQL_SELECT_ID_BY_CODE = "SELECT id FROM %s WHERE code = %s".
				formatted(tableName, entity.getCode());

		try {
			// insert value
			try (Statement statement = connection.createStatement()) {
				statement.executeUpdate(SQL_INSERT);
			} catch (SQLException e) {
				throw new SQLException("In insert statement: ", e);
			}
			// get auto-generic id
			try (Statement statement = connection.createStatement()) {
				try(ResultSet resultSet = statement.executeQuery(SQL_SELECT_ID_BY_CODE)) {
					if(resultSet.next()) {
						entity.setId(resultSet.getLong("id"));
					}
				}
			} catch (SQLException e) {
				throw new SQLException("In select statement: ", e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Status findById(Long id) {
		Status entity = null;
		String SQL_SELECT_BY_ID = "SELECT id, code, name, closed FROM %s WHERE id = %d".formatted(tableName, id);

		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_BY_ID)) {
				if (resultSet.next()) {
					entity = new Status(
							resultSet.getLong("id"),
							resultSet.getString("code"),
							resultSet.getString("name"),
							resultSet.getBoolean("closed")
					);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public void update(@NotNull Status entity) {
		String SQL_UPDATE = "UPDATE %s SET code = %s, name = %s, closed = %d WHERE id = %d".
				formatted(tableName, entity.getCode(), entity.getName(),
						entity.isClosed() ? 1 : 0, entity.getId());

		try (Statement statement = connection.createStatement()) {
			statement.executeUpdate(SQL_UPDATE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(@NotNull Status entity) {
		String SQL_DELETE = "DELETE FROM %s WHERE id = %d".formatted(tableName, entity.getId());

		try (Statement statement = connection.createStatement()) {
			statement.executeUpdate(SQL_DELETE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Status> getAll() {
		List<Status> entityList = new ArrayList<>();
		String SQL_SELECT_ALL= "SELECT id, code, name, closed FROM %s".formatted(tableName);

		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL)) {
				while (resultSet.next()) {
					Status entity = new Status(
							resultSet.getLong("id"),
							resultSet.getString("code"),
							resultSet.getString("name"),
							resultSet.getBoolean("closed")
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
	public Status findByCode(String code) {
		Status entity = null;
		String SQL_SELECT_BY_CODE = "SELECT id, code, name, closed FROM %s WHERE code = %s".formatted(tableName, code);

		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_BY_CODE)) {
				if (resultSet.next()) {
					entity = new Status(
							resultSet.getLong("id"),
							resultSet.getString("code"),
							resultSet.getString("name"),
							resultSet.getBoolean("closed")
					);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}
}
