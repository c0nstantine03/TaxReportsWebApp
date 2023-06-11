package org.project.trwa.db.dao.impl.jba;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.project.trwa.db.dao.ReportHistoryDao;
import org.project.trwa.db.entity.ReportHistory;
import java.util.List;
import java.util.Optional;

public class ReportHistoryDaoImpl implements ReportHistoryDao {
	private final EntityManager entityManager;

	public ReportHistoryDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Optional<ReportHistory> insert(ReportHistory entity) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.persist(entity);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw e;
		}
		return Optional.of(entity);
	}

	@Override
	public Optional<ReportHistory> findById(Long id) {
		return Optional.of(entityManager.find(ReportHistory.class, id));
	}

	@Override
	public void update(ReportHistory entity) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.merge(entity);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw e;
		}
	}

	@Override
	public void delete(Long id) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.remove(entityManager.find(ReportHistory.class, id));
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw e;
		}
	}

	@Override
	public List<ReportHistory> getAll() {
		return entityManager.createQuery("SELECT r FROM ReportHistory r", ReportHistory.class).getResultList();
	}

	@Override
	public List<ReportHistory> findByCode(String code) {
		return entityManager.createQuery("SELECT r FROM ReportHistory r WHERE r.code = :code", ReportHistory.class)
				.setParameter("code", code).getResultList();
	}

	@Override
	public Optional<ReportHistory> findLastByCode(String code) {
		return Optional.of(entityManager.createQuery("SELECT r FROM ReportHistory r WHERE r.code = :code ORDER BY id DESC LIMIT 1",
						ReportHistory.class).setParameter("code", code).getSingleResult());
	}

	@Override
	public List<ReportHistory> findClosedForAuthor(Long id) {
		return entityManager.createQuery("SELECT r FROM ReportHistory r WHERE r.author.id = :id", ReportHistory.class)
				.setParameter("id", id).getResultList();
	}

	@Override
	public List<ReportHistory> findClosedForInspector(Long id) {
		return entityManager.createQuery("SELECT r FROM ReportHistory r WHERE r.inspector.id = :id", ReportHistory.class)
				.setParameter("id", id).getResultList();
	}
}
