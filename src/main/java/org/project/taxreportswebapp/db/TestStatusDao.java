package org.project.taxreportswebapp.db;

import org.project.taxreportswebapp.db.dao.StatusDao;
import org.project.taxreportswebapp.db.dao.factory.DaoFactory;
import org.project.taxreportswebapp.db.entity.Status;
import lombok.Getter;

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
		DaoFactory daoFactory = DaoFactory.getInstance();

		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		StatusDao statusDao = daoFactory.createStatusDao();
		List<Status> personalityList = statusDao.getAll();
		if (personalityList.isEmpty()) {
			System.out.println("There isn't any statuses.");
		} else {
			for (Status status : personalityList) {
				System.out.println(status);
			}
			System.out.println();
		}
	}
}
