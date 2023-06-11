package org.project.trwa.db.dao.impl.jba;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.project.trwa.db.dao.PersonalityDao;
import org.project.trwa.db.entity.Personality;
import java.util.List;
import java.util.Optional;

public class PersonalityDaoImpl implements PersonalityDao {
    private final EntityManager entityManager;

    public PersonalityDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Personality> insert(Personality entity) {
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
    public Optional<Personality> findById(Long id) {
        return Optional.of(entityManager.find(Personality.class, id));
    }

    @Override
    public void update(Personality entity) {
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
            entityManager.remove(entityManager.find(Personality.class, id));
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public List<Personality> getAll() {
        return entityManager.createQuery("SELECT p FROM Personality p", Personality.class).getResultList();
    }

    @Override
    public Optional<Personality> findByCode(String code) {
        return Optional.of(entityManager.find(Personality.class, code));
    }
}
