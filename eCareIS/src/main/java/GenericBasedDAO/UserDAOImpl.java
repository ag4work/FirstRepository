package GenericBasedDAO;

import entity.User;
import org.apache.log4j.Logger;

import javax.persistence.EntityManagerFactory;

/**
 * Created by Alexey on 25.06.2015.
 */
public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {
    private EntityManagerFactory emf;
    Logger logger = Logger.getLogger(UserDAOImpl.class);
    public UserDAOImpl(EntityManagerFactory emf) {
        super(User.class, emf);
        this.emf = emf;
        logger.info("UserDAOImpl(generic extended) object created");
    }
}
