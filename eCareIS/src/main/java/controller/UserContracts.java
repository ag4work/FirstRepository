package controller;

import org.apache.log4j.Logger;
import service.ContractService;
import service.ContractServiceImpl;
import service.DTO.ContractDTO;
import service.DTO.UserDTO;
import service.UserService;
import service.UserServiceGenericBasedImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * Created by Alexey on 03.07.2015.
 */
@WebServlet(name = "UserContracts", urlPatterns = "/showUserContracts.sec")
public class UserContracts extends HttpServlet {
    Logger logger = Logger.getLogger(UserContracts.class);
    ContractService contractService = ContractServiceImpl.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        //ContractService contractService = new ContractServiceImpl();
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        UserService userService = UserServiceGenericBasedImpl.getInstance();
        UserDTO userDTO = userService.getUserById(userId);

        Set<ContractDTO> contractDTOs = contractService.getContractsByUserId(userId);
        request.setAttribute("contractSet", contractDTOs);
        request.setAttribute("user", userDTO);
        request.getRequestDispatcher("WEB-INF/pages/clientContracts.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
