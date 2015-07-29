package controllers_mvc;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.ContractService;
import service.DTO.ContractDTO;
import service.DTO.UserDTO;
import service.UserService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;

/**
 * Created by Alexey on 22.07.2015.
 */

@Controller
@RequestMapping("/app/users")
public class UserController {
    Logger logger = Logger.getLogger(UserController.class);
    @Autowired
    UserService userService;

    @Autowired
    ContractService contractService;

    @RequestMapping(method = RequestMethod.GET)
    public String showAll(Model model) {
        List<UserDTO> userDTOList = userService.getAllUsers();
        model.addAttribute("userList", userDTOList);
        return "users";
    }

    @RequestMapping(value = "{userId}/contracts", method = RequestMethod.GET)
    public String userContracts(@PathVariable("userId") Integer userId,
                             Model model, RedirectAttributes redirectAttributes ) {
        try {
            UserDTO userDTO = userService.getUserById(userId);
            Set<ContractDTO> contractDTOs = contractService.getContractsByUserId(userId);
            model.addAttribute("contractSet", contractDTOs);
            model.addAttribute("user", userDTO);
            return "clientContracts";
        } catch (EntityNotFoundException e) {
            logger.warn("EntityNotFoundException, userid:" + userId );
            logger.warn(e);
            redirectAttributes.addFlashAttribute("errorText", "Пользователь с таким идентификатором не существует.");
            return "redirect:/app/users";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorText", "При просмотре контрактов пользователя что-то пошло не так :(");
            logger.warn(e);
            return "redirect:/app/users";
        }

    }

}
