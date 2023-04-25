package db.dao.impl.mysql;

import db.dao.RoleDao;
import db.entity.Role;
import org.jetbrains.annotations.*;

import java.sql.*;
import java.util.List;

public class RoleDaoImpl implements RoleDao {
	private final Connection connection;
	private final String SQL_INSERT_ROLE = "INSERT INTO role_list (id, name) VALUES (?, ?)";
	private final String SQL_SELECT_ALL_ROLE = "SELECT id, name FROM role_list";
	private final String SQL_SELECT_BY_ID_ROLE = "SELECT id, name FROM role_list WHERE id = ?";
	private final String SQL_UPDATE_ROLE = "UPDATE role_list SET name = ? WHERE id = ?";
	private final String SQL_DELETE_ROLE = "DELETE FROM role_list WHERE id = ?";

	public RoleDaoImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void create(@NotNull Role entity) {

		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_ROLE)) {

			preparedStatement.setLong(1, entity.getId());
			preparedStatement.setString(2, entity.getName());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Role> getAll() {
		return null;
	}

	@Override
	public Role findById(Long id) {

		Role entity = null;
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID_ROLE)) {

			preparedStatement.setLong(1, entity.getId());

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				entity = new Role(resultSet.getLong("id"),
						resultSet.getString("name"));
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public void update(Role role) {

	}

	@Override
	public void delete(Role role) {

	}
}
