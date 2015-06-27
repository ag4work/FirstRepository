package DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Alexey on 24.06.2015.
 */
public class EntityManagerFactorySingleton {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("EcarePU");

    private EntityManagerFactorySingleton(){
    }

    public static EntityManagerFactory getInstance(){
        return emf;
    }
}
