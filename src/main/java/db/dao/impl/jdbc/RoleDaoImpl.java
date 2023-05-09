package db.dao.impl.jdbc;

import db.dao.RoleDao;
import db.dao.impl.mapper.RoleMapper;
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
	public void insert(@NotNull Role entity) throws SQLException {
		String SQL_INSERT = "INSERT INTO %s (code, name) VALUES (%s, %s)".
				formatted(tableName, entity.getCode(), entity.getName());

		DaoImplGeneral.update(connection, SQL_INSERT);
	}

	@Override
	public Role findById(Long id) {
		String SQL_SELECT_BY_ID = "SELECT id, code, name FROM %s WHERE id = %d".formatted(tableName, id);

		Role entity = null;
		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_BY_ID)) {
				if (resultSet.next()) {
					entity = new RoleMapper().extractFromResultSet(resultSet);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public void update(@NotNull Role entity) throws SQLException {
		String SQL_UPDATE = "UPDATE %s SET code = %s, name = %s WHERE id = %d".
				formatted(tableName, entity.getCode(), entity.getName(), entity.getId());

		DaoImplGeneral.update(connection, SQL_UPDATE);
	}

	@Override
	public void delete(@NotNull Role entity) throws SQLException {
		String SQL_DELETE = "DELETE FROM %s WHERE id = %d".formatted(tableName, entity.getId());

		DaoImplGeneral.update(connection, SQL_DELETE);
	}

	@Override
	public List<Role> getAll() {
		String SQL_SELECT_ALL= "SELECT id, code, name FROM %s".formatted(tableName);

		List<Role> entityList = new ArrayList<>();
		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL)) {
				while (resultSet.next()) {
					Role entity = new RoleMapper().extractFromResultSet(resultSet);
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
		String SQL_SELECT_BY_CODE = "SELECT id, code, name FROM %s WHERE code = %s".formatted(tableName, code);

		Role entity = null;
		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_BY_CODE)) {
				if (resultSet.next()) {
					entity = new RoleMapper().extractFromResultSet(resultSet);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}
}
