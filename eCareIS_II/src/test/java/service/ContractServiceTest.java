package service;

import dao.ContractDAO;
import entity.Contract;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.mockito.Mockito.mock;

/**
 * Created by Alexey on 08.08.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class ContractServiceTest {

//    @Autowired
//    ContractService contractService;
//
//    @Autowired
//    ContractDAO contractDAO;

    @Test
    public void test(){

    }

    @Configuration
    static class ContextConfiguration {

//        @Bean
//        public ContractService contractService() {
//            return new ContractServiceImpl();
//        }
//
//        @Bean
//        public ContractDAO contractDao() {
//            ContractDAO contractDao = mock(ContractDAO.class);
//
//            Contract contract = new Contract();
//
//
//            return contractDao;
//        }
//
//        private static void fillContract(Contract contract){
//
//        }


    }
}
