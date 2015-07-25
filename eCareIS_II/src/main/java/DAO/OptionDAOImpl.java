package DAO;

import entity.Option;
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
public class OptionDAOImpl implements OptionDAO  {
    Logger logger = Logger.getLogger(OptionDAOImpl.class);

    @Autowired
    private EntityManager em;


    @Override
    @SuppressWarnings("unchecked")
    public List<Option> getAll() {
//        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("SELECT a FROM Option a");
        List<Option> entities = query.getResultList();

        logger.info("getAll()");
        return entities;
    }

    @Override
    public Option get(final Serializable id) {
        logger.info("get() with id:" + id);
//        EntityManager em = emf.createEntityManager();

        Option entity = em.find(Option.class, id);
        if (entity == null) {
            throw new EntityNotFoundException("Not found Option for ID "
                    + id);
        }
        return entity;
    }

    @Override
    public void delete(final Serializable id) {
        logger.info("delete() with id:" + id);
//        EntityTransaction transaction = null;
//        try {
            Option entity = em.find(Option.class, id);
//            if (entity == null) {
//                throw new EntityNotFoundException("Not found option for ID "
//                        + id);
//            }
            em.remove(entity);
//        } catch (PersistenceException pex) {
//            logger.warn(pex);
//            pex.printStackTrace();
//        } finally {
////            em.close();
//        }

    }

    @Override
    public Option update(final Option entity) {
        logger.info("update():" + entity);
//        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = null;
        Option storedEntity = null;
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
//            em.close();
        }
        return storedEntity;
    }

    @Override
    public void add(Option entity) {
        logger.info("add option id:" + entity);
        em.persist(entity);
    }
}
