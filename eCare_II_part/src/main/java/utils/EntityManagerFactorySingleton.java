package utils;

/**
 * Created by Alexey on 01.07.2015.
 */


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class EntityManagerFactorySingleton {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("EcarePU");

    private EntityManagerFactorySingleton(){
    }

    public static EntityManagerFactory getInstance(){
        return emf;
    }
}