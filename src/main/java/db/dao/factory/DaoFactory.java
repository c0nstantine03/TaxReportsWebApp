package db.dao.factory;

import db.dao.*;


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
}
