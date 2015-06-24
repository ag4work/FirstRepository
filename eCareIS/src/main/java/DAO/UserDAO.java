package DAO;

import entity.Contract;
import entity.User;

import java.util.List;

/**
 * Created by Alexey on 23.06.2015.
 */
public interface UserDAO {
    void add(User user);
    void update(User user);
    void delete(User user);
    User getById(Integer id);
    List<User> getAll();
}
