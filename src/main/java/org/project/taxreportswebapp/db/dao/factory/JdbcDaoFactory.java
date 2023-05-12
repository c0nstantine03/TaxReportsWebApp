package org.project.taxreportswebapp.db.dao.factory;

import org.project.taxreportswebapp.db.dao.impl.jdbc.PersonalityDaoImpl;
import org.project.taxreportswebapp.db.dao.impl.jdbc.ReportDaoImpl;
import org.project.taxreportswebapp.db.dao.impl.jdbc.RoleDaoImpl;
import org.project.taxreportswebapp.db.dao.impl.jdbc.NextStatusDaoImpl;
import org.project.taxreportswebapp.db.dao.impl.jdbc.UserDaoImpl;
import org.project.taxreportswebapp.db.dao.impl.jdbc.ResidencyDaoImpl;
import org.project.taxreportswebapp.db.dao.impl.jdbc.ReportHistoryDaoImpl;
import org.project.taxreportswebapp.db.dao.impl.jdbc.StatusDaoImpl;
import org.project.taxreportswebapp.db.dao.ReportHistoryDao;
import org.project.taxreportswebapp.db.dao.StatusDao;
import org.project.taxreportswebapp.db.dao.ResidencyDao;
import org.project.taxreportswebapp.db.dao.UserDao;
import org.project.taxreportswebapp.db.dao.RoleDao;
import org.project.taxreportswebapp.db.dao.PersonalityDao;
import org.project.taxreportswebapp.db.dao.ReportDao;
import org.project.taxreportswebapp.db.dao.NextStatusDao;

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
