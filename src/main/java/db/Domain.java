package db;

import db.dao.*;
import db.dao.factory.DaoFactory;
import db.entity.Personality;

import java.util.List;

public class Domain {
    public static void main(String[] args) {

        DaoFactory daoFactory = DaoFactory.getInstance();

        PersonalityDao personalityDao = daoFactory.createPersonalityDao();
        List<Personality> personalityList = personalityDao.getAll();
        if (personalityList.isEmpty()) {
            System.out.println("There isn't any personalities.");
        } else {
            for (Personality psn : personalityList) {
                System.out.println(psn);
            }
        }
        RoleDao roleDao = daoFactory.createRoleDao();

    }
}
