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

    public UserDTO getUserById(Integer userId);
    public UserDTO findUserByEmail(String email);
    public UserDTO userWithEmailAndPasswordExists(String email, String password);
    public List<UserDTO> getAllUsers();
    public void addUser(UserDTO userDTO);
    public List<UserDTO> getUsers(Integer page, Integer usersPerPage);
    public Long getUserCount();

}
