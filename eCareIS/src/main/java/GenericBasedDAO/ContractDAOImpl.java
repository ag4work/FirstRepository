package GenericBasedDAO;

import entity.Contract;
import org.apache.log4j.Logger;

import javax.persistence.EntityManagerFactory;

/**
 * Created by Alexey on 01.07.2015.
 */

public class ContractDAOImpl extends GenericDAOImpl<Contract> implements ContractDAO  {
    private EntityManagerFactory emf;
    Logger logger = Logger.getLogger(UserDAOImpl.class);

    public ContractDAOImpl (EntityManagerFactory emf) {
        super(Contract.class, emf);
        this.emf = emf;
        logger.info("TariffDAOImpl(generic extended) object created");
    }
}
