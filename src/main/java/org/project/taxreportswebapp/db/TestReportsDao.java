package org.project.taxreportswebapp.db;

import org.project.taxreportswebapp.db.dao.ReportDao;
import org.project.taxreportswebapp.db.dao.factory.DaoFactory;
import org.project.taxreportswebapp.db.dao.impl.conn.DataSource;
import org.project.taxreportswebapp.db.entity.Report;
import org.project.taxreportswebapp.db.entity.Status;
import org.project.taxreportswebapp.db.entity.User;
import lombok.Getter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Getter
public class TestReportsDao implements Runnable {
	private final Thread thread;

	public TestReportsDao() {
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		DataSource dataSource = DataSource.getInstance();
		DaoFactory daoFactory = DaoFactory.getInstance();

		for (Long i = 0L; i < 5; i++) {
			try (Connection connection = dataSource.getConnection()) {
				Thread.sleep(500);
				ReportDao reportDao = daoFactory.createReportDao(connection);
				reportDao.insert(
						new Report(0L, "#test" + i,
								"There is some content of report",
								 new User(i % 2 + 1), null, null,
								null, new Status(1L), null)
				);
				List<Report> reportList = reportDao.getAll();
				if (reportList.isEmpty()) {
					System.out.println("There isn't any reports.");
				} else {
					for (Report report : reportList) {
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
}
