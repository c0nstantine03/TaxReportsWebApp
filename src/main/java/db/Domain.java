package db;

import db.dao.*;
import db.dao.impl.DataSource;
import db.entity.Person;

import java.util.List;

public class Domain {
    public static void main(String[] args) {

        PersonDAO personDAO = DAOFactory.getInstance().createPersonDAO(DataSource.getConnection());
        //List<Person> personList = personDAO.getAll();
        //RoleDAO roleDAO = DAOFactory.getInstance().createRoleDAO(DataSource.getConnection());

    }
}
