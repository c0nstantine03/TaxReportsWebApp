package db.dao;

import db.entity.Personality;

import java.util.List;

public interface PersonalityDao extends DAO<Personality> {

    List<Personality> getAll();
}
