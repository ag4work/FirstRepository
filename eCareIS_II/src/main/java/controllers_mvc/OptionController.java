package controllers_mvc;

import exceptions.CycleInOptionsDependencyException;
import exceptions.InconsistentOptionDependency;
import exceptions.OptionInconsistencyImpossibleException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.DTO.OptionDTO;
import service.OptionService;
import service.UserService;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("app/options")
public class OptionController {

    @Autowired
    OptionService optionService;

    Logger logger = Logger.getLogger(OptionController.class);

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
        //todo make a special class for validation
        if (errors.hasErrors()) {
            return "options";
        }
        try {
            optionService.addOption(optionDTO);
            model.addFlashAttribute("successText", "Опция \""+ optionDTO.getTitle()+ "\" успешно добавлена.");
        }
        catch (Exception e) {
            logger.error("something went wrong while trying to add option id:" + optionDTO.getTitle(),e);
            model.addFlashAttribute("errorText", "Во время добавления опции возникла неполадка.");
        }
        return "redirect:/app/options";
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
        catch (EntityNotFoundException e) {
            model.addFlashAttribute("errorText", "Операция не выполнена."
                    + "Запрашиваемой опции уже не существует.");
            logger.warn(e);
        } catch (Exception e) {
            logger.error("something went wrong while trying to delete option id:" + optionId, e);
            model.addFlashAttribute("errorText", "Во время удаления опции возникла неполадка.");
        }
        return "redirect:/app/options";
    }

    @RequestMapping(value = "/edit/", method = RequestMethod.POST)
    public String editOption(@RequestParam("optionId") Integer optionId, Model model){
        try {

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
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorText", "Операция не выполнена."
                    + "Запрашиваемой опции уже не существует.");
            logger.warn(e);
        } catch (Exception e) {
            model.addAttribute("errorText", "Операция не выполнена. "
                    + "Возникла ошибка :(");
            logger.warn(e);
        }
        return showAll(model);
    }

    @RequestMapping(value = "/edit/optionAddDependency", method = RequestMethod.POST)
    public String optionAddDependency(@RequestParam("optionId") Integer baseOptionId,
                                      @RequestParam("dependentOptionId") Integer dependentOptionId,
                                      Model model){
        try {
            optionService.addDependency(baseOptionId,dependentOptionId);
            model.addAttribute("successText", "Зависимость успешно добавлена.");
        } catch (InconsistentOptionDependency inconsistentOptionDependency) {
            model.addAttribute("errorText", "Зависимость не может быть добавлена в силу имеющихся установленных несовместимостей между опциями." +
                    "Проверяется несовместимость между 1. объединением множеств опций(зависимых и требующихся) для выбранной зависимой опции и 2." +
                    "множеством всех требующихся точек для базовой опции");
            logger.warn("opt Ids:"+ baseOptionId + " " + dependentOptionId+ " ", inconsistentOptionDependency);
        } catch (CycleInOptionsDependencyException e) {
            model.addAttribute("errorText", "Зависимость не может быть добавлена. Это привело бы к зацикливанию зависимостей.");
            logger.warn("opt Ids:"+ baseOptionId + " " + dependentOptionId+ " ", e);
        }
        return editOption(baseOptionId, model);

    }

    @RequestMapping(value = "/edit/deleteOptionDependency", method = RequestMethod.POST)
    public String deleteOptionDependency(@RequestParam("optionId") Integer baseOptionId,
                                      @RequestParam("dependentOptionId") Integer dependentOptionId,
                                      Model model){
        try {
            optionService.removeDependency(baseOptionId, dependentOptionId);
            model.addAttribute("successText", "Зависимость успешно удалена");
        }
        catch (Exception e){
            logger.warn("Ошибка при удалении зависимости опции id("+
                    dependentOptionId+") для базовой опции id("+
                    baseOptionId+") ", e);
            model.addAttribute("errorText", "При удалении зависимости возникла"
                    +" ошибка. Скорее всего одна из опций уже удалена.");
        }

        return editOption(baseOptionId, model);
    }

    @RequestMapping(value = "/edit/optionAddInconsistency", method = RequestMethod.POST)
    public String optionAddInconsistency(@RequestParam("optionId") Integer baseOptionId,
                                      @RequestParam("inconsistentOptionId") Integer inconsistentOptionId,
                                      Model model){
        try {
            optionService.addInconsistency(baseOptionId, inconsistentOptionId);
            model.addAttribute("successText", "Несовместимость успешно добавлена.");
        } catch (OptionInconsistencyImpossibleException e) {
            model.addAttribute("errorText", "Выбранная несовместимость не может быть " +
                    "добавлена в силу существующих зависистей в дереве опций.");
            logger.warn("Opt Ids:" + baseOptionId + " " + inconsistentOptionId
                    + " ", e);
        }
        return editOption(baseOptionId, model);

    }

    @RequestMapping(value = "/edit/removeInconsistentOption", method = RequestMethod.POST)
    public String removeInconsistentOption(@RequestParam("optionId") Integer baseOptionId,
                                         @RequestParam("inconsistentOptionId") Integer inconsistentOptionId,
                                         Model model){
        try {
            optionService.removeInconsistency(baseOptionId, inconsistentOptionId);
            model.addAttribute("successText", "Несовместимость успешно удалена");
        } catch (Exception e) {
            model.addAttribute("errorText", "Во время удаления несовместимости"
                    + " возникла ошибка :( Скорее всего одной из двух опций "
                    + "уже не существует");
            logger.warn("Во время удаления несовместимости" +
                    "возникла ошибка. Opt Ids:" + baseOptionId + " "
                    + inconsistentOptionId + " ", e);

        }
        return editOption(baseOptionId, model);

    }


}
