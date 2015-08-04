package controllers_mvc;

import controllers_mvc.validationFormClasses.ClientNumberPassw;
import controllers_mvc.validationFormClasses.NewTariff;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.DTO.OptionDTO;
import service.DTO.TariffDTO;
import service.TariffService;

import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping(value = "/app/tariffs")
public class TariffController {
    Logger logger = Logger.getLogger(TariffController.class);

    @Autowired
    TariffService tariffService;

    @ModelAttribute("tariffs")
    public Set<TariffDTO> addAllTariffToModel() {
        return tariffService.getAllTariffs();
    }


    @RequestMapping(method = RequestMethod.GET)
    public String showAll(Model model) {
        Set<TariffDTO> tariffDTOs = tariffService.getAllTariffs();
//        model.addAttribute("tariffs", tariffDTOs);
        model.addAttribute("newTariff", new NewTariff());
        return "tariffs";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addTariff(@Valid @ModelAttribute("newTariff")
                                NewTariff newTariff,  Errors errors, Model model,
                            RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            return "tariffs";
        }

        try {
            TariffDTO tariffDTO = new TariffDTO();
            tariffDTO.setTitle(newTariff.getTitle());
            tariffDTO.setPrice(Integer.parseInt(newTariff.getPrice()));
            tariffService.addTariff(tariffDTO);
            redirectAttributes.addFlashAttribute("successText", "Тариф \""+ tariffDTO.getTitle()+ "\" успешно добавлен.");
        }
        catch (Exception e) {
            logger.error("something went wrong while trying to add the tariff " + newTariff.getTitle());
            redirectAttributes.addFlashAttribute("errorText", "Во время добавления тарифа возникла неполадка.");
        }
        return "redirect:/app/tariffs";

    }



}
