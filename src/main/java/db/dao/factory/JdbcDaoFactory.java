package db.dao.factory;

import db.dao.*;
import db.dao.impl.conn.DataSource;
import db.dao.impl.jdbc.*;


public class JdbcDaoFactory extends DaoFactory {
	private DataSource dataSource;

	JdbcDaoFactory() {
		this.dataSource = DataSource.getInstance();
	}

	@Override
	public PersonalityDao createPersonDao() {
		return new PersonalityDaoImpl(dataSource.getConnection());
	}

	@Override
	public RoleDao createRoleDao() {
		return new RoleDaoImpl(dataSource.getConnection());
	}

}
