package controller;

import DAO.TariffDAO;
import DAO.TariffDAOJDBCImpl;
import entity.Tariff;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


/**
 * Created by Alexey on 11.06.2015.
 */
@WebServlet("/t")
public class TariffServlet extends javax.servlet.http.HttpServlet {


    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<p> sdfs </p>");
        TariffDAO tariffDAO = new TariffDAOJDBCImpl();
        List<Tariff> tariffs = tariffDAO.findAll();
        request.setAttribute("tariffList",tariffs);
        request.getRequestDispatcher("tariffs.jsp").forward(request,response);;

    }
}



