package GenericBasedDAO;

import entity.Tariff;
import org.apache.log4j.Logger;

import javax.persistence.EntityManagerFactory;

/**
 * Created by Alexey on 01.07.2015.
 */

public class TariffDAOImpl extends GenericDAOImpl<Tariff> implements TariffDAO  {
    private EntityManagerFactory emf;
    Logger logger = Logger.getLogger(UserDAOImpl.class);
    public TariffDAOImpl (EntityManagerFactory emf) {
        super(Tariff.class, emf);
        this.emf = emf;
        logger.info("TariffDAOImpl(generic extended) object created");
    }
}
