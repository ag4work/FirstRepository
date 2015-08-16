package dao.impl;

import dao.OptionDAO;
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
public class OptionDAOImpl implements OptionDAO {
    Logger logger = Logger.getLogger(OptionDAOImpl.class);

    @Autowired
    private EntityManager em;


    @Override
    @SuppressWarnings("unchecked")
    public List<Option> getAll() {
        Query query = em.createQuery("SELECT a FROM Option a");
        List<Option> entities = query.getResultList();

        logger.info("getAll()");
        return entities;
    }

    @Override
    public Option get(final Serializable id) {
        logger.info("get() with id:" + id);
        Option entity = em.find(Option.class, id);
        if (entity == null) {
            throw new EntityNotFoundException("Not found Option for ID "
                    + id);
        }
        return entity;
    }

    @Override
    public void delete(final Serializable id) {
        logger.info("delete option with id:" + id);
        Option entity = em.find(Option.class, id);
        em.remove(entity);
    }

    @Override
    public Option update(final Option entity) {
        logger.info("update option: " + entity);
        Option storedEntity = null;
        storedEntity = em.merge(entity);
        return storedEntity;
    }

    @Override
    public void add(Option entity) {
        logger.info("add option id:" + entity);
        em.persist(entity);
    }
}
