package org.project.taxreportswebapp.db.dao;

import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

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
