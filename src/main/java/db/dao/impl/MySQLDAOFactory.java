package db.dao.impl;

import db.dao.*;
import db.dao.impl.mysql.*;

import java.sql.Connection;

public class MySQLDAOFactory extends DAOFactory {

	@Override
	public PersonDAO createPersonDAO(Connection connection) {
		return new PersonDAOImpl(connection);
	}

	@Override
	public RoleDAO createRoleDAO(Connection connection) {
		return new RoleDAOImpl(connection);
	}

}
