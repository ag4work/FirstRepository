package controllers_mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.ContractService;
import service.DTO.ContractDTO;

import java.util.Set;

@Controller
public class Contracts {

    @Autowired
    ContractService contractService;

    @RequestMapping(value = "/app/contracts", method = RequestMethod.GET)
    public String showAll(Model model) {
        Set<ContractDTO> contractDTOs = contractService.getAllContracts();
        model.addAttribute("contractSet", contractDTOs);
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

}
