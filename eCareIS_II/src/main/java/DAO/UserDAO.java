package dao;

import entity.User;
import org.springframework.stereotype.Repository;

/**
 * Created by Alexey on 25.06.2015.
 */
@Repository
public interface UserDAO extends DAOOperations<User> {

}
