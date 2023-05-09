package db.dao.factory;

import db.dao.*;
import db.dao.impl.conn.DataSource;
import db.dao.impl.jdbc.*;


public class JdbcDaoFactory extends DaoFactory {
	private final DataSource dataSource;

	JdbcDaoFactory() {
		this.dataSource = DataSource.getInstance();
	}

	@Override
	public RoleDao createRoleDao() {
		return new RoleDaoImpl(dataSource.getConnection());
	}

	@Override
	public ResidencyDao createResidencyDao() {
		return new ResidencyDaoImpl(dataSource.getConnection());
	}

	@Override
	public PersonalityDao createPersonalityDao() {
		return new PersonalityDaoImpl(dataSource.getConnection());
	}

	@Override
	public UserDao createUserDao() {
		return new UserDaoImpl(dataSource.getConnection());
	}

	@Override
	public StatusDao createStatusDao() {
		return new StatusDaoImpl(dataSource.getConnection());
	}

	public NextStatusDao createNextStatusDao() {
		return new NextStatusDaoImpl(dataSource.getConnection());
	}
}
