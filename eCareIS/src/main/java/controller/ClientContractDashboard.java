package controller;

import controller.util.SessionUserInfo;
import org.apache.log4j.Logger;
import service.ContractService;
import service.ContractServiceImpl;
import service.DTO.ContractDTO;
import utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Alexey on 10.07.2015.
 */
@WebServlet(name = "ClientContractDashboard.sec", urlPatterns = "/clientContractDashboard.sec")
public class ClientContractDashboard extends HttpServlet {
    ContractService contractService = ContractServiceImpl.getInstance();
    Logger logger = Logger.getLogger(ClientContractDashboard.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        logger.info("Client dashboard servlet");
        HttpSession session = request.getSession();
        SessionUserInfo sessionUserInfo  = (SessionUserInfo)session.
                getAttribute(Constants.SESSION_USER_INFO_STR);
        ContractDTO contractDTO = contractService.getContract(sessionUserInfo.getContracId());
        logger.info("ContractId in session:" + sessionUserInfo.getContracId());
        request.setAttribute("contract", contractDTO);
        request.getRequestDispatcher("WEB-INF/pages/clientDashboard.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
