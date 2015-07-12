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
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alexey on 05.07.2015.
 */
@WebServlet(name = "ContractSearch", urlPatterns = "/ContractSearch.sec")
public class ContractSearch extends HttpServlet {
    ContractService contractService = ContractServiceImpl.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long phoneNumber = Long.parseLong(request.getParameter("phoneNumber"));
        ContractDTO foundContractDTO = contractService.getContractByPhonenumber(phoneNumber);
        Set<ContractDTO> contractDTOs = new HashSet<ContractDTO>();
        if (foundContractDTO==null) {
            contractDTOs = Collections.emptySet();
        }
        else
        {
            contractDTOs.add(foundContractDTO);
        }
        request.setAttribute("contractSet", contractDTOs);
        request.getRequestDispatcher("WEB-INF/pages/contracts.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
