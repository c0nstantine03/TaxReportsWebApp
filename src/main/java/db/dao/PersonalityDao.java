package db.dao;

import db.entity.Personality;

import java.util.List;
import java.util.Optional;

public interface PersonalityDao extends DAO<Personality> {

    List<Personality> getAll();

    Optional<Personality> findByCode(String code);
}
