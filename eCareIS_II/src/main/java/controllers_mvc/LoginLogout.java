package controllers_mvc;


import controllers_mvc.validationFormClasses.ClientNumberPassw;
import controllers_mvc.validationFormClasses.StaffEmailPassw;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.ContractService;
import service.DTO.ContractDTO;
import service.DTO.UserDTO;
import service.OptionService;
import service.UserService;
import utils.Constants;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginLogout {

    @Autowired
    UserService userService;

    @Autowired
    OptionService optionService;

    @Autowired
    ContractService contractService;

    Logger logger = Logger.getLogger(OptionList.class);

    @RequestMapping(value = "/loginClient", method = RequestMethod.GET)
    public String loginClient(Model model) {
        model.addAttribute("numberPasswForm", new ClientNumberPassw());
        return "loginClient";
    }

    @RequestMapping(value = "/loginStaff", method = RequestMethod.GET)
    public String loginStaff(Model model) {
        model.addAttribute("emailPasswForm", new StaffEmailPassw());
        return "loginStaff";
    }


    @RequestMapping(value = "/staffLogin", method = RequestMethod.POST)
    public String staffLogin(@Valid @ModelAttribute("emailPasswForm")StaffEmailPassw emailPasswForm,
                      Errors errors, HttpSession session, RedirectAttributes redirectAttributes ) {
        if (errors.hasErrors()) {
            return "loginStaff";
        }
        String password = emailPasswForm.getPass();
        String email = emailPasswForm.getEmail();

        UserDTO userDTO = userService.userWithEmailAndPasswordExists(email, password);
        if (userDTO != null && userDTO.getRole() == Constants.ADMIN) {
            SessionUserInfo sessionUserInfo = new SessionUserInfo(userDTO.getRole(), null);
            session.setAttribute(Constants.SESSION_USER_INFO_STR, sessionUserInfo);
            return "redirect:/app/users";
        } else {
            redirectAttributes.addFlashAttribute("errorText", "Сотрудника с такими логином и паролем не существует");
            return "redirect:/loginStaff";
        }
    }

    @RequestMapping(value = "/clientLogin", method = RequestMethod.POST)
    public String clientLogin(@Valid @ModelAttribute("numberPasswForm")ClientNumberPassw numberPasswForm,
                      Errors errors,HttpSession session, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            return "loginClient";
        }


        String password = numberPasswForm.getPassword();
        //            todo may be make some try of parce here?
        Long phonenumber = Long.parseLong(numberPasswForm.getPhoneNumber());
        ContractDTO clientContractDTO = contractService.getContractByPhonenumber(phonenumber);
        if (clientContractDTO != null && clientContractDTO.getUserDTO()
                .getPassword().equals(password) &&
                clientContractDTO.getUserDTO().getRole() == Constants.CLIENT) {
            Integer contractId = clientContractDTO.getContractId();
            Integer userRole = clientContractDTO.getUserDTO().getRole();
            SessionUserInfo sessionUserInfo = new SessionUserInfo(
                    userRole, contractId);
            session.setAttribute(Constants.SESSION_USER_INFO_STR, sessionUserInfo);
            return "redirect:/app/clientDashboard";
        } else {
            redirectAttributes.addFlashAttribute("errorText", "Клиента с "
                    + "такими логином и паролем не существует");
            return "redirect:/loginClient";
        }
    }


        @RequestMapping(value = "/logout", method = RequestMethod.GET)
        public String loginClient(HttpSession session) {
            session.invalidate();
            return "redirect:/loginStaff";
        }


}