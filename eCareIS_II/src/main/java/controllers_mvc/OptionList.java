package controllers_mvc;

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
import service.DTO.OptionDTO;
import service.OptionService;
import service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("/options")
public class OptionList {

    @Autowired
    OptionService optionService;

    Logger logger = Logger.getLogger(OptionList.class);

    @ModelAttribute("options")
    public Set<OptionDTO> registerContact() {
        return optionService.getAllOptions();
    }

    @RequestMapping(/*value = "/list",*/method = RequestMethod.GET)
    public String showAll(Model model) {
        Set<OptionDTO> optionDTOs  = optionService.getAllOptions();
        model.addAttribute("options", optionDTOs);
        model.addAttribute("newOption", new OptionDTO());
        return "options";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@Valid @ModelAttribute("newOption")OptionDTO optionDTO,
                      Errors errors,RedirectAttributes model ) {

        if (errors.hasErrors()) {
            return "options";
        }
        try {
            optionService.addOption(optionDTO);
            model.addFlashAttribute("successText", "Опция \""+ optionDTO.getTitle()+ "\" успешно добавлена.");
        }
        catch (Exception e) {
            logger.error("something went wrong while trying to add option id:" + optionDTO.getOptionId());
            model.addFlashAttribute("errorText", "Во время добавления опции возникла неполадка.");
        }
        return "redirect:/options";
    }

    @RequestMapping(value = "/remove/{optionId}", method = RequestMethod.GET)
    public String removeOption(@PathVariable("optionId") Integer optionId, RedirectAttributes model) {

       try {
           logger.info("try to delete option: " + optionId);
           String deletedOptTitle = optionService.getOptionById(optionId).getTitle();
           optionService.delete(optionId);
           model.addFlashAttribute("successText", "Опция \"" + deletedOptTitle
                   + "\" успешно удалена.");
           logger.error("Опция удалена id: "+optionId);
       }
       catch (Exception e) {
           logger.error("something went wrong while trying to delete option id:" + optionId);
           model.addFlashAttribute("errorText", "Во время удаления опции возникла неполадка.");
       }
       return "redirect:/options";
    }
}
