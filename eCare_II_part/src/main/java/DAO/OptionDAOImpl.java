package DAO;

import entity.Option;
import org.apache.log4j.Logger;

import javax.persistence.EntityManagerFactory;

/**
 * Created by Alexey on 01.07.2015.
 */

public class OptionDAOImpl extends GenericDAOImpl<Option> implements OptionDAO  {
    private EntityManagerFactory emf;
    Logger logger = Logger.getLogger(UserDAOImpl.class);

    public OptionDAOImpl (EntityManagerFactory emf) {
        super(Option.class, emf);
        this.emf = emf;
        logger.info("TariffDAOImpl(generic extended) object created");
    }
}
