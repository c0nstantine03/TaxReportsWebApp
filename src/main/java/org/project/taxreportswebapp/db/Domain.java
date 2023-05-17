package org.project.taxreportswebapp.db;

import org.project.taxreportswebapp.db.dao.ReportHistoryDao;
import org.project.taxreportswebapp.db.dao.factory.DaoFactory;
import org.project.taxreportswebapp.db.entity.ReportHistory;

import java.util.List;

public class Domain {
	public static void main(String[] args) throws InterruptedException {
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

		DaoFactory daoFactory = DaoFactory.getInstance();

		Thread.sleep(500);
		ReportHistoryDao reportHistoryDao = daoFactory.createReportHistoryDao();
		List<ReportHistory> reportHistoryList = reportHistoryDao.getAll();
		if (reportHistoryList.isEmpty()) {
			System.out.println("There isn't any reports in history.");
		} else {
			for (ReportHistory report : reportHistoryList) {
				System.out.println(report);
			}
			System.out.println();
		}
	}
}
