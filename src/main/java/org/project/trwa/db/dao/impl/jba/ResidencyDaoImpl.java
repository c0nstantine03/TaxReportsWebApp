package org.project.trwa.db.dao.impl.jba;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.project.trwa.db.dao.ResidencyDao;
import org.project.trwa.db.entity.Residency;
import java.util.List;
import java.util.Optional;

public class ResidencyDaoImpl implements ResidencyDao {
	private final EntityManager entityManager;

	public ResidencyDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Optional<Residency> insert(Residency entity) {
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
	public Optional<Residency> findById(Long id) {
		return Optional.of(entityManager.find(Residency.class, id));
	}

	@Override
	public void update(Residency entity) {
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
			entityManager.remove(entityManager.find(Residency.class, id));
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw e;
		}
	}

	@Override
	public List<Residency> getAll() {
		return entityManager.createQuery("SELECT r FROM Residency r", Residency.class).getResultList();
	}

	@Override
	public Optional<Residency> findByCode(String code) {
		return Optional.of(entityManager.find(Residency.class, code));
	}
}
