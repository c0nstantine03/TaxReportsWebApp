package org.project.trwa.db;

import lombok.Getter;
import org.project.trwa.db.dao.RoleDao;
import org.project.trwa.db.dao.factory.DaoFactory;
import org.project.trwa.db.entity.Role;

import java.util.List;

@Getter
public class TestRoleDao implements Runnable {
	private final Thread thread;

	public TestRoleDao() {
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		DaoFactory daoFactory = DaoFactory.getInstance();

		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		RoleDao roleDao = daoFactory.createRoleDao();
		List<Role> personalityList = roleDao.getAll();
		if (personalityList.isEmpty()) {
			System.out.println("There isn't any roles.");
		} else {
			for (Role role : personalityList) {
				System.out.println(role);
			}
			System.out.println();
		}
	}
}
