package db.dao;

public interface DAO<T> {

	//create
	void insert(T entity);

	//read
	T findById(Long id);

	//update
	void update(T entity);

	//delete
	void delete(T entity);
}
