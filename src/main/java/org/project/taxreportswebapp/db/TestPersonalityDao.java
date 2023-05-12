package org.project.taxreportswebapp.db;

import org.project.taxreportswebapp.db.dao.PersonalityDao;
import org.project.taxreportswebapp.db.dao.factory.DaoFactory;
import org.project.taxreportswebapp.db.dao.impl.conn.DataSource;
import org.project.taxreportswebapp.db.entity.Personality;
import lombok.Getter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Getter
public class TestPersonalityDao implements Runnable {
	private final Thread thread;

	public TestPersonalityDao() {
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		DataSource dataSource = DataSource.getInstance();
		DaoFactory daoFactory = DaoFactory.getInstance();

		try (Connection connection = dataSource.getConnection()) {
			Thread.sleep(100);
			PersonalityDao personalityDao = daoFactory.createPersonalityDao(connection);
			List<Personality> personalityList = personalityDao.getAll();
			if (personalityList.isEmpty()) {
				System.out.println("There isn't any personalities.");
			} else {
				for (Personality psn : personalityList) {
					System.out.println(psn);
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
