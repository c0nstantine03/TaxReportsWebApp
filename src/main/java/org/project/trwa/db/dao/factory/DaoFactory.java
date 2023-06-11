package org.project.trwa.db.dao.factory;

import org.project.trwa.db.dao.ReportHistoryDao;
import org.project.trwa.db.dao.StatusDao;
import org.project.trwa.db.dao.ResidencyDao;
import org.project.trwa.db.dao.UserDao;
import org.project.trwa.db.dao.RoleDao;
import org.project.trwa.db.dao.PersonalityDao;
import org.project.trwa.db.dao.ReportDao;
import org.project.trwa.db.dao.NextStatusDao;


public abstract class DaoFactory {
	private static volatile DaoFactory instance;

	// Double-Checked Instantiation
	public static DaoFactory getInstance() {
		DaoFactory localInstance = instance;
		if (localInstance == null) {
			synchronized (DaoFactory.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new JbaDaoFactory();
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
