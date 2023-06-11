package org.project.trwa.db.dao;

import org.project.trwa.db.entity.Role;
import org.project.trwa.db.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserDao extends DAO<User> {

	List<User> getAll();

	List<User> getByRole(Role role);

	Optional<User> findByLogin(String login);

	Optional<User> findByLoginAndPassword(String login, String password);
}
