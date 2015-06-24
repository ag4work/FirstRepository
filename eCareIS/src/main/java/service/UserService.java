package service;

import entity.User;

import java.util.List;

/**
 * Created by Alexey on 24.06.2015.
 */
public interface UserService {
    public User findUserByEmail(String email);
    public User userWithEmailAndPasswordExists(String email, String password);
    public List<User> getAllUsers();
    public void addUser(User user);

}
