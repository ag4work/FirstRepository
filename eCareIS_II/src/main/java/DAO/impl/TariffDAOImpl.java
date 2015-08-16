package dao.impl;


import dao.TariffDAO;
import entity.Contract;
import entity.Tariff;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        Query query = em.createQuery("SELECT a FROM Tariff a");
        List<Tariff> entities = query.getResultList();

        logger.info("getAll()");
        return entities;
    }

    @Override
    public Tariff get(final Serializable id) {
        logger.info("get tariff with id:" + id);
        Tariff entity = em.find(Tariff.class, id);
        return entity;
    }

    @Override
    public void delete(final Serializable id) {
        logger.info("delete tariff with id:" + id);
        Tariff entity = em.find(Tariff.class, id);
        em.remove(entity);
    }

    @Override
    public Tariff update(final Tariff entity) {
        logger.info("update tariff:" + entity);
        Tariff storedEntity = null;
        storedEntity = em.merge(entity);
        return storedEntity;
    }

    @Override
    public void add(Tariff entity) {
        logger.info("add tariff:" + entity);
        em.persist(entity);
    }

    @Override
    public Set<Contract> getContractsByTariff(Integer tariffId){
        Contract contract = null;
        try {
            Tariff tariff = em.find(Tariff.class, tariffId);
            Query jpqlQuery = em.createQuery("SELECT c FROM Contract c WHERE c.tariff=:tariff");
            jpqlQuery.setParameter("tariff", tariff);
            return new HashSet<Contract>(
                    (List<Contract>)jpqlQuery.getResultList());
        } catch (Exception e) {
            logger.info("No contracts found for tariffId:" + tariffId, e);
        }
        return Collections.emptySet();
    }


}
