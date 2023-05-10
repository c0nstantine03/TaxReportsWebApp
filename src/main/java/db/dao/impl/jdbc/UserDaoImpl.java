package db.dao.impl.jdbc;

import db.dao.UserDao;
import db.dao.impl.mapper.*;
import db.entity.User;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
	private final Connection connection;
	private final String tableName = "user_list";

	public UserDaoImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(@NotNull User entity) throws SQLException {
		String SQL_INSERT = """
				INSERT INTO %s (login, password, first_name, last_name, mail, role_id, residency_id, person_id)
				VALUES (%s, %s, %s, %s, %s, %d, %d, %d)""".
				formatted(tableName, entity.getLogin(), entity.getPassword(), entity.getFirstName(), entity.getLastName(),
				entity.getMail(), entity.getRole().getId(), entity.getResidency().getId(), entity.getPersonality().getId());

		statementUpdate(connection, SQL_INSERT);
	}

	@Override
	public User findById(Long id) {
		String SQL_SELECT_BY_ID = """
				SELECT id, login, password, first_name, last_name, mail, role_id, \
				residency_id, person_id, is_enable, created, modified
				FROM %s WHERE id = %d""".formatted(tableName, id);

		User entity = null;
		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_BY_ID)) {
				if (resultSet.next()) {
					entity = new UserMapper().extractFromResultSet(resultSet);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public void update(@NotNull User entity) throws SQLException {
		String SQL_UPDATE = """
		UPDATE %s SET login = %s, password = %s, first_name = %s, last_name = %s, mail = %s, \
		role_id = %d, residency_id = %d, person_id = %d, modified = NOW()
		WHERE id = %d""".
				formatted(tableName, entity.getLogin(), entity.getPassword(),
				entity.getFirstName(), entity.getLastName(), entity.getMail(), entity.getRole().getId(),
				entity.getResidency().getId(), entity.getPersonality().getId(), entity.getId());

		statementUpdate(connection, SQL_UPDATE);
	}

	@Override
	public void delete(@NotNull User entity) throws SQLException {
		String SQL_DELETE = "DELETE FROM %s WHERE id = %d".formatted(tableName, entity.getId());

		statementUpdate(connection, SQL_DELETE);
	}

	@Override
	public List<User> getAll() {
		List<User> entityList = new ArrayList<>();
		String SQL_SELECT_ALL = """
		SELECT id, login, password, first_name, last_name, mail, role_id, \
		residency_id, person_id, is_enable, created, modified FROM %s""".formatted(tableName);

		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL)) {
				while (resultSet.next()) {
					User entity = new UserMapper().extractFromResultSet(resultSet);
					entityList.add(entity);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entityList;
	}

	@Override
	public User findByLogin(String login) {
		String SQL_SELECT_BY_LOGIN = """
				SELECT id, login, password, first_name, last_name, mail, role_id, \
				residency_id, person_id, is_enable, created, modified
				FROM %s WHERE login = %s""".formatted(tableName, login);

		User entity = null;
		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_BY_LOGIN)) {
				if (resultSet.next()) {
					entity = new UserMapper().extractFromResultSet(resultSet);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public User findByLoginAndPassword(String login, String password) {
		String SQL_SELECT_BY_LOGIN_AND_PASS = """
				SELECT id, login, password, first_name, last_name, mail, role_id, \
				residency_id, person_id, is_enable, created, modified
				FROM %s WHERE login = %s AND password = %s""".formatted(tableName, login, password);

		User entity = null;
		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_BY_LOGIN_AND_PASS)) {
				if (resultSet.next()) {
					entity = new UserMapper().extractFromResultSet(resultSet);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public void setEnable(String login, Boolean enable) throws SQLException {
		String SQL_SET_ENABLE = "UPDATE %s SET is_enable = %d WHERE login = %s".
				formatted(tableName, enable ? 1 : 0, login);

		statementUpdate(connection, SQL_SET_ENABLE);
	}
}
