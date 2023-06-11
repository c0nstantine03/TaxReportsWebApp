package org.project.trwa.db.dao.impl.jba;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.project.trwa.db.dao.ReportDao;
import org.project.trwa.db.entity.Report;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ReportDaoImpl implements ReportDao {
	private final EntityManager entityManager;

	public ReportDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Optional<Report> insert(Report entity) {
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
	public Optional<Report> findById(Long id) {
		return Optional.of(entityManager.find(Report.class, id));
	}

	@Override
	public void update(Report entity) {
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
			entityManager.remove(entityManager.find(Report.class, id));
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw e;
		}
	}

	@Override
	public List<Report> getAll() {
		return entityManager.createQuery("SELECT r FROM Report r", Report.class).getResultList();
	}

	@Override
	public Optional<Report> findByCode(String code) {
		return Optional.of(entityManager.find(Report.class, code));
	}

	@Override
	public List<Report> findForAuthor(Long id) {
		return entityManager.createQuery("SELECT r FROM Report r WHERE r.author.id = :id", Report.class)
				.setParameter("id", id).getResultList();
	}

	@Override
	public List<Report> findForInspector(Long id) {
		return entityManager.createQuery("SELECT r FROM Report r WHERE r.inspector.id = :id", Report.class)
				.setParameter("id", id).getResultList();
	}
}
