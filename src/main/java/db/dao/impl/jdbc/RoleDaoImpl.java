package db.dao.impl.jdbc;

import db.dao.RoleDao;
import db.entity.Role;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements RoleDao {
	private final Connection connection;
	private final String tableName = "role_list";

	public RoleDaoImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(@NotNull Role entity) {
		String SQL_INSERT = "INSERT INTO %s (code, name) VALUES (%s, %s)".
				formatted(tableName, entity.getCode(), entity.getName());

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
	public Role findById(Long id) {
		Role entity = null;
		String SQL_SELECT_BY_ID = "SELECT id, code, name FROM %s WHERE id = %d".formatted(tableName, id);

		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_BY_ID)) {
				if (resultSet.next()) {
					entity = new Role(
							resultSet.getLong("id"),
							resultSet.getString("code"),
							resultSet.getString("name")
					);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public void update(@NotNull Role entity) {
		String SQL_UPDATE = "UPDATE %s SET code = %s, name = %s WHERE id = %d".
				formatted(tableName, entity.getCode(), entity.getName(), entity.getId());

		try (Statement statement = connection.createStatement()) {
			statement.executeUpdate(SQL_UPDATE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(@NotNull Role entity) {
		String SQL_DELETE = "DELETE FROM %s WHERE id = %d".formatted(tableName, entity.getId());

		try (Statement statement = connection.createStatement()) {
			statement.executeUpdate(SQL_DELETE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Role> getAll() {
		List<Role> entityList = new ArrayList<>();
		String SQL_SELECT_ALL= "SELECT id, code, name FROM %s".formatted(tableName);

		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL)) {
				while (resultSet.next()) {
					Role entity = new Role(
							resultSet.getLong("id"),
							resultSet.getString("code"),
							resultSet.getString("name")
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
	public Role findByCode(String code) {
		Role entity = null;
		String SQL_SELECT_BY_CODE = "SELECT id, code, name FROM %s WHERE code = %s".formatted(tableName, code);

		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_BY_CODE)) {
				if (resultSet.next()) {
					entity = new Role(
							resultSet.getLong("id"),
							resultSet.getString("code"),
							resultSet.getString("name")
					);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}
}
