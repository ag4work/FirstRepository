package controller;


import GenericBasedDAO.TariffDAO;
import GenericBasedDAO.TariffDAOImpl;
import entity.Tariff;
import org.apache.log4j.Logger;
import utils.EntityManagerFactorySingleton;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


/**
 * Created by Alexey on 11.06.2015.
 */
@WebServlet("/t")
public class TariffServlet extends javax.servlet.http.HttpServlet {

Logger logger = Logger.getLogger(TariffServlet.class);

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        TariffDAO tariffDAO = new TariffDAOImpl(EntityManagerFactorySingleton.getInstance());
        List<Tariff> tariffs = tariffDAO.getAll();
        request.setAttribute("tariffList",tariffs);
        logger.info(tariffs);
        request.getRequestDispatcher("WEB-INF/pages/tariffs.jsp").forward(request,response);

    }
}



