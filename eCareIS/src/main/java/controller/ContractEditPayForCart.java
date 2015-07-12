package controller;

import service.Cart;
import service.ContractService;
import service.ContractServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Alexey on 09.07.2015.
 */
@WebServlet(name = "ContractEditPayForCart", urlPatterns = "/contractEditPayForCart.sec")
public class ContractEditPayForCart extends HttpServlet {
    ContractService contractService = ContractServiceImpl.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Integer contractId = Integer.parseInt(request.getParameter("contractId"));
        HttpSession session = request.getSession();
        Cart cart = (Cart)session.getAttribute("cart");
        contractService.applyCart(cart,contractId);
        session.removeAttribute("cart");
        request.getRequestDispatcher("contractEdit.sec").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
