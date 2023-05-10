package db.dao;

import db.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao extends DAO<User> {

	List<User> getAll();

	User findByLogin(String login);

	User findByLoginAndPassword(String login, String password);

	void setEnable(String login, Boolean enable) throws SQLException;
}
