package checkups;

import DAO.*;
import entity.Contract;
import entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Alexey on 23.06.2015.
 */
public class PersistenceBasicTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EcarePU");
        EntityManager em = emf.createEntityManager();

        ContractDAO contractDAO = new ContractDAOImpl(emf);

        Contract contract1 = contractDAO.getById(1);
        contract1.setContractId(null);
        contractDAO.add(contract1);
//        System.out.println(contractDAO.getById(1));

        emf.close();
    }
}
