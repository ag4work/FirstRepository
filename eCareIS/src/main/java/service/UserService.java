package service;

import entity.User;

/**
 * Created by Alexey on 24.06.2015.
 */
public interface UserService {
    public User findUserByEmail(String email);
    public User userWithEmailAndPasswordExists(String email, String password);

}
