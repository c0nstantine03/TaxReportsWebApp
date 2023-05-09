package db.dao.impl.jdbc;

import db.dao.UserDao;
import db.dao.factory.DaoFactory;
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
	public void insert(@NotNull User entity) {
		String SQL_INSERT = """
				INSERT INTO %s (login, password, first_name, last_name, mail, role_id, residency_id, person_id)
				VALUES (%s, %s, %s, %s, %s, %d, %d, %d)""".
				formatted(tableName, entity.getLogin(), entity.getPassword(), entity.getFirstName(), entity.getLastName(),
				entity.getMail(), entity.getRole().getId(), entity.getResidency().getId(), entity.getPersonality().getId());

		String SQL_SELECT_ID_BY_LOGIN = "SELECT id FROM %s WHERE login = %s".
				formatted(tableName, entity.getLogin());

		try {
			// insert value
			try (Statement statement = connection.createStatement()) {
				statement.executeUpdate(SQL_INSERT);
			} catch (SQLException e) {
				throw new SQLException("In insert statement: ", e);
			}
			// get auto-generic id
			try (Statement statement = connection.createStatement()) {
				try(ResultSet resultSet = statement.executeQuery(SQL_SELECT_ID_BY_LOGIN)) {
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
	public User findById(Long id) {
		User entity = null;
		String SQL_SELECT_BY_ID = """
				SELECT id, login, password, first_name, last_name, mail, role_id, \
				residency_id, person_id, is_enable, created, modified
				FROM %s WHERE id = %d""".formatted(tableName, id);

		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_BY_ID)) {
				if (resultSet.next()) {
					entity = new User(
							resultSet.getLong("id"),
							resultSet.getString("login"),
							resultSet.getString("password"),
							resultSet.getString("first_name"),
							resultSet.getString("last_name"),
							resultSet.getString("mail"),
							DaoFactory.getInstance().createRoleDao().findById(resultSet.getLong("role_id")),
							DaoFactory.getInstance().createResidencyDao().findById(resultSet.getLong("residency_id")),
							DaoFactory.getInstance().createPersonalityDao().findById(resultSet.getLong("person_id")),
							resultSet.getBoolean("is_enable"),
							resultSet.getTimestamp("created"),
							resultSet.getTimestamp("modified")
					);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public void update(@NotNull User entity) {
		String SQL_UPDATE = """
		UPDATE %s SET login = %s, password = %s, first_name = %s, last_name = %s, mail = %s, \
		role_id = %d, residency_id = %d, person_id = %d, modified = NOW()
		WHERE id = %d""".
				formatted(tableName, entity.getLogin(), entity.getPassword(),
				entity.getFirstName(), entity.getLastName(), entity.getMail(), entity.getRole().getId(),
				entity.getResidency().getId(), entity.getPersonality().getId(), entity.getId());

		try (Statement statement = connection.createStatement()) {
			statement.executeUpdate(SQL_UPDATE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(@NotNull User entity) {
		String SQL_DELETE = "DELETE FROM %s WHERE id = %d".formatted(tableName, entity.getId());

		try (Statement statement = connection.createStatement()) {
			statement.executeUpdate(SQL_DELETE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
					User entity = new User(
							resultSet.getLong("id"),
							resultSet.getString("login"),
							resultSet.getString("password"),
							resultSet.getString("first_name"),
							resultSet.getString("last_name"),
							resultSet.getString("mail"),
							DaoFactory.getInstance().createRoleDao().findById(resultSet.getLong("role_id")),
							DaoFactory.getInstance().createResidencyDao().findById(resultSet.getLong("residency_id")),
							DaoFactory.getInstance().createPersonalityDao().findById(resultSet.getLong("person_id")),
							resultSet.getBoolean("is_enable"),
							resultSet.getTimestamp("created"),
							resultSet.getTimestamp("modified")
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
	public User findByLogin(String login) {
		User entity = null;
		String SQL_SELECT_BY_LOGIN = """
				SELECT id, login, password, first_name, last_name, mail, role_id, \
				residency_id, person_id, is_enable, created, modified
				FROM %s WHERE login = %s""".formatted(tableName, login);

		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_BY_LOGIN)) {
				if (resultSet.next()) {
					entity = new User(
							resultSet.getLong("id"),
							resultSet.getString("login"),
							resultSet.getString("password"),
							resultSet.getString("first_name"),
							resultSet.getString("last_name"),
							resultSet.getString("mail"),
							DaoFactory.getInstance().createRoleDao().findById(resultSet.getLong("role_id")),
							DaoFactory.getInstance().createResidencyDao().findById(resultSet.getLong("residency_id")),
							DaoFactory.getInstance().createPersonalityDao().findById(resultSet.getLong("person_id")),
							resultSet.getBoolean("is_enable"),
							resultSet.getTimestamp("created"),
							resultSet.getTimestamp("modified")
					);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public User findByLoginAndPassword(String login, String password) {
		User entity = null;
		String SQL_SELECT_BY_LOGIN = """
				SELECT id, login, password, first_name, last_name, mail, role_id, \
				residency_id, person_id, is_enable, created, modified
				FROM %s WHERE login = %s AND password = %s""".formatted(tableName, login, password);

		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_BY_LOGIN)) {
				if (resultSet.next()) {
					entity = new User(
							resultSet.getLong("id"),
							resultSet.getString("login"),
							resultSet.getString("password"),
							resultSet.getString("first_name"),
							resultSet.getString("last_name"),
							resultSet.getString("mail"),
							DaoFactory.getInstance().createRoleDao().findById(resultSet.getLong("role_id")),
							DaoFactory.getInstance().createResidencyDao().findById(resultSet.getLong("residency_id")),
							DaoFactory.getInstance().createPersonalityDao().findById(resultSet.getLong("person_id")),
							resultSet.getBoolean("is_enable"),
							resultSet.getTimestamp("created"),
							resultSet.getTimestamp("modified")
					);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public void setEnable(Long id, Boolean enable) {
		String SQL_SET_ENABLE = "UPDATE %s SET is_enable = %d WHERE id = %d".
				formatted(tableName, enable ? 1 : 0, id);

		try (Statement statement = connection.createStatement()) {
			statement.executeUpdate(SQL_SET_ENABLE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
