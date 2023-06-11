package org.project.trwa.db.dao.impl.jdbc;

import org.project.trwa.db.dao.UserDao;
import org.project.trwa.db.dao.impl.mapper.UserMapper;
import org.project.trwa.db.entity.Role;
import org.project.trwa.db.entity.User;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoImpl implements UserDao, General<User> {
	private static final Logger logger = Logger.getLogger(UserDaoImpl.class.getName());
	private final Connection connection;
	private final String tableName = "user_list";

	public UserDaoImpl(@NotNull Connection connection) {
		this.connection = connection;
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			logger.log(Level.WARNING, e.getMessage());
		}
	}

	@Override
	public void finalize() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			logger.log(Level.WARNING, e.getMessage());
		}
	}

	@Override
	public User getMappedEntity(ResultSet resultSet) throws SQLException {
		return new UserMapper().extractFromResultSet(resultSet);
	}

	@Override
	public Optional<User> insert(@NotNull User entity) throws SQLException {
		String SQL_INSERT = """
				INSERT INTO %s (login, password, first_name, last_name, mail, role_id, residency_id, person_id)
				VALUES (?, ?, ?, ?, ?, ?, ?, ?)""".formatted(tableName);

		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT)) {
			preparedStatement.setString(1, entity.getLogin());
			preparedStatement.setString(2, entity.getPassword());
			preparedStatement.setString(3, entity.getFirstName());
			preparedStatement.setString(4, entity.getLastName());
			preparedStatement.setString(5, entity.getMail());
			preparedStatement.setLong(6, entity.getRole().getId());
			preparedStatement.setLong(7, entity.getResidency().getId());
			preparedStatement.setLong(8, entity.getPersonality().getId());

			preparedStatement.executeUpdate();
			connection.commit();
			return findByLogin(entity.getLogin());
		} catch (SQLException e) {
			logger.log(Level.WARNING, e.getMessage());
			connection.rollback();
		}
		return Optional.empty();
	}

	@Override
	public Optional<User> findById(Long id) {
		String SQL_SELECT_BY_ID = """
				SELECT id, login, password, first_name, last_name, mail, role_id,
				residency_id, person_id, is_enable, created, modified
				FROM %s WHERE id = %d""".formatted(tableName, id);

		return findOneBy(connection, SQL_SELECT_BY_ID, logger);
	}

	@Override
	public void update(@NotNull User entity) throws SQLException {
		String SQL_UPDATE = """
		UPDATE %s SET login = ?, password = ?, first_name = ?, last_name = ?, mail = ?,
		role_id = ?, residency_id = ?, person_id = ?, modified = NOW()
		WHERE id = %d""".formatted(tableName, entity.getId());

		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
			preparedStatement.setString(1, entity.getLogin());
			preparedStatement.setString(2, entity.getPassword());
			preparedStatement.setString(3, entity.getFirstName());
			preparedStatement.setString(4, entity.getLastName());
			preparedStatement.setString(5, entity.getMail());
			preparedStatement.setLong(6, entity.getRole().getId());
			preparedStatement.setLong(7, entity.getResidency().getId());
			preparedStatement.setLong(8, entity.getPersonality().getId());

			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			logger.warning(e.getMessage());
			connection.rollback();
		}
	}

	@Override
	public void delete(Long id) throws SQLException {
		String SQL_DELETE = "DELETE FROM %s WHERE id = %d".formatted(tableName, id);

		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			logger.warning(e.getMessage());
			connection.rollback();
		}
	}

	@Override
	public List<User> getAll() {
		String SQL_SELECT_ALL = """
		SELECT id, login, password, first_name, last_name, mail, role_id,
		residency_id, person_id, is_enable, created, modified FROM %s""".formatted(tableName);

		return findManyBy(connection, SQL_SELECT_ALL, logger);
	}

	@Override
	public List<User> getByRole(@NotNull Role role) {
		String SQL_SELECT_BY_ROLE = """
		SELECT id, login, password, first_name, last_name, mail, role_id,
		residency_id, person_id, is_enable, created, modified
		FROM %s WHERE role_id = %d""".formatted(tableName, role.getId());

		return findManyBy(connection, SQL_SELECT_BY_ROLE, logger);
	}

	@Override
	public Optional<User> findByLogin(String login) {
		String SQL_SELECT_BY_LOGIN = """
				SELECT id, login, password, first_name, last_name, mail, role_id, \
				residency_id, person_id, is_enable, created, modified
				FROM %s WHERE login = '%s'""".formatted(tableName, login);

		return findOneBy(connection, SQL_SELECT_BY_LOGIN, logger);
	}

	@Override
	public Optional<User> findByLoginAndPassword(String login, String password) {
		String SQL_SELECT_BY_LOGIN_AND_PASS = """
				SELECT id, login, password, first_name, last_name, mail, role_id, \
				residency_id, person_id, is_enable, created, modified
				FROM %s WHERE login = '%s' AND password = '%s'""".formatted(tableName, login, password);

		return findOneBy(connection, SQL_SELECT_BY_LOGIN_AND_PASS, logger);
	}

	@Override
	public void changeEnable(String login, Boolean enable) throws SQLException {
		String SQL_SET_ENABLE = "UPDATE %s SET is_enable = ? WHERE login = '%s'".
				formatted(tableName, login);

		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SET_ENABLE)) {
			preparedStatement.setBoolean(1, enable);
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			logger.warning(e.getMessage());
			connection.rollback();
		}
	}
}
