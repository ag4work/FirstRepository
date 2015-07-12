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
 * Created by Alexey on 05.07.2015.
 */
@WebServlet(name = "contractBlockStatusEdit", urlPatterns = "/contractBlockStatusEdit.sec")
public class ContractBlockStatusEdit extends HttpServlet {
    ContractService contractService = ContractServiceImpl.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Integer contractId = Integer.parseInt(request.getParameter("contractId"));
        String command = request.getParameter("command");
        if (command.equals("block")){
            contractService.blockByStaff(contractId);
        }
        else {
            if (command.equals("unblock")) {
                contractService.unblockByStaff(contractId);
            }
        }
        response.sendRedirect("contracts.sec");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
