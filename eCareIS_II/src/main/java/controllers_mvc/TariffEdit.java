package controllers_mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.ContractService;
import service.DTO.ContractDTO;
import service.DTO.OptionDTO;
import service.DTO.TariffDTO;
import service.OptionService;
import service.TariffService;
import utils.Constants;

import java.util.HashSet;
import java.util.Set;


@Controller
public class TariffEdit {

    @Autowired
    OptionService optionService;

    @Autowired
    ContractService contractService;

    @Autowired
    TariffService tariffService;

    @RequestMapping(value = "/app/tariffs/remove", method = RequestMethod.POST)
    public String removeTariff(@RequestParam("tariffId") Integer tariffToDeleteId,
                            @RequestParam("command") String command,
                            RedirectAttributes redirectAttributes,
                            Model model) {
        //todo make a try of tarrifid is not exist anymore
        if (tariffToDeleteId.equals(Constants.DEFAULT_TARIFF_ID)) {
            redirectAttributes.addFlashAttribute("errorText", "Нельзя удалять этот тариф." +
                    " Он используется как базовый при подключении " +
                    "нового пользователя. Также при удалении других тарифов " +
                    "пользователи автоматически переключаются с " +
                    "удаляемого тарифа на тариф по умолчанию.");

            return "redirect:/app/tariffs";
        } else {
            // если подтв удаление, то удаляем
            // иначе показываем страничку с текстом подтв удаления
            Set<ContractDTO> contractsByTariff = contractService.getContractsByTariff(tariffToDeleteId);
            TariffDTO tariffDTO = tariffService.getTariffById(tariffToDeleteId);

            if (command != null && command.equals("deleteConfirmed")) {
                tariffService.removeTariffAndMoveContractsToBaseTariff(tariffToDeleteId);
                Set<ContractDTO> updatedContractDTOs = new HashSet<ContractDTO>();
                for (ContractDTO contractDTO : contractsByTariff)
                    updatedContractDTOs.add(contractService.getContract(contractDTO.getContractId()));
                contractsByTariff = updatedContractDTOs;
                model.addAttribute("tariffDeleted", true);
                model.addAttribute("successText", "Тариф \""
                        + tariffDTO.getTitle() +
                        "\" удален. Контракты, привязанные к нему переведены" +
                        " на тариф \"Базовый\"");
            }

            model.addAttribute("contractSet", contractsByTariff);
            model.addAttribute("tariff", tariffDTO);
            return "tariffDeleteConfirmation";

        }
    }

    @RequestMapping(value = "/app/tariffs/PossibleOptions", method = RequestMethod.POST)
    public String PossibleOptionsList(@RequestParam("tariffId") Integer tariffId,
                            Model model){
        //todo make a try of tarrifid is not exist anymore
        TariffDTO tariffDTO = tariffService.getTariffById(tariffId);
        Set<OptionDTO> allOptions = optionService.getAllOptions();
        Set<OptionDTO> tariffPossibleOptions = tariffService.
                getTariffById(tariffId).getPossibleOption();
        Set<OptionDTO> tariffPossibleOptionsWithSets = new HashSet<OptionDTO>();
        for (OptionDTO optionDTOWithoutSets : tariffPossibleOptions){
            tariffPossibleOptionsWithSets.add(optionService.getOptionById(
                    optionDTOWithoutSets.getOptionId()));
        }

        allOptions.removeAll(tariffPossibleOptionsWithSets);

        model.addAttribute("tariffPossibleOptions", tariffPossibleOptionsWithSets);
        model.addAttribute("allOtherOptions", allOptions);
        model.addAttribute("tariff", tariffDTO);
        return "tariffChoosePossibleOption";
    }

    @RequestMapping(value = "/app/tariffs/PossibleOptionsEdit", method = RequestMethod.POST)
    public String PossibleOptionsForTariff(@RequestParam("tariffId") Integer tariffId,
                                      @RequestParam("optionId") Integer optionId,
                                      @RequestParam("command") String command,
                                      Model model) {
        //todo check all params on existence
        if (command.equals("add")) {
            tariffService.addOptionAsPossibleForTariff(tariffId, optionId);
            model.addAttribute("successText", "Опция \"" +
                    optionService.getOptionById(optionId).getTitle() + "\" добавлена");
        }
        if (command.equals("remove")) {
            tariffService.
                    removeOptionAndAllDependentOptionsTreeAsPossibleForTariff(
                            tariffId, optionId);
            model.addAttribute("successText", "Опция \"" +
                    optionService.getOptionById(optionId).getTitle() + "\" отключена");
        }
        return PossibleOptionsList(tariffId,model);
    }


}
