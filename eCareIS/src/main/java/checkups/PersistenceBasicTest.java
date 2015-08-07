package checkups;


import entity.Contract;
import entity.User;
import utils.Constants;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Alexey on 23.06.2015.
 */
public class PersistenceBasicTest {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("EcarePU");
    static EntityManager em = emf.createEntityManager();
    public static void main(String[] args) {
        Query query = em.createQuery("SELECT u FROM User u ORDER BY u.id ASC");
        query.setFirstResult(3);
        query.setMaxResults(5);
        List<User> users = query.getResultList();
        System.out.println(users);


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
