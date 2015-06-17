package controller;

import DAO.TariffDAO;
import DAO.TariffDAOJDBCImpl;
import entity.Tariff;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Alexey on 11.06.2015.
 */
@WebServlet("/addTariff")
public class addTariff extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html;charset=windows-1251");
        PrintWriter out = response.getWriter();
        //request.setCharacterEncoding("Cp1251");
        request.setCharacterEncoding("utf8");

        int price = Integer.parseInt(request.getParameter("price"));
        //out.println(request.getParameter("name"));

        TariffDAO tariffDAO = new TariffDAOJDBCImpl();
        Tariff tariff = new Tariff();
        tariff.setTitle(request.getParameter("title"));
        tariff.setPrice(price);
        tariffDAO.create(tariff);

        response.sendRedirect("/t");
//        out.println(trainName+"  "+seats);

    }
}
