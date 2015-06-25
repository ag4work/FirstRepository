package GenericBasedDAO;

import entity.User;

import javax.persistence.EntityManagerFactory;

/**
 * Created by Alexey on 25.06.2015.
 */
public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {
    private EntityManagerFactory emf;
    public UserDAOImpl(EntityManagerFactory emf) {
        super(User.class, emf);
        this.emf = emf;
    }
}
