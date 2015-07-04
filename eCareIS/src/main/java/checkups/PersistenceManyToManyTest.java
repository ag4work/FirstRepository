package checkups;


import entity.Contract;
import entity.Option;
import entity.Tariff;
import entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Set;

/**
 * Created by Alexey on 23.06.2015.
 */
public class PersistenceManyToManyTest {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("EcarePU");
    private static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {

        //userTest();
//        contractTest();
        optionTest();
//        tariffTest();

        emf.close();
    }

    private static void tariffTest() {
        Tariff tariff = em.find(Tariff.class,5);
        System.out.println(tariff);
//        System.out.println(tariff.getContractHasThisTariff());
        System.out.println(tariff.getPossibleOption());

//        Option option1 = em.find(Option.class,1);
//        Option option2 = em.find(Option.class,1);
//        tariff.getPossibleOption().add(option1);
//        tariff.getPossibleOption().add(option2);
//        em.getTransaction().begin();
//        em.merge(tariff);
//        em.getTransaction().commit();

    }

    private static void optionTest() {
        Option option = em.find(Option.class,1);
        System.out.println(option);
        System.out.println(option.getDependentOption());
        System.out.println(option.getInconsistentOption());
        System.out.println(option.getContracts());
        for (Tariff tariff : option.getTariffHasThisOption())
            System.out.println(tariff);
    }

    public static  void contractTest(){
        Contract contract = em.find(Contract.class,1);
        System.out.println(contract);
        System.out.println(contract.getUser());
        System.out.println(contract.getTariff());
        System.out.println(contract.getChosenOption());
    }


    public static void userTest(){

        User user = em.find(User.class,27);

        Contract contract = em.find(Contract.class,11);

        Set<Contract> contractList = user.getContractOfUser();

        //contractList.add(contract);
 //       contractList.get(0).setPhoneNumber(333L);
        contractList.add(contract);
//        em.getTransaction().begin();
//        em.persist(contractList.get(0));
//        em.getTransaction().commit();

//        user.setContractOfUser(contractList);
//        System.out.println(user.getContractOfUser());
        System.out.println(user.getContractOfUser());
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
        System.out.println(user.getContractOfUser());
        em.close();




//        UserDAO userDAO = new UserDAOImpl(emf);
//        User user = userDAO.get(27);
//
//
//        ContractDAO contractDAO = new ContractDAOImpl(emf);
//
//        Contract contract = contractDAO.get(11);
//        contract.setPhoneNumber(11232L);
//        contractDAO.update(contract);
//
//        user.getContractOfUser().add(contract);
//        userDAO.update(user);
//        System.out.println(user.getContractOfUser());
//        user = userDAO.get(27);
//        System.out.println(user.getContractOfUser());

//        System.out.println(user);
//        System.out.println(user.getContractOfUser());
//        List<Contract> contracts = user.getContractOfUser();
//        UserDAO userDAO = new UserDAOImpl(emf);
//        User anotherUser = new User();
//        anotherUser.setName("Иван");

    }



}
