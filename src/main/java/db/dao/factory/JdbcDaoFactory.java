package db.dao.factory;

import db.dao.*;
import db.dao.impl.jdbc.*;

import java.sql.Connection;
import java.sql.SQLException;


public class JdbcDaoFactory extends DaoFactory {

	JdbcDaoFactory() {}

	@Override
	public RoleDao createRoleDao(Connection connection) {
		return new RoleDaoImpl(connection);
	}

	@Override
	public ResidencyDao createResidencyDao(Connection connection) {
		return new ResidencyDaoImpl(connection);
	}

	@Override
	public PersonalityDao createPersonalityDao(Connection connection) {
		return new PersonalityDaoImpl(connection);
	}

	@Override
	public UserDao createUserDao(Connection connection) {
		return new UserDaoImpl(connection);
	}

	@Override
	public StatusDao createStatusDao(Connection connection) {
		return new StatusDaoImpl(connection);
	}

	public NextStatusDao createNextStatusDao(Connection connection) {
		return new NextStatusDaoImpl(connection);
	}

	@Override
	public ReportDao createReportDao(Connection connection) {
		return new ReportDaoImpl(connection);
	}

	@Override
	public ReportHistoryDao createReportHistoryDao(Connection connection) {
		return new ReportHistoryDaoImpl(connection);
	}
}
