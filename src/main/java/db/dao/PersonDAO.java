package db.dao;

import db.entity.Person;

import java.util.List;

public interface PersonDAO extends GenericDAO<Person> {

    List<Person> getAll();
}
