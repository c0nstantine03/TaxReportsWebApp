package db.dao;

import db.dao.impl.MySQLDAOFactory;

import java.sql.Connection;

public abstract class DAOFactory {
	private static volatile DAOFactory instance;

	public static DAOFactory getInstance() {
		DAOFactory localInstance = instance;
		if (localInstance == null) {
			synchronized (DAOFactory.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new MySQLDAOFactory();
				}
			}
		}
		return localInstance;
	}

	public abstract PersonDAO createPersonDAO(Connection connection);

	public abstract RoleDAO createRoleDAO(Connection connection);
}
