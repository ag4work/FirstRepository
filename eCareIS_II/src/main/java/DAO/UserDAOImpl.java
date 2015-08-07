package dao;

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
        User entity = em.find(User.class, id);
        return entity;
    }

    @Override
    public void delete(final Serializable id) {
        logger.info("delete() with id:" + id);
        EntityTransaction transaction = null;
        User entity = em.find(User.class, id);
        em.remove(entity);
    }

    @Override
    public User update(final User entity) {
        logger.info("update():" + entity);
        User storedEntity = null;
        storedEntity = em.merge(entity);
        return storedEntity;
    }

    @Override
    public void add(User entity) {
        logger.info("add():" + entity);
        em.persist(entity);
    }
}

