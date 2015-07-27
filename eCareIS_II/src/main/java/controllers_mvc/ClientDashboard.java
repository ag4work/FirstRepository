package controllers_mvc;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.ContractService;
import service.DTO.ContractDTO;
import utils.Constants;

import javax.servlet.http.HttpSession;

@Controller
public class ClientDashboard {

    @Autowired
    ContractService contractService;
    Logger logger = Logger.getLogger(ClientDashboard.class);


    @RequestMapping(value = "/app/clientDashboard", method = RequestMethod.GET)
    public String showAll(Model model, HttpSession session) {
        logger.info("Client dashboard servlet");

        SessionUserInfo sessionUserInfo  = (SessionUserInfo)session.
                getAttribute(Constants.SESSION_USER_INFO_STR);
        //todo: check up for userinfo is in the session and not null
        ContractDTO contractDTO = contractService.getContract(sessionUserInfo.getContracId());
        logger.info("ContractId in session:" + sessionUserInfo.getContracId());
        model.addAttribute("contract", contractDTO);
        return "clientDashboard";
    }

}
