package org.project.trwa.db.dao.impl.jba;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.project.trwa.db.dao.NextStatusDao;
import org.project.trwa.db.entity.NextStatus;

import java.util.List;
import java.util.Optional;

public class NextStatusDaoImpl implements NextStatusDao {
	private final EntityManager entityManager;

	public NextStatusDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Optional<NextStatus> insert(NextStatus entity) {
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
	public Optional<NextStatus> findById(Long id) {
		return Optional.of(entityManager.find(NextStatus.class, id));
	}

	@Override
	public void update(NextStatus entity) {
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
			entityManager.remove(entityManager.find(NextStatus.class, id));
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw e;
		}
	}

	@Override
	public List<NextStatus> getAll() {
		return entityManager.createQuery("SELECT n FROM NextStatus n", NextStatus.class).getResultList();
	}

	@Override
	public List<NextStatus> findNextStatuses(Long statusId) {
		return entityManager.createQuery("SELECT n FROM NextStatus n WHERE n.currentStatus.id = :status_id", NextStatus.class)
				.setParameter("status_id", statusId).getResultList();
	}

	@Override
	public Optional<NextStatus> findByStatuses(NextStatus entity) {
		return Optional.of(entityManager.createQuery("SELECT n FROM NextStatus n " +
								"WHERE n.currentStatus.id = :status_id AND n.nextStatus.id = :next_id", NextStatus.class).
				setParameter("status_id", entity.getCurrentStatus().getId()).
				setParameter("next_id", entity.getNextStatus().getId()).getSingleResult());
	}
}
