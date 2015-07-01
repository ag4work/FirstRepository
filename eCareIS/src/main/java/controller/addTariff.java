package controller;


import GenericBasedDAO.TariffDAO;
import GenericBasedDAO.TariffDAOImpl;
import entity.Tariff;
import utils.EntityManagerFactorySingleton;

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
@WebServlet(name = "Tariffs", urlPatterns = "/Tariffs.sec")
public class addTariff extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");





        PrintWriter out = response.getWriter();

        int price = Integer.parseInt(request.getParameter("price"));
        //out.println(request.getParameter("name"));

        TariffDAO tariffDAO = new TariffDAOImpl(EntityManagerFactorySingleton.getInstance());
        Tariff tariff = new Tariff();
        tariff.setTitle(request.getParameter("title"));
        tariff.setPrice(price);
        tariffDAO.add(tariff);

        response.sendRedirect("/t");
//        out.println(trainName+"  "+seats);

    }
}
