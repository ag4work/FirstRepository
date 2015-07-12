package controller;

import exceptions.BlockedByStaffException;
import service.ContractService;
import service.ContractServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alexey on 12.07.2015.
 */
@WebServlet(name = "clientContractDashboardBlockUnblock",urlPatterns = "/clientContractDashboardBlockUnblock.sec")
public class clientContractDashboardBlockUnblock extends HttpServlet {
    ContractService contractService = ContractServiceImpl.getInstance();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Integer contractId = Integer.parseInt(request.getParameter("contractId"));
        String command = request.getParameter("command");
        if (command.equals("block")){
            contractService.blockByClient(contractId);
            request.setAttribute("successText", "Контракт заблокирован пользователем.");
        }
        else
        if (command.equals("unblock")) {
            try {
                contractService.unblockByClient(contractId);
                request.setAttribute("successText", "Контракт разблокирован пользователем.");
            } catch (BlockedByStaffException e){
                request.setAttribute("errorText", "Контракт заблокирован " +
                        "сотрудником и не может быть разблокирован пользователем");
            }
        }
        request.getRequestDispatcher("clientContractDashboard.sec").forward(request, response);
//        response.sendRedirect("clientContractDashboard.sec");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
