package checkups;


import entity.Contract;
import entity.Option;
import entity.Tariff;
import entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Alexey on 23.06.2015.
 */
public class PersistenceManyToManyTest {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("EcarePU");
    private static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {

//        userTest();
//        contractTest();
      //  optionTest();
        tariffTest();

        emf.close();
    }

    private static void tariffTest() {
        Tariff tariff = em.find(Tariff.class,1);
        System.out.println(tariff);
        System.out.println(tariff.getContractHasThisTariff());
        System.out.println(tariff.getPossibleOption());
    }

    private static void optionTest() {
        Option option = em.find(Option.class,1);
        System.out.println(option);
        System.out.println(option.getDependentOption());
        System.out.println(option.getInconsistentOption());
        System.out.println(option.getContractByOptionId());
        System.out.println(option.getTariffHasThisOption());
    }

    public static  void contractTest(){
        Contract contract = em.find(Contract.class,1);
        System.out.println(contract);
        System.out.println(contract.getUserByClientId());
        System.out.println(contract.getTariffByTariffId());
        System.out.println(contract.getChosenOption());
    }

    public static void userTest(){
        User user = em.find(User.class,1);
        System.out.println(user);
        System.out.println(user.getContractOfUser());
    }



}
