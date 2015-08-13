package controllers_mvc;

import controllers_mvc.validationFormClasses.AddNumberToContractForm;
import controllers_mvc.validationFormClasses.NewUserForm;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.ContractService;
import service.DTO.ContractDTO;
import service.DTO.UserDTO;
import service.UserService;
import utils.Constants;
import utils.NumberSplitter;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Alexey on 22.07.2015.
 */

@Controller
@RequestMapping("/app/users")
@SessionAttributes({"phonenumbersList","user"})
public class UserController {
    Logger logger = Logger.getLogger(UserController.class);
    @Autowired
    UserService userService;

    @Autowired
    ContractService contractService;



    @RequestMapping(method = RequestMethod.GET)
    public String showAll(Model model) {
//        List<UserDTO> userDTOList = userService.getAllUsers();
//        model.addAttribute("userList", userDTOList);
        return "redirect:/app/users/page/1";
    }

    @RequestMapping(value = "page/{pageNum}",method = RequestMethod.GET)
    public String showAll(@PathVariable Integer pageNum, Model model) {
        List<UserDTO> userDTOList = userService.getUsers(pageNum, Constants.USERS_PER_PAGE);
        model.addAttribute("numOfPages",  userService.getUserCount() / Constants.USERS_PER_PAGE + 1);
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("userList", userDTOList);
        return "usersByPages";
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

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addUserForm(Model model) {
        model.addAttribute("newUserForm", new NewUserForm());
        return "createuser";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addNewUser(@Valid @ModelAttribute("newUserForm")
                             NewUserForm newUserForm,  Errors errors,
                             Model model,
                             RedirectAttributes redirectAttributes) {

        if (errors.hasErrors()) {
            return "createuser";
        }
        try{
            UserDTO userDTO = new UserDTO();
            userDTO.setName(newUserForm.getName());
            userDTO.setLastname(newUserForm.getLastname());

            DateFormat dateFormatter;
            dateFormatter = new SimpleDateFormat("yyyy-mm-DD");
            Date userBirthday = (Date) dateFormatter.parse(newUserForm.getBirthday());
            userDTO.setBirthday(userBirthday);
            userDTO.setPassport(newUserForm.getPassport());
            userDTO.setAddress(newUserForm.getAddress());
            userDTO.setEmail(newUserForm.getEmail());
            userDTO.setPassword(newUserForm.getPassword());
            userDTO.setRole(Integer.parseInt(newUserForm.getRole()));
            userService.addUser(userDTO);
            String nameAndLastName = userDTO.getName() + " " + userDTO.getLastname();
            redirectAttributes.addFlashAttribute("successText", "Пользователь \""+ nameAndLastName+ "\" успешно добавлен.");
            return "redirect:/app/users";
        } catch (ParseException e) {
            model.addAttribute("errorText", "Возникла ошибка :( Скорее всего введена некорректная дата");
            logger.error(e);
            return "createuser";

        } catch (Exception e) {
            model.addAttribute("errorText", "Возникла ошибка :( Мы вскоре проанализируем ее и устраним.");
            logger.error(e);
            return "createuser";
        }
    }


    @RequestMapping(value = "/newPhoneNumber/Choose", method = RequestMethod.POST)
    public String newPhoneNumberChoose(@RequestParam("id") Integer userId, Model model){

        UserDTO userDTO = userService.getUserById(userId);

        logger.info("userId = " + userId);
        model.addAttribute("user", userDTO);
        model.addAttribute("phonenumbersList",
                contractService.getFreeNumberSet(
                        Constants.DEFAULT_QUANTITY_OF_PHONESNUMBERS_FOR_CHOOSE));
        model.addAttribute("addNumberToContractForm", new AddNumberToContractForm());
        return "phonenumber_choose";
}

    @RequestMapping(value = "/newPhoneNumber/add", method = RequestMethod.POST)
    public String addContract(@Valid @ModelAttribute("addNumberToContractForm")
                              AddNumberToContractForm addNumberToContractForm,
                              Errors errors,
                              @RequestParam Integer userId,
                              RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            return "phonenumber_choose";
        }

        Long phoneNum = null;
        try {
            ContractDTO contractDTO = new ContractDTO();
            UserDTO userDTO = userService.getUserById(userId);
            contractDTO.setUserDTO(userDTO);

            Integer balance = Integer.parseInt(addNumberToContractForm.
                    getInitialBalance());
            contractDTO.setBalance(balance);

            phoneNum = Long.parseLong(addNumberToContractForm.getPhoneNumber());
            contractDTO.setPhoneNumber(phoneNum);

            contractService.add(contractDTO);
            String userFIO = userDTO.getName() + " " + userDTO.getLastname();
            redirectAttributes.addFlashAttribute("successText",
                    "Контракт с номером " + NumberSplitter.
                            getBeautifulNumber(phoneNum.toString())
                            + " успешно добавлен пользователю \""+ userFIO + "\".");
        }   catch (Exception e) {
            logger.error("При добавлении номера" + phoneNum
                    + "для пользователя с id" + userId + "произошла ошибка: ",e);
            redirectAttributes.addFlashAttribute("errorText",
                    "При добавлении номера произошла ошибка.");

        }
        return "redirect:/app/users";
    }


}
