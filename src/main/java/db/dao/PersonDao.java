package db.dao;

import db.entity.Person;

import java.util.List;

public interface PersonDao extends GenericDao<Person> {

    List<Person> getAll();
}
