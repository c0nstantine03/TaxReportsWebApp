package org.project.taxreportswebapp.db.dao.factory;

import org.project.taxreportswebapp.db.dao.ReportHistoryDao;
import org.project.taxreportswebapp.db.dao.StatusDao;
import org.project.taxreportswebapp.db.dao.ResidencyDao;
import org.project.taxreportswebapp.db.dao.UserDao;
import org.project.taxreportswebapp.db.dao.RoleDao;
import org.project.taxreportswebapp.db.dao.PersonalityDao;
import org.project.taxreportswebapp.db.dao.ReportDao;
import org.project.taxreportswebapp.db.dao.NextStatusDao;


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

	public abstract RoleDao createRoleDao();

	public abstract ResidencyDao createResidencyDao();

	public abstract PersonalityDao createPersonalityDao();

	public abstract UserDao createUserDao();

	public abstract StatusDao createStatusDao();

	public abstract NextStatusDao createNextStatusDao();

	public abstract ReportDao createReportDao();

	public abstract ReportHistoryDao createReportHistoryDao();
}
