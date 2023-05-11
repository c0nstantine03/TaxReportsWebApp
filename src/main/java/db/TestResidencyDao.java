package db;

import db.dao.ResidencyDao;
import db.dao.factory.DaoFactory;
import db.dao.impl.conn.DataSource;
import db.entity.Residency;
import lombok.Getter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Getter
public class TestResidencyDao implements Runnable {
	private final Thread thread;

	public TestResidencyDao() {
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		DataSource dataSource = DataSource.getInstance();
		DaoFactory daoFactory = DaoFactory.getInstance();

		try (Connection connection = dataSource.getConnection()) {
			Thread.sleep(200);
			ResidencyDao roleDao = daoFactory.createResidencyDao(connection);
			List<Residency> personalityList = roleDao.getAll();
			if (personalityList.isEmpty()) {
				System.out.println("There isn't any residencies.");
			} else {
				for (Residency residency : personalityList) {
					System.out.println(residency);
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
