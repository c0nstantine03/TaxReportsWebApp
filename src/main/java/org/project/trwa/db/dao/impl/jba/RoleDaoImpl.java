package org.project.trwa.db.dao.impl.jba;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.project.trwa.db.dao.RoleDao;
import org.project.trwa.db.entity.Role;

import java.util.List;
import java.util.Optional;

public class RoleDaoImpl implements RoleDao {
	private final EntityManager entityManager;

	public RoleDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Optional<Role> insert(Role entity) {
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
	public Optional<Role> findById(Long id) {
		return Optional.of(entityManager.find(Role.class, id));
	}

	@Override
	public void update(Role entity) {
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
			entityManager.remove(entityManager.find(Role.class, id));
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw e;
		}
	}

	@Override
	public List<Role> getAll() {
		return entityManager.createQuery("SELECT r FROM Role r", Role.class).getResultList();
	}

	@Override
	public Optional<Role> findByCode(String code) {
		return Optional.of(entityManager.find(Role.class, code));
	}
}
