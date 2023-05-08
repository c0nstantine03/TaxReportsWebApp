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
		String SQL_INSERT_ROLE = "INSERT INTO " + tableName + " (code, name) VALUES (?, ?)";

		try {
			// insert value
			try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_ROLE)) {
				preparedStatement.setString(1, entity.getCode());
				preparedStatement.setString(2, entity.getName());

				preparedStatement.executeUpdate();
			}
			// get auto-generic id
			Role localEntity = findByCode(entity.getCode());
			entity.setId(localEntity.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Role> getAll() {
		List<Role> entityList = new ArrayList<>();
		String SQL_SELECT_ALL_ROLE = "SELECT id, code, name FROM " + tableName;

		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_ROLE)) {
				while (resultSet.next()) {
					Role entity = new Role(resultSet.getLong("id"),
							resultSet.getString("code"),
							resultSet.getString("name"));
					entityList.add(entity);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entityList;
	}

	@Override
	public Role findById(Long id) {
		Role entity = null;
		String SQL_SELECT_BY_ID_ROLE = "SELECT id, code, name FROM " + tableName + " WHERE id = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID_ROLE)) {

			preparedStatement.setLong(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				entity = new Role(resultSet.getLong("id"),
						resultSet.getString("code"),
						resultSet.getString("name"));
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public Role findByCode(String code) {
		Role entity = null;
		String SQL_SELECT_BY_CODE_ROLE = "SELECT id, code, name FROM " + tableName + " WHERE code = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_CODE_ROLE)) {
			preparedStatement.setString(1, code);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					entity = new Role(resultSet.getLong("id"),
							resultSet.getString("code"),
							resultSet.getString("name"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public void update(@NotNull Role entity) {
		String SQL_UPDATE_ROLE = "UPDATE " + tableName + " SET code = ?, name = ? WHERE id = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ROLE)) {
			preparedStatement.setString(1, entity.getCode());
			preparedStatement.setString(2, entity.getName());
			preparedStatement.setLong(3, entity.getId());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(@NotNull Role entity) {
		String SQL_DELETE_ROLE = "DELETE FROM " + tableName + " WHERE id = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ROLE)) {
			preparedStatement.setLong(1, entity.getId());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
