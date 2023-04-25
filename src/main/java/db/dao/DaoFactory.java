package db.dao;

import db.dao.impl.JdbcDaoFactory;

import java.sql.Connection;

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

	public abstract PersonDao createPersonDao(Connection connection);

	public abstract RoleDao createRoleDao(Connection connection);
}
