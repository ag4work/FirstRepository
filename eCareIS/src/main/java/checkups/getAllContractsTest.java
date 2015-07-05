package checkups;

import DAO.ContractDAO;
import DAO.ContractDAOImpl;
import entity.Contract;
import service.ContractService;
import service.ContractServiceImpl;
import service.DTO.ContractDTO;
import utils.EntityManagerFactorySingleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;

/**
 * Created by Alexey on 05.07.2015.
 */
public class getAllContractsTest {
    public static void main(String[] args) {

//        ContractService contractService = new ContractServiceImpl();
//        ContractDAO contractDAO = new ContractDAOImpl(EntityManagerFactorySingleton.getInstance());
//        List<Contract> contracts = contractDAO.getAll();
//        System.out.println(contracts);
        EntityManagerFactory emf = EntityManagerFactorySingleton.getInstance();
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("SELECT a FROM Contract a");
        List<Contract> entities = query.getResultList();

        emf.close();
        System.out.println(entities);


    }
}

