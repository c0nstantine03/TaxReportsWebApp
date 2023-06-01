package org.project.trwa.db.dao;

import org.project.trwa.db.entity.NextStatus;

import java.util.List;
import java.util.Optional;

public interface NextStatusDao extends DAO<NextStatus> {

	List<NextStatus> getAll();

	List<NextStatus> findNextStatuses(Long id);

	Optional<NextStatus> findByStatuses(NextStatus entity);
}
