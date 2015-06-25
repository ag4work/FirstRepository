package service;

import DAO.EntityManagerFactorySingleton;

import GenericBasedDAO.UserDAO;
import GenericBasedDAO.UserDAOImpl;
import entity.User;

import java.util.List;

/**
 * Created by Alexey on 24.06.2015.
 */
public class UserServiceGenericBasedImpl implements UserService{
    UserDAO userDAO = new UserDAOImpl(EntityManagerFactorySingleton.getInstance());

    public User findUserByEmail(String email){
        List<User> allUsers = userDAO.getAll();
        for (User user : allUsers){
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User userWithEmailAndPasswordExists(String email, String password) {
        User user = findUserByEmail(email);
        if (user!=null && user.getPassword().equals(password)){
            return user;
        } else {
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAll();
    }

    @Override
    public void addUser(User user) {
        userDAO.add(user);
    }
}
