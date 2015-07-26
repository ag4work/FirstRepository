package controllers_mvc;

import controllers_mvc.validationFormClasses.ClientNumberPassw;
import controllers_mvc.validationFormClasses.StaffEmailPassw;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.OptionService;

import javax.validation.Valid;


@Controller
public class LoginLogout {

    @Autowired
    OptionService optionService;

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

                      Errors errors) {
        if (errors.hasErrors()) {
            return "loginStaff";
        }
        return "redirect:/users";
    }

    @RequestMapping(value = "/clientLogin", method = RequestMethod.POST)
    public String clientLogin(@Valid @ModelAttribute("numberPasswForm")ClientNumberPassw numberPasswForm,
                      Errors errors) {
        if (errors.hasErrors()) {
            return "loginClient";
        }
        return "redirect:/users";
//        return "redirect:/options";
    }

}