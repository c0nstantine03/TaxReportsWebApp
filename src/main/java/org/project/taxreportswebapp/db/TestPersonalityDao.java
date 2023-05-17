package org.project.taxreportswebapp.db;

import lombok.Getter;
import org.project.taxreportswebapp.db.dao.PersonalityDao;
import org.project.taxreportswebapp.db.dao.factory.DaoFactory;
import org.project.taxreportswebapp.db.entity.Personality;

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
		DaoFactory daoFactory = DaoFactory.getInstance();

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		PersonalityDao personalityDao = daoFactory.createPersonalityDao();
		List<Personality> personalityList = personalityDao.getAll();
		if (personalityList.isEmpty()) {
			System.out.println("There isn't any personalities.");
		} else {
			for (Personality psn : personalityList) {
				System.out.println(psn);
			}
			System.out.println();
		}
	}
}
