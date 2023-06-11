package org.project.trwa.db.dao.impl.jba;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.project.trwa.db.dao.StatusDao;
import org.project.trwa.db.entity.Status;

import java.util.List;
import java.util.Optional;

public class StatusDaoImpl implements StatusDao {
	private final EntityManager entityManager;

	public StatusDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Optional<Status> insert(Status entity) {
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
	public Optional<Status> findById(Long id) {
		return Optional.of(entityManager.find(Status.class, id));
	}

	@Override
	public void update(Status entity) {
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
			entityManager.remove(entityManager.find(Status.class, id));
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw e;
		}
	}

	@Override
	public List<Status> getAll() {
		return entityManager.createQuery("SELECT s FROM Status s", Status.class).getResultList();
	}

	@Override
	public Optional<Status> findByCode(String code) {
		return Optional.of(entityManager.find(Status.class, code));
	}
}
