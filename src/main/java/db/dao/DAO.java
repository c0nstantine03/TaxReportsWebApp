package db.dao;

import java.sql.SQLException;

public interface DAO<T> {

	//create
	void insert(T entity) throws SQLException;

	//read
	T findById(Long id);

	//update
	void update(T entity) throws SQLException;

	//delete
	void delete(T entity) throws SQLException;
}
