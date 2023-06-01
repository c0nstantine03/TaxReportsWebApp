package org.project.trwa.db.dao.factory;

import org.project.trwa.db.dao.impl.conn.DataSource;
import org.project.trwa.db.dao.impl.jdbc.*;
import org.project.trwa.db.dao.*;


public class JdbcDaoFactory extends DaoFactory {

	JdbcDaoFactory() {}

	@Override
	public RoleDao createRoleDao() {
		return new RoleDaoImpl(DataSource.getInstance().getConnection());
	}

	@Override
	public ResidencyDao createResidencyDao() {
		return new ResidencyDaoImpl(DataSource.getInstance().getConnection());
	}

	@Override
	public PersonalityDao createPersonalityDao() {
		return new PersonalityDaoImpl(DataSource.getInstance().getConnection());
	}

	@Override
	public UserDao createUserDao() {
		return new UserDaoImpl(DataSource.getInstance().getConnection());
	}

	@Override
	public StatusDao createStatusDao() {
		return new StatusDaoImpl(DataSource.getInstance().getConnection());
	}

	public NextStatusDao createNextStatusDao() {
		return new NextStatusDaoImpl(DataSource.getInstance().getConnection());
	}

	@Override
	public ReportDao createReportDao() {
		return new ReportDaoImpl(DataSource.getInstance().getConnection());
	}

	@Override
	public ReportHistoryDao createReportHistoryDao() {
		return new ReportHistoryDaoImpl(DataSource.getInstance().getConnection());
	}
}
