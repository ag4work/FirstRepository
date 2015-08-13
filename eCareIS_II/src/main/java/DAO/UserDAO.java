package dao;

import entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Alexey on 25.06.2015.
 */
@Repository
public interface UserDAO extends DAOOperations<User> {
    public List<User> getUsers(Integer page, Integer usersPerPage);
    public Long getUserCount();

}
