package db.dao;

import db.entity.Status;

import java.util.List;
import java.util.Optional;

public interface StatusDao extends DAO<Status> {

	List<Status> getAll();

	Optional<Status> findByCode(String code);
}
