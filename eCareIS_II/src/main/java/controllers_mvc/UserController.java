package controllers_mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.DTO.UserDTO;
import service.UserService;

import java.util.List;

/**
 * Created by Alexey on 22.07.2015.
 */

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping(method = RequestMethod.GET)
    public String showAll(Model model) {
        List<UserDTO> userDTOList = userService.getAllUsers();
        model.addAttribute("userList", userDTOList);
        return "users";
//        request.getRequestDispatcher("/WEB-INF/pages/users.jsp").forward(request,response);
    }
}
