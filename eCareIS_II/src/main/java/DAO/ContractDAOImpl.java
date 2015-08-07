package dao;

import entity.Contract;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Repository
public class ContractDAOImpl  implements ContractDAO  {

    Logger logger = Logger.getLogger(ContractDAOImpl.class);

    @Autowired
    private EntityManager em;

    @Override
    public Contract getContractByPhonenumber(Long number){
        Contract contract = null;
        try {
            Query jpqlQuery = em.createQuery("SELECT c FROM Contract c WHERE c.phoneNumber=:number");
            jpqlQuery.setParameter("number", number);
            contract = (Contract) jpqlQuery.getSingleResult();
            logger.info("Contract with number " + number + "found");
        } catch (Exception e) {
            logger.info("Contract with number " + number + "not found");
        }
        return contract;
    }

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
            Contract entity = em.find(Contract.class, id);
            em.remove(entity);
    }

    @Override
    public Contract update(final Contract entity) {
        logger.info("trying to update contract with id:" + entity.getContractId());
        Contract storedEntity = null;
        storedEntity = em.merge(entity);
        logger.info("contract with id:" + entity.getContractId()+ " updated");
        return storedEntity;
    }

    @Override
    public void add(Contract entity) {
        logger.info("trying to add contract with phonenumber:" + entity.getPhoneNumber());
        em.persist(entity);
        logger.info("contract with with phonenumber:" + entity.getPhoneNumber() + " added");
    }

}
