package org.project.trwa.db.dao;

import java.sql.SQLException;
import java.util.Optional;

interface DAO<T> {

	//create
	Optional<T> insert(T entity) throws SQLException;

	//read
	Optional<T> findById(Long id);

	//statementUpdate
	void update(T entity) throws SQLException;

	//delete
	void delete(Long id) throws SQLException;
}
