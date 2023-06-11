package org.project.trwa.server.services.impl;

import org.project.trwa.db.dao.UserDao;
import org.project.trwa.db.dao.impl.conn.DataSource;
import org.project.trwa.db.dao.impl.jdbc.UserDaoImpl;
import org.project.trwa.db.entity.Role;
import org.project.trwa.db.entity.User;
import org.project.trwa.server.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public Optional<User> getUser(Long id) {
		try (Connection connection = DataSource.getInstance().getConnection()) {
			UserDao userDao = new UserDaoImpl(connection);
			return userDao.findById(id);
		} catch (Exception e) {
			logger.error("Error: " + e.getMessage());
		}
		return Optional.empty();
	}

	@Override
	public List<User> getAllUsers() {
		try (Connection connection = DataSource.getInstance().getConnection()) {
			UserDao userDao = new UserDaoImpl(connection);
			return userDao.getAll();
		} catch (Exception e) {
			logger.error("Error: " + e.getMessage());
		}
		return new ArrayList<>();
	}

	@Override
	public List<User> getUsersByRole(Role role) {
		try (Connection connection = DataSource.getInstance().getConnection()) {
			UserDao userDao = new UserDaoImpl(connection);
			return userDao.getByRole(role);
		} catch (Exception e) {
			logger.error("Error: " + e.getMessage());
		}
		return new ArrayList<>();
	}

	@Override
	public Optional<User> loginUser(String login, String password) {
		try (Connection connection = DataSource.getInstance().getConnection()) {
			UserDao userDao = new UserDaoImpl(connection);
			return userDao.findByLoginAndPassword(login, password);
		} catch (Exception e) {
			logger.error("Error: " + e.getMessage());
		}
		return Optional.empty();
	}

	@Override
	public Optional<User> registerUser(User user) {
		try (Connection connection = DataSource.getInstance().getConnection()) {
			UserDao userDao = new UserDaoImpl(connection);
			return userDao.insert(user);
		} catch (Exception e) {
			logger.error("Error: " + e.getMessage());
		}
		return Optional.empty();
	}

	@Override
	public void updateUser(User user) {
		try (Connection connection = DataSource.getInstance().getConnection()) {
			UserDao userDao = new UserDaoImpl(connection);
			userDao.update(user);
		} catch (Exception e) {
			logger.error("Error: " + e.getMessage());
		}
	}

	@Override
	public void deleteUser(Long id, String password) {
		try (Connection connection = DataSource.getInstance().getConnection()) {
			UserDao userDao = new UserDaoImpl(connection);
			User user = userDao.findById(id).orElse(new User(0L));
			if (user.getPassword().equals(password)) {
				userDao.delete(id);
			}
		} catch (Exception e) {
			logger.error("Error: " + e.getMessage());
		}
	}
}
