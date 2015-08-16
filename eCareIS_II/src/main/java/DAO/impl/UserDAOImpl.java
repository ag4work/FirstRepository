package dao.impl;

import dao.UserDAO;
import entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Created by Alexey on 25.06.2015.
 */
@Repository
public class UserDAOImpl implements UserDAO {

    Logger logger = Logger.getLogger(UserDAOImpl.class);

    @Autowired
    private EntityManager em;


    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAll() {
        Query query = em.createQuery("SELECT a FROM User a");
        List<User> entities = query.getResultList();

        logger.info("getAll User");
        return entities;
    }

    @Override
    public User get(final Serializable id) {
        logger.info("get User with id:" + id);
        User entity = em.find(User.class, id);
        return entity;
    }

    @Override
    public void delete(final Serializable id) {
        logger.info("delete User with id:" + id);
        User entity = em.find(User.class, id);
        em.remove(entity);
    }

    @Override
    public User update(final User entity) {
        logger.info("update User:" + entity);
        User storedEntity = null;
        storedEntity = em.merge(entity);
        return storedEntity;
    }

    @Override
    public void add(User entity) {
        logger.info("add User:" + entity);
        em.persist(entity);
    }

    @Override
    public List<User> getUsers(Integer page, Integer usersPerPage){
        try {
            Query query = em.createQuery("SELECT u FROM User u ORDER BY u.id ASC");
            query.setFirstResult((page - 1) * usersPerPage);
            query.setMaxResults(usersPerPage);
            return query.getResultList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public Long getUserCount(){
        Query query = em.createQuery("SELECT COUNT(u) FROM User u");
        return (Long)query.getSingleResult();
    }


}

