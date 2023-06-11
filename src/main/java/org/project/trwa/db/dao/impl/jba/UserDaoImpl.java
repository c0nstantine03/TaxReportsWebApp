package org.project.trwa.db.dao.impl.jba;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.project.trwa.db.dao.UserDao;
import org.project.trwa.db.entity.Role;
import org.project.trwa.db.entity.User;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
	private final EntityManager entityManager;

	public UserDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Optional<User> insert(User entity) {
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
	public Optional<User> findById(Long id) {
		return Optional.of(entityManager.find(User.class, id));
	}

	@Override
	public void update(User entity) {
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
	public void delete(Long id) throws SQLException {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.remove(entityManager.find(User.class, id));
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw e;
		}
	}

	@Override
	public List<User> getAll() {
		return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
	}

	@Override
	public List<User> getByRole(Role role) {
		return entityManager.createQuery("SELECT u FROM User u WHERE u.role.code = :code", User.class)
				.setParameter("code", role.getCode()).getResultList();
	}

	@Override
	public Optional<User> findByLogin(String login) {
		return Optional.of(entityManager.find(User.class, login));
	}

	@Override
	public Optional<User> findByLoginAndPassword(String login, String password) {
		return Optional.of(entityManager.createQuery("SELECT u FROM User u WHERE u.login = :login AND u.password = :password", User.class).
				setParameter("login", login).setParameter("password", password).getSingleResult());
	}
}
