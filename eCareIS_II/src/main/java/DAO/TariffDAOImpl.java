package DAO;


import entity.Tariff;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Alexey on 01.07.2015.
 */

@Repository
public class TariffDAOImpl  implements TariffDAO {
    Logger logger = Logger.getLogger(TariffDAOImpl.class);

    @Autowired
    private EntityManager em;


    @Override
    @SuppressWarnings("unchecked")
    public List<Tariff> getAll() {
//        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("SELECT a FROM Tariff a");
        List<Tariff> entities = query.getResultList();

        logger.info("getAll()");
        return entities;
    }

    @Override
    public Tariff get(final Serializable id) {
        logger.info("get() with id:" + id);
//        EntityManager em = emf.createEntityManager();

        Tariff entity = em.find(Tariff.class, id);
        if (entity == null) {
            throw new EntityNotFoundException("Not found Tariff for ID "
                    + id);
        }
        return entity;
    }

    @Override
    public void delete(final Serializable id) {
        logger.info("delete() with id:" + id);
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();
            Tariff entity = em.find(Tariff.class, id);
            if (entity == null) {
                throw new EntityNotFoundException("Not found Tariff for ID "
                        + id);
            }
            em.remove(entity);
            transaction.commit();
        } catch (PersistenceException pex) {
            logger.warn(pex);
            pex.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            em.close();
        }
    }

    @Override
    public Tariff update(final Tariff entity) {
        logger.info("update():" + entity);
//        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = null;
        Tariff storedEntity = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();
            storedEntity = em.merge(entity);
            transaction.commit();
        } catch (PersistenceException pex) {
            logger.warn(pex);
            pex.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        } finally {
            em.close();
        }
        return storedEntity;
    }

    @Override
    public void add(Tariff entity) {
        logger.info("add():" + entity);
//        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(entity);
            transaction.commit();
        } catch (PersistenceException pex) {
            logger.warn(pex);
            pex.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        } finally {
            em.close();

        }
    }

}
