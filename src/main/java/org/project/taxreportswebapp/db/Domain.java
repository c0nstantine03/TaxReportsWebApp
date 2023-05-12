package org.project.taxreportswebapp.db;

import org.project.taxreportswebapp.db.entity.ReportHistory;
import org.project.taxreportswebapp.db.dao.ReportHistoryDao;
import org.project.taxreportswebapp.db.dao.factory.DaoFactory;
import org.project.taxreportswebapp.db.dao.impl.conn.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
			Logger.getLogger(Domain.class.getName()).log(Level.WARNING, e.getMessage());
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
