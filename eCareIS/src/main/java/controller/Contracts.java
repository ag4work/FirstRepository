package controller;

import service.ContractService;
import service.ContractServiceImpl;
import service.DTO.ContractDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * Created by Alexey on 05.07.2015.
 */
@WebServlet(name = "Contracts", urlPatterns = "/contracts.sec")
public class Contracts extends HttpServlet {
    ContractService contractService = new ContractServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Set<ContractDTO> contractDTOs = contractService.getAllContracts();
        request.setAttribute("contractSet", contractDTOs);
        request.getRequestDispatcher("WEB-INF/pages/contracts.jsp").forward(request,response);
    }
}
