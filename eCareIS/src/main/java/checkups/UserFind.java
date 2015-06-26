package checkups;

import DAO.EntityManagerFactorySingleton;
import entity.User;
import service.DTO.UserDTO;
import service.UserService;
import service.UserServiceGenericBasedImpl;
import service.UserServiceImpl;

/**
 * Created by Alexey on 24.06.2015.
 */
public class UserFind {
    public static void main(String[] args) {

        UserService userService = new UserServiceGenericBasedImpl();
        UserDTO userDTO = userService.findUserByEmail("iv@iv.com");
//        System.out.println(userDTO.getContracts().size());
        if (userDTO!=null){
            System.out.println(userDTO);
        }
        else{
            System.out.println("Such user was not found");
        }
        String email = "iv@iv.com";
        String password = "111";
        System.out.println(userService.userWithEmailAndPasswordExists(email, password));
        System.out.println(userService.userWithEmailAndPasswordExists("wrong email", password));
        System.out.println(userService.userWithEmailAndPasswordExists(email, "wrong password"));
        EntityManagerFactorySingleton.getInstance().close();
    }
}
