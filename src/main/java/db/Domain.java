package db;

import db.dao.*;
import db.dao.factory.DaoFactory;
import db.dao.impl.conn.DataSource;
import db.entity.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Domain {
	public static void main(String[] args) {
		TestPersonalityDao testPersonalityDao = new TestPersonalityDao();
		TestRoleDao testRoleDao = new TestRoleDao();
		TestResidencyDao testResidencyDao = new TestResidencyDao();
		TestStatusDao testStatusDao = new TestStatusDao();
		TestReportsDao testReportsDao = new TestReportsDao();

		try {
			testPersonalityDao.getThread().join();
			testRoleDao.getThread().join();
			testResidencyDao.getThread().join();
			testStatusDao.getThread().join();
			testReportsDao.getThread().join();
		} catch (InterruptedException e) {
			e.printStackTrace(System.err);
		}
		System.out.println();
	}
}
