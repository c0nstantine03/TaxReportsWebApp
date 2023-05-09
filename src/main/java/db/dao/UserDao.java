package db.dao;

import db.entity.User;

import java.util.List;

public interface UserDao extends DAO<User> {

	User findById(Long id);

	List<User> getAll();

	User findByLogin(String login);

	User findByLoginAndPassword(String login, String password);

	void setEnable(Long id, Boolean enable);
}
