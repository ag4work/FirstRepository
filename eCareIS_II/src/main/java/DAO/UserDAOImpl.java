package DAO;

import entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Alexey on 25.06.2015.
 */
@Repository
public class UserDAOImpl implements UserDAO {

    //    private EntityManagerFactory emf;
    Logger logger = Logger.getLogger(UserDAOImpl.class);

    @Autowired
    private EntityManager em;


    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAll() {
//        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("SELECT a FROM User a");
        List<User> entities = query.getResultList();

        logger.info("getAll()");
        return entities;
    }

    @Override
    public User get(final Serializable id) {
        logger.info("get() with id:" + id);
//        EntityManager em = emf.createEntityManager();

        User entity = em.find(User.class, id);
        if (entity == null) {
            throw new EntityNotFoundException("Not found User for ID "
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
            User entity = em.find(User.class, id);
            if (entity == null) {
                throw new EntityNotFoundException("Not found User for ID "
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
    public User update(final User entity) {
        logger.info("update():" + entity);
//        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = null;
        User storedEntity = null;
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
    public void add(User entity) {
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

