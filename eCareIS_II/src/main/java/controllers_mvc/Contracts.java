package controllers_mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.ContractService;
import service.DTO.ContractDTO;

import java.util.Set;

@Controller
@RequestMapping("/app/contracts")
public class Contracts {
    @Autowired
    ContractService contractService;

    @RequestMapping(method = RequestMethod.GET)
    public String showAll(Model model) {
        Set<ContractDTO> contractDTOs = contractService.getAllContracts();
        model.addAttribute("contractSet", contractDTOs);
        return "contracts";
    }
}
