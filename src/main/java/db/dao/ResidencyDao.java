package db.dao;

import db.entity.Residency;

import java.util.List;
import java.util.Optional;

public interface ResidencyDao extends DAO<Residency> {

	List<Residency> getAll();

	Optional<Residency> findByCode(String code);
}
