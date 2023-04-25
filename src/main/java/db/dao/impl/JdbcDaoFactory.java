package db.dao.impl;

import db.dao.*;
import db.dao.impl.mysql.*;

import java.sql.Connection;

public class JdbcDaoFactory extends DaoFactory {

	@Override
	public PersonDao createPersonDao(Connection connection) {
		return new PersonDaoImpl(connection);
	}

	@Override
	public RoleDao createRoleDao(Connection connection) {
		return new RoleDaoImpl(connection);
	}

}
