package db;

import db.dao.*;
import db.dao.factory.DaoFactory;
import db.dao.impl.conn.DataSource;
import db.entity.Personality;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Domain {
	public static void main(String[] args) {
		DataSource dataSource = DataSource.getInstance();
		DaoFactory daoFactory = DaoFactory.getInstance();

		try (Connection connection = dataSource.getConnection()) {
			PersonalityDao personalityDao = daoFactory.createPersonalityDao(connection);
			List<Personality> personalityList = personalityDao.getAll();
			if (personalityList.isEmpty()) {
				System.out.println("There isn't any personalities.");
			} else {
				for (Personality psn : personalityList) {
					System.out.println(psn);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
