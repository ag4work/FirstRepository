package DAO;

import entity.Contract;
import entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Alexey on 23.06.2015.
 */
public class UserDAOImpl implements  UserDAO {
    EntityManagerFactory emf;

    public UserDAOImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void add(User user){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
    }
    public void update(User user){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
    }
    public void delete(User user){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }

    }
    public User getById(Integer id){
        EntityManager em = emf.createEntityManager();
        return em.find(User.class,id);
    }


    public List<User> getAll(){
        EntityManager em = emf.createEntityManager();
        Query query =  em.createQuery("select a from User a");
        return query.getResultList();

    }



}