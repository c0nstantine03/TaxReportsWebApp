package db.dao;

import java.util.List;

public interface GenericDAO<T> {

	//create
	void add(T entity);

	//read
	T getById(Long id);

	//update
	void update(T entity);

	//delete
	void delete(T entity);
}
