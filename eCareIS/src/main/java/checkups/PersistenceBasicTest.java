package checkups;


import entity.Contract;
import entity.Tariff;
import entity.User;
import utils.Constants;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

/**
 * Created by Alexey on 23.06.2015.
 */
public class PersistenceBasicTest {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("EcarePU");
    static EntityManager em = emf.createEntityManager();
    public static void main(String[] args) {
//        for (User user : getUsers(4,6)) {
//            System.out.println(user);
//        }
        Integer usersPerPage = 23;
        Integer userCount = (int) (long) getUserCount();
        Integer pageCount = userCount / usersPerPage+1;
        for (int page = 1; page <= pageCount; page++ ){
            for (User user : getUsers(page,usersPerPage)) {
                System.out.println(user);
            }
            System.out.println();
        }


        em.close();
        emf.close();
    }

//    Query query = em.createQuery("SELECT u FROM User u ORDER BY u.id ASC");
//    query.setFirstResult(3);
//    query.setMaxResults(5);
//    List<User> users = query.getResultList();
//    System.out.println(users);

    public static List<User> getUsers(Integer page, Integer usersPerPage){
        try {
            Query query = em.createQuery("SELECT u FROM User u ORDER BY u.id ASC");
            query.setFirstResult((page - 1) * usersPerPage);
            query.setMaxResults(usersPerPage);
            return query.getResultList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public static Long getUserCount(){
        Query query = em.createQuery("SELECT COUNT(u) FROM User u");
        return (Long)query.getSingleResult();
    }

    public static List<Contract> getContractByTariff(Integer tariffId){
        Contract contract = null;
        try {
            Tariff tariff = em.find(Tariff.class, tariffId);
            Query jpqlQuery = em.createQuery("SELECT c FROM Contract c WHERE c.tariff=:tariff");
            jpqlQuery.setParameter("tariff", tariff);
            return (List<Contract>)jpqlQuery.getResultList();
        } catch (Exception e) {
            System.out.println(e);
        }
        return Collections.emptyList();
    }


    public static Contract getContractByPhonenumber(Long number){
        Contract contract = null;
        try {
            Query jpqlQuery = em.createQuery("SELECT c FROM Contract c WHERE c.phoneNumber=:number");
            jpqlQuery.setParameter("number", number);
            contract = (Contract) jpqlQuery.getSingleResult();
        } catch (Exception e) {
            System.out.println(e);
        }
        return contract;
    }

    private static Long getFreeNumber(){
        boolean exist;
        Long number;
        do {
            exist = false;
            number = Constants.DEFAULT_PHONE_CODE * 10000000L + (long) (Math.random() * 10000000);
            if (getContractByPhonenumber(number)!=null){
                exist = true;
            }
        } while (exist);
        return number;
    }
}
