package DAO;

import entity.Contract;
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
public class ContractDAOImpl  implements ContractDAO  {
    //    private EntityManagerFactory emf;
    Logger logger = Logger.getLogger(ContractDAOImpl.class);

    @Autowired
    private EntityManager em;


    @Override
    @SuppressWarnings("unchecked")
    public List<Contract> getAll() {
//        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("SELECT a FROM Contract a");
        List<Contract> entities = query.getResultList();

        logger.info("getAll()");
        return entities;
    }

    @Override
    public Contract get(final Serializable id) {
        logger.info("get() with id:" + id);
//        EntityManager em = emf.createEntityManager();

        Contract entity = em.find(Contract.class, id);
        if (entity == null) {
            throw new EntityNotFoundException("Not found Contract for ID "
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
            Contract entity = em.find(Contract.class, id);
            if (entity == null) {
                throw new EntityNotFoundException("Not found Contract for ID "
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
    public Contract update(final Contract entity) {
        logger.info("update():" + entity);
//        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = null;
        Contract storedEntity = null;
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
    public void add(Contract entity) {
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
