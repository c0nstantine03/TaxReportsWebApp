package db.dao;

import db.entity.Status;

import java.util.List;

public interface StatusDao extends DAO<Status> {

	List<Status> getAll();

	Status findByCode(String code);
}
