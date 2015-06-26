package service;

import entity.User;
import service.DTO.UserDTO;

import java.util.List;

/**
 * Created by Alexey on 24.06.2015.
 */
public interface UserService {
    public UserDTO findUserByEmail(String email);
    public UserDTO userWithEmailAndPasswordExists(String email, String password);
    public List<UserDTO> getAllUsers();
    public void addUser(UserDTO userDTO);

}
