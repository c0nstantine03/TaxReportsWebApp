package db.dao.impl.mysql;

import db.dao.RoleDAO;
import db.entity.Person;
import db.entity.Role;
import org.jetbrains.annotations.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDAOImpl implements RoleDAO {
	private final Connection connection;
	private final String SQL_INSERT_ROLE = "INSERT INTO role_list (id, name) VALUES (?, ?)";
	private final String SQL_SELECT_BY_ID_ROLE = "SELECT id, name FROM role_list WHERE id = ?";

	public RoleDAOImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void add(@NotNull Role entity) {

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
	public Role getById(Long id) {

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
