package db;

import db.dao.*;
import db.dao.impl.DataSource;

public class Domain {
    public static void main(String[] args) {

        PersonDao personDAO = DaoFactory.getInstance().createPersonDao(DataSource.getConnection());
        //List<Person> personList = personDAO.getAll();
        //RoleDAO roleDAO = DAOFactory.getInstance().createRoleDAO(DataSource.getConnection());

    }
}
