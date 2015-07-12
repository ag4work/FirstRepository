package controller;

import service.ContractService;
import service.ContractServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alexey on 09.07.2015.
 */
@WebServlet(name = "ContractEditRemoveOption", urlPatterns = "/ContractEditRemoveOption.sec")
public class ContractEditRemoveOption extends HttpServlet {
    ContractService contractService = ContractServiceImpl.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Integer contractId = Integer.parseInt(request.getParameter("contractId"));
        Integer optiontId = Integer.parseInt(request.getParameter("optionId"));
        contractService.removeOptionWithAllDependent(optiontId,contractId);
        request.setAttribute("successText", "Chosen option with all dependencies were removed");

        request.getRequestDispatcher("contractEdit.sec").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
