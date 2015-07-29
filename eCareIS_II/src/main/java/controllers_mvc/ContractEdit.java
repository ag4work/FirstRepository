package controllers_mvc;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.*;
import service.DTO.ContractDTO;
import service.DTO.OptionDTO;
import service.DTO.TariffDTO;
import utils.Constants;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.AssertTrue;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
public class ContractEdit {
    Logger logger = Logger.getLogger(ContractEdit.class);
    @Autowired
    ContractService contractService;

    @Autowired
    TariffService tariffService;

    @Autowired
    OptionService optionService;

    @Autowired
    CartService cartService;


    @RequestMapping(value="/app/contractEdit", method = RequestMethod.POST)
    public String showContract(Model model, @RequestParam("contractId") Integer contractId,
                               @RequestParam("tariffId") Integer tariffId, HttpSession session) {

        ContractDTO contractDTO;
        if (contractId!=null){
            contractDTO = contractService.getContract(contractId);
            model.addAttribute("contract", contractDTO);
            Set<OptionDTO> contractOptions =
                    contractService.getContractOptionsWithSets(contractId);
            model.addAttribute("contractOptions", contractOptions);
        }
        else
        {
            //TODO redirect to error page
        }

        List<TariffDTO> tariffDTOs = new ArrayList<TariffDTO>(tariffService.getAllTariffs());

        if (tariffDTOs.size()==0){
            //TODO redirect to error page
        }


        if (tariffId!=null && !tariffId.equals(Constants.NOT_CHOSEN_TARIFF_ID)){
//            logger.info("tariffId:"+tariffId);
//            logger.info("Constants.DEFAULT_TARIFF_ID:"+Constants.DEFAULT_TARIFF_ID);
//            logger.info(tariffId.equals(Constants.DEFAULT_TARIFF_ID));


            //todo try catch here!
            Integer chosenTariffId = tariffId;

            TariffDTO chosenTariffDTO = tariffService.getTariffById(chosenTariffId);
            int chosenTariffDTOIndex = tariffDTOs.indexOf(chosenTariffDTO);
            if (chosenTariffDTOIndex==-1){
                //TODO redirect to error page
            }

            TariffDTO tempForSWAP = tariffDTOs.get(0);
            tariffDTOs.set(0,tariffDTOs.get(chosenTariffDTOIndex));
            tariffDTOs.set(chosenTariffDTOIndex,tempForSWAP);
        }

        model.addAttribute("tariffs",tariffDTOs);
        TariffDTO chosenTariff = tariffDTOs.get(0);

        Set<OptionDTO> firstTariffOptions = chosenTariff.getPossibleOption();
        Set<OptionDTO> optionsWithFullInfo = new HashSet<OptionDTO>();
        for (OptionDTO option : firstTariffOptions){
            optionsWithFullInfo.add(optionService.getOptionById(option.getOptionId()));
        }
        model.addAttribute("chosenTariffOptions", optionsWithFullInfo);

        Cart cart = (Cart)session.getAttribute("cart");
        if (cart!=null && !cart.getContractId().equals(contractId)){
            session.removeAttribute("cart");
        }
        return "contractEdit";
    }

    @RequestMapping(value="/app/addTariffAndOptionToCart", method = RequestMethod.POST)
    public String addTariffAndOptionToCart(@RequestParam("contractId") Integer contractId,
                               @RequestParam("tariffId") Integer tariffId,
                               @RequestParam("optionId") Integer optionId, HttpSession session,
                               Model model) {


        ContractDTO contractDTO = contractService.getContract(contractId);
        TariffDTO tariffDTO = tariffService.getTariffById(tariffId);
        OptionDTO optionDTO = optionService.getOptionById(optionId);

        Cart cart = (Cart)session.getAttribute("cart");
        if (cart==null || !cart.getContractId().equals(contractId) ||
                !cart.getTariffDTO().equals(tariffDTO)){
            cart = new Cart();
            cartService.addOptionWithAllRequired(optionDTO,cart);
            cart.setTariffDTO(tariffDTO);
            cart.setContractId(contractId);
            session.setAttribute("cart", cart);
        }
        else {
            if (cartService.isOptionConsistentWithOptionsInCart(optionDTO,cart))
                cartService.addOptionWithAllRequired(optionDTO,cart);
            else
            {
                model.addAttribute("errorText", "You are trying to add an " +
                        "option (with all dependencies), one of them are not" +
                        "consistent with options placed in the shopping cart " +
                        "already");
//                request.setAttribute("errorText", "Выпытались добавить опцию " +
//                        "(вместе с обязательными для нее другими опциями), одна" +
//                        " из которых несовместима с опциями в корзине");

            }
        }
        return showContract(model, contractId, tariffId, session);
    }
    @RequestMapping(value="/app/PayForCart", method = RequestMethod.POST)
    public String addTariffAndOptionToCart(@RequestParam("contractId") Integer contractId,
                                           HttpSession session, Model model) {

        Cart cart = (Cart) session.getAttribute("cart");
        contractService.applyCart(cart, contractId);
        session.removeAttribute("cart");
        return showContract(model, contractId, cart.getTariffDTO().getTariffId(), session);
    }


    @RequestMapping(value="app/RemoveOptionFromContract", method = RequestMethod.POST)
    public String removeOptionFromContract(@RequestParam("contractId") Integer contractId,
                                           @RequestParam("tariffId") Integer tariffId,
                                           @RequestParam("optionId") Integer optionId, HttpSession session,
                                           Model model) {

        contractService.removeOptionWithAllDependent(optionId,contractId);
        model.addAttribute("successText", "Chosen option with all dependencies were removed");
        return showContract(model, contractId, tariffId, session);
    }


}
