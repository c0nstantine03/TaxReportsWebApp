package db.dao;

import db.entity.Residency;

import java.util.List;

public interface ResidencyDao extends DAO<Residency> {

	List<Residency> getAll();

}
