package controllers_mvc;

import controllers_mvc.validationFormClasses.AddNumberToContractForm;
import controllers_mvc.validationFormClasses.SearchedPhonenumberForm;
import org.apache.log4j.Logger;
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
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Controller
public class Contracts {

    Logger logger = Logger.getLogger(Contracts.class);

    @Autowired
    ContractService contractService;

    @Autowired
    UserService userService;

    @ModelAttribute("contractSet")
    public Set<ContractDTO> addAllContractsToModel() {
        //todo think about pagination
        return contractService.getAllContracts();
    }

    @RequestMapping(value = "/app/contracts", method = RequestMethod.GET)
    public String showAll(Model model) {
//        Set<ContractDTO> contractDTOs = contractService.getAllContracts();
//        model.addAttribute("contractSet", contractDTOs);
        model.addAttribute("searchNumberForm", new SearchedPhonenumberForm());
        return "contracts";
    }

    @RequestMapping(value="/app/contractBlockStatusEdit", method = RequestMethod.POST)
    public String ContractBlockStatusEdit(Model model,
                                          @RequestParam("contractId") Integer contractId,
                                          @RequestParam("command") String command) {
        if (command.equals("block")){
            contractService.blockByStaff(contractId);
        }
        else {
            if (command.equals("unblock")) {
                contractService.unblockByStaff(contractId);
            }
        }
     //   Integer userId = contractService.
        return "redirect:/app/contracts";
    }

    @RequestMapping(value = "/app/contracts/search", method = RequestMethod.POST)
    public String searchContract(@Valid @ModelAttribute("searchNumberForm")SearchedPhonenumberForm form,
                                 Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "contracts";
        }
        ContractDTO foundContractDTO = null;
        try {
            foundContractDTO = contractService.
                    getContractByPhonenumber(Long.parseLong(form.getNumber()));
        } catch (Exception e) {
            logger.warn("Error while looking up for the number:" + form.getNumber());
        }

        Set<ContractDTO> contractDTOs = new HashSet<ContractDTO>();
        if (foundContractDTO==null) {
            contractDTOs = Collections.emptySet();
        }
        else
        {
            contractDTOs.add(foundContractDTO);
        }
        model.addAttribute("contractSet", contractDTOs);

        return "contracts";
    }


}
