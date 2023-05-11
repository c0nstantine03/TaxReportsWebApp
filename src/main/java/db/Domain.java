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
		DataSource dataSource = DataSource.getInstance();
		DaoFactory daoFactory = DaoFactory.getInstance();

		try (Connection connection = dataSource.getConnection()) {
			Thread.sleep(500);
			ReportHistoryDao reportHistoryDao = daoFactory.createReportHistoryDao(connection);
			List<ReportHistory> reportHistoryList = reportHistoryDao.getAll();
			if (reportHistoryList.isEmpty()) {
				System.out.println("There isn't any reports in history.");
			} else {
				for (ReportHistory report : reportHistoryList) {
					System.out.println(report);
				}
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
