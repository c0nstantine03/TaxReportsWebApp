package db;

import db.dao.StatusDao;
import db.dao.factory.DaoFactory;
import db.dao.impl.conn.DataSource;
import db.entity.Status;
import lombok.Getter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Getter
public class TestStatusDao implements Runnable {
	private final Thread thread;

	public TestStatusDao() {
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		DataSource dataSource = DataSource.getInstance();
		DaoFactory daoFactory = DaoFactory.getInstance();

		try (Connection connection = dataSource.getConnection()) {
			Thread.sleep(400);
			StatusDao statusDao = daoFactory.createStatusDao(connection);
			List<Status> personalityList = statusDao.getAll();
			if (personalityList.isEmpty()) {
				System.out.println("There isn't any statuses.");
			} else {
				for (Status status : personalityList) {
					System.out.println(status);
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
