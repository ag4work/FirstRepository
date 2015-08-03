package service;



import DAO.UserDAO;
import DAO.UserDAOImpl;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.DTO.UserDTO;
//import utils.EntityManagerFactorySingleton;
import utils.Mappers.UserMapper;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Alexey on 24.06.2015.
 */
@Service
public class UserServiceGenericBasedImpl implements UserService{
    @Autowired
    UserDAO userDAO;

    @Transactional
    public UserDTO getUserById(Integer userId){
        return UserMapper.EntityToDTOWithSet(userDAO.get(userId));
    }

    @Transactional
    public UserDTO findUserByEmail(String email){
        List<User> allUsers = userDAO.getAll();
        for (User user : allUsers){
            if (user.getEmail().equals(email)) {
                return UserMapper.EntityToDTOWithSet(user);
            }
        }
        return null;
    }

    @Override
    @Transactional
    public UserDTO userWithEmailAndPasswordExists(String email, String password) {
        UserDTO userDTO = findUserByEmail(email);
        if (userDTO!=null && userDTO.getPassword().equals(password)){
            return userDTO;
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOs = new ArrayList<UserDTO>();
        for (User user : userDAO.getAll()){
            userDTOs.add(UserMapper.EntityToDTOWithSet(user));
        }
        return userDTOs;
    }

    @Override
    @Transactional
    public void addUser(UserDTO userDTO) {
        userDAO.add(UserMapper.DTOToEntity(userDTO));
    }
}
