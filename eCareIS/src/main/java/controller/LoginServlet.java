package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import DAO.*;
import entity.Contract;
import entity.Tariff;
import entity.User;
import service.ContractService;
import service.ContractServiceImpl;
import service.DTO.UserDTO;
import service.UserService;
import service.UserServiceImpl;

/**
 * Created by Alexey on 24.06.2015.
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/login" )
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        UserService userService = new UserServiceImpl();
        UserDTO userDTO = userService.userWithEmailAndPasswordExists(email,password);

        PrintWriter out = response.getWriter();

        if (userDTO!=null){

            ContractService contractService = new ContractServiceImpl();
            List<Contract> contracts = contractService.getAllContracts();
            request.setAttribute("contractList",contracts);
            request.getRequestDispatcher("contracts.jsp").forward(request,response);
        }
        else{
            //out.println("Such user was not found");
            request.setAttribute("BadEmailPassword","BadEmailPassword");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }





    }
}
