package controller;


import service.ContractService;
import service.ContractServiceImpl;
import service.DTO.ContractDTO;
import service.DTO.UserDTO;
import service.UserService;
import service.UserServiceGenericBasedImpl;
import utils.EntityManagerFactorySingleton;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alexey on 02.07.2015.
 */
@WebServlet(name = "ContractAddNew.sec", urlPatterns = "/addContract.sec")
public class ContractAddNewsec extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        ContractDTO contractDTO = new ContractDTO();
        UserService userService = new UserServiceGenericBasedImpl();

        Integer userId = Integer.parseInt(request.getParameter("userId"));
        UserDTO  userDTO = userService.getUserById(userId);
        contractDTO.setUserByClientId(userDTO);

        Integer balance = Integer.parseInt(request.getParameter("initialbalance"));
        contractDTO.setBalance(balance);
        Long phoneNum = Long.parseLong(request.getParameter("phoneNumber"));
        contractDTO.setPhoneNumber(phoneNum);

        ContractService contractService = new ContractServiceImpl();
        contractService.add(contractDTO);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
