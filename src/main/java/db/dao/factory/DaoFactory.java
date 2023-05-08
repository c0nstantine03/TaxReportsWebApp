package db.dao.factory;

import db.dao.PersonalityDao;
import db.dao.RoleDao;
import db.dao.impl.conn.DataSource;


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

	public abstract PersonalityDao createPersonDao();

	public abstract RoleDao createRoleDao();
}
