package utils;

import entity.User;
import service.DTO.UserDTO;

/**
 * Created by Alexey on 26.06.2015.
 */
public class UserMapper {

    public static User DTOToEntity(UserDTO userDTO){
        if (userDTO==null) return null;

        User user = new User();

        user.setUserId(userDTO.getUserId());
        user.setName(userDTO.getName());
        user.setLastname(userDTO.getLastname());
        user.setBirthday(userDTO.getBirthday());
        user.setPassport(userDTO.getPassport());
        user.setAddress(userDTO.getAddress());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());

        return user;
    }

    public static UserDTO EntityToDTO(User user){
        if (user==null) return null;

        UserDTO userDTO = new UserDTO();

        userDTO.setUserId(user.getUserId());
        userDTO.setName(user.getName());
        userDTO.setLastname(user.getLastname());
        userDTO.setBirthday(user.getBirthday());
        userDTO.setPassport(user.getPassport());
        userDTO.setAddress(user.getAddress());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());

        return userDTO;
    }

}
