package controllers_mvc;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.DTO.OptionDTO;
import service.OptionService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Controller
@RequestMapping("/options/edit")
public class OptionEdit {
    @Autowired
    OptionService optionService;

    Logger logger = Logger.getLogger(OptionList.class);

    @RequestMapping(value = "{optionId}", method = RequestMethod.GET)
    public String optionEdit(@PathVariable("optionId") Integer optionId,
                               Model model,RedirectAttributes redirectAttributes ) {

        OptionDTO currentOption;
        try{
            currentOption = optionService.getOptionById(optionId);

            Set<OptionDTO> dependentOptions = currentOption.getDependentOption();
            Set<OptionDTO> inconsistentOptions = currentOption.getInconsistentOption();
            Set<OptionDTO> allOtherOptions = optionService.getAllOptions();
            allOtherOptions.removeAll(dependentOptions);
            allOtherOptions.remove(currentOption);
            allOtherOptions.removeAll(inconsistentOptions);
            model.addAttribute("currentOption", currentOption);
            model.addAttribute("allOtherOptions", allOtherOptions);
            return "optionEdit";

        } catch (Exception e) {
            logger.warn("something went wrong while trying to delete option id:" + optionId );
            logger.warn(e);
            redirectAttributes.addFlashAttribute("errorText", "Скорее всего запрашиваемой опции не существует.");
            return "redirect:/options";

        }

    }

}
