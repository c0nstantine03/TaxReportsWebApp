package db.dao.impl.jdbc;

import db.dao.StatusDao;
import db.dao.impl.mapper.StatusMapper;
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
	public void insert(@NotNull Status entity) throws SQLException {
		String SQL_INSERT = "INSERT INTO %s (code, name, closed) VALUES (%s, %s, %d)".
				formatted(tableName, entity.getCode(), entity.getName(), entity.getClosed() ? 1 : 0);

		DaoImplGeneral.update(connection, SQL_INSERT);
	}

	@Override
	public Status findById(Long id) {
		Status entity = null;
		String SQL_SELECT_BY_ID = "SELECT id, code, name, closed FROM %s WHERE id = %d".formatted(tableName, id);

		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_BY_ID)) {
				if (resultSet.next()) {
					entity = new StatusMapper().extractFromResultSet(resultSet);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public void update(@NotNull Status entity) throws SQLException {
		String SQL_UPDATE = "UPDATE %s SET code = %s, name = %s, closed = %d WHERE id = %d".
				formatted(tableName, entity.getCode(), entity.getName(),
						entity.getClosed() ? 1 : 0, entity.getId());

		DaoImplGeneral.update(connection, SQL_UPDATE);
	}

	@Override
	public void delete(@NotNull Status entity) throws SQLException {
		String SQL_DELETE = "DELETE FROM %s WHERE id = %d".formatted(tableName, entity.getId());

		DaoImplGeneral.update(connection, SQL_DELETE);
	}

	@Override
	public List<Status> getAll() {
		List<Status> entityList = new ArrayList<>();
		String SQL_SELECT_ALL= "SELECT id, code, name, closed FROM %s".formatted(tableName);

		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL)) {
				while (resultSet.next()) {
					Status entity = new StatusMapper().extractFromResultSet(resultSet);
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
					entity = new StatusMapper().extractFromResultSet(resultSet);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}
}
