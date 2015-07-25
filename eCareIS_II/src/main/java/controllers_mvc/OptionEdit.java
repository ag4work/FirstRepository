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

import java.util.Set;

@Controller
@RequestMapping("/options/edit")
public class OptionEdit {
    @Autowired
    OptionService optionService;

    Logger logger = Logger.getLogger(OptionList.class);

    @RequestMapping(value = "{optionId}", method = RequestMethod.GET)
    public String removeOption(@PathVariable("optionId") Integer optionId, Model model) {
        OptionDTO currentOption = optionService.getOptionById(optionId);
        Set<OptionDTO> dependentOptions = currentOption.getDependentOption();
        Set<OptionDTO> inconsistentOptions = currentOption.getInconsistentOption();
        Set<OptionDTO> allOtherOptions = optionService.getAllOptions();
        allOtherOptions.removeAll(dependentOptions);
        allOtherOptions.remove(currentOption);
        allOtherOptions.removeAll(inconsistentOptions);
        model.addAttribute("currentOption", currentOption);
        model.addAttribute("allOtherOptions", allOtherOptions);
        return "optionEdit";
    }
}
