package db.dao;

public interface GenericDao<T> {

	//create
	void create(T entity);

	//read
	T findById(Long id);

	//update
	void update(T entity);

	//delete
	void delete(T entity);
}
