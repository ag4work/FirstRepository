package controllers_mvc;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import controllers_mvc.validationFormClasses.SearchedPhonenumberForm;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import service.ContractService;
import service.DTO.ContractDTO;
import service.UserService;
import utils.Constants;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Controller
@SessionAttributes({"contractSet"})
public class Contracts {

    private static final Logger logger = Logger.getLogger(Contracts.class);

    @Autowired
    ContractService contractService;

    @Autowired
    UserService userService;

//    @ModelAttribute("contractSet")
//    public Set<ContractDTO> addAllContractsToModel() {
//        //todo think about pagination
//        return contractService.getAllContracts();
//    }

    @RequestMapping(value = "/app/contracts", method = RequestMethod.GET)
    public String showAll(Model model) {
        return "redirect:/app/contracts/page/1";
    }

    @RequestMapping(value = "/app/contracts/page/{pageNum}", method = RequestMethod.GET)
    public String showAllByPages(@PathVariable Integer pageNum, Model model) {
        model.addAttribute("searchNumberForm", new SearchedPhonenumberForm());
        model.addAttribute("contractSet", contractService.getContracts(pageNum, Constants.CONTRACTS_PER_PAGE));
        model.addAttribute("numOfPages",  contractService.getContractCount() / Constants.CONTRACTS_PER_PAGE + 1);
        model.addAttribute("currentPage", pageNum);

        return Constants.CONTRACTS_VIEW;
    }

    @RequestMapping(value="/app/contractBlockStatusEdit", method = RequestMethod.POST)
    public String contractBlockStatusEdit(@RequestParam("contractId") Integer contractId,
                                          @RequestParam("command") String command,
                                          @RequestParam Integer page) {
        if ("block".equals(command)){
            contractService.blockByStaff(contractId);
        }
        else {
            if ("unblock".equals(command)) {
                contractService.unblockByStaff(contractId);
            }
        }
     //   Integer userId = contractService.
        if (page==null) {
            page=1;
        }
        return "redirect:/app/contracts/page/"+page;
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
            logger.warn("Error while looking up for the number:" + form.getNumber(), e);
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

        return Constants.CONTRACTS_VIEW;
    }


}
