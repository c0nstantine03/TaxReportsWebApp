package org.project.taxreportswebapp.db.dao.factory;

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


public abstract class DaoFactory {
	private static volatile DaoFactory instance;

	// Double-Checked Instantiation
	public static DaoFactory getInstance() {
		DaoFactory localInstance = instance;
		if (localInstance == null) {
			synchronized (DaoFactory.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new JdbcDaoFactory();
				}
			}
		}
		return localInstance;
	}

	public abstract RoleDao createRoleDao(Connection connection);

	public abstract ResidencyDao createResidencyDao(Connection connection);

	public abstract PersonalityDao createPersonalityDao(Connection connection);

	public abstract UserDao createUserDao(Connection connection);

	public abstract StatusDao createStatusDao(Connection connection);

	public abstract NextStatusDao createNextStatusDao(Connection connection);

	public abstract ReportDao createReportDao(Connection connection);

	public abstract ReportHistoryDao createReportHistoryDao(Connection connection);
}
