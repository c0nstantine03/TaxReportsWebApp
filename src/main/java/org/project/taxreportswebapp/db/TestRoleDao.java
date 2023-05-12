package org.project.taxreportswebapp.db;

import org.project.taxreportswebapp.db.dao.RoleDao;
import org.project.taxreportswebapp.db.dao.factory.DaoFactory;
import org.project.taxreportswebapp.db.dao.impl.conn.DataSource;
import org.project.taxreportswebapp.db.entity.Role;
import lombok.Getter;

import java.sql.Connection;
import java.sql.SQLException;
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
		DataSource dataSource = DataSource.getInstance();
		DaoFactory daoFactory = DaoFactory.getInstance();

		try (Connection connection = dataSource.getConnection()) {
			Thread.sleep(300);
			RoleDao roleDao = daoFactory.createRoleDao(connection);
			List<Role> personalityList = roleDao.getAll();
			if (personalityList.isEmpty()) {
				System.out.println("There isn't any roles.");
			} else {
				for (Role role : personalityList) {
					System.out.println(role);
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
