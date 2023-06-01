package org.project.trwa.db;

import lombok.Getter;
import org.project.trwa.db.dao.ResidencyDao;
import org.project.trwa.db.dao.factory.DaoFactory;
import org.project.trwa.db.entity.Residency;

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
		DaoFactory daoFactory = DaoFactory.getInstance();

		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		ResidencyDao roleDao = daoFactory.createResidencyDao();
		List<Residency> personalityList = roleDao.getAll();
		if (personalityList.isEmpty()) {
			System.out.println("There isn't any residencies.");
		} else {
			for (Residency residency : personalityList) {
				System.out.println(residency);
			}
			System.out.println();
		}
	}
}
