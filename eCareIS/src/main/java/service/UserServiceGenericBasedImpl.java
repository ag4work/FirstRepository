package service;



import GenericBasedDAO.UserDAO;
import GenericBasedDAO.UserDAOImpl;
import entity.User;
import service.DTO.UserDTO;
import utils.EntityManagerFactorySingleton;
import utils.UserMapper;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Alexey on 24.06.2015.
 */
public class UserServiceGenericBasedImpl implements UserService{
    UserDAO userDAO = new UserDAOImpl(EntityManagerFactorySingleton.getInstance());

    public UserDTO findUserByEmail(String email){
        List<User> allUsers = userDAO.getAll();
        for (User user : allUsers){
            if (user.getEmail().equals(email)) {
                return UserMapper.EntityToDTO(user);
            }
        }
        return null;
    }

    @Override
    public UserDTO userWithEmailAndPasswordExists(String email, String password) {
        UserDTO userDTO = findUserByEmail(email);
        if (userDTO!=null && userDTO.getPassword().equals(password)){
            return userDTO;
        } else {
            return null;
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOs = new ArrayList<UserDTO>();
        for (User user : userDAO.getAll()){
            userDTOs.add(UserMapper.EntityToDTO(user));
        }
        return userDTOs;
    }

    @Override
    public void addUser(UserDTO userDTO) {
        userDAO.add(UserMapper.DTOToEntity(userDTO));
    }
}
