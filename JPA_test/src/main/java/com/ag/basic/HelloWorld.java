package com.ag.basic;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


public class HelloWorld {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SchoolPU");
        EntityManager em = emf.createEntityManager();

        List<User> users = em.createQuery("select u from User u", User.class).getResultList();

        for(User u:users){
            System.out.println(u);
        }

        em.close();
        emf.close();
    }
}
