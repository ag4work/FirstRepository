package controllers_mvc;

import controllers_mvc.validationFormClasses.AddNumberToContractForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.ContractService;
import service.DTO.ContractDTO;
import service.DTO.UserDTO;
import service.UserService;

import javax.validation.Valid;

/**
 * Created by Alexey on 05.08.2015.
 */

@Controller
public class AddContract {
    @Autowired
    ContractService contractService;

    @Autowired
    UserService userService;



    @RequestMapping(value = "/app/contracts/add", method = RequestMethod.POST)
    public String addContract(@Valid @ModelAttribute("addNumberToContractForm")
                              AddNumberToContractForm addNumberToContractForm,
                              Errors errors, Model model, @RequestParam Integer userId) {
        if (errors.hasErrors()) {
            return "phonenumber_choose";
        }

//        ContractDTO contractDTO = new ContractDTO();
//        UserDTO userDTO = userService.getUserById(userId);
//        contractDTO.setUserDTO(userDTO);
//
//        Integer balance = Integer.parseInt(addNumberToContractForm.
//                getInitialBalance());
//        contractDTO.setBalance(balance);
//
//        Long phoneNum = Long.parseLong(addNumberToContractForm.getPhoneNumber());
//        contractDTO.setPhoneNumber(phoneNum);
//
//        contractService.add(contractDTO);
//
        return "redirect:/app/users";



    }

}
