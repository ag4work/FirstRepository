package DAO;

import entity.Contract;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;

/**
 * Created by Alexey on 23.06.2015.
 */
public class ContractDAOImpl implements  ContractDAO {
    EntityManagerFactory emf;

    public ContractDAOImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void add(Contract contract){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(contract);
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
    }
    public void update(Contract contract){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(contract);
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
    }
    public void delete(Contract contract){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(contract);
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }

    }
    public Contract getById(Integer id){
        EntityManager em = emf.createEntityManager();
        return em.find(Contract.class,id);
    }


    public List<Contract> getAll(){
        EntityManager em = emf.createEntityManager();
        Query query =  em.createQuery("select a from Contract a");
        return query.getResultList();

    }



}