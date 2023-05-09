package db.dao;

import db.entity.NextStatus;

import java.util.List;

public interface NextStatusDao extends DAO<NextStatus> {

	List<NextStatus> getAll();

	List<NextStatus> findNextStatuses(Long id);
}
