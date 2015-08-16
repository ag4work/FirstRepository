package service;

import entity.User;
import org.springframework.stereotype.Service;
import service.DTO.UserDTO;

import java.util.List;

/**
 * Created by Alexey on 24.06.2015.
 */
@Service
public interface UserService {

    /**
     * getting user nu userID
     * @param userId userId
     * @return UserDTO
     */
    public UserDTO getUserById(Integer userId);

    /**
     * get user by email
     * @param email user email
     * @return user dto
     */
    public UserDTO findUserByEmail(String email);

    /**
     * for login: get user by passw and email
     * @param email user email
     * @param password user password
     * @return user DTO
     */
    public UserDTO userWithEmailAndPasswordExists(String email, String
            password);

    /**
     * gett all users
     * @return list of all users
     */
    public List<UserDTO> getAllUsers();

    /**
     * add new user
     * @param userDTO user DTO
     */
    public void addUser(UserDTO userDTO);

    /**
     * for pagination
     * @param page page
     * @param usersPerPage users per page
     * @return list of users
     */
    public List<UserDTO> getUsers(Integer page, Integer usersPerPage);

    /**
     * number of all users
     * @return user count
     */
    public Long getUserCount();

}
