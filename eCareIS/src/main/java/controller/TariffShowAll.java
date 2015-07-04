package controller;


import org.apache.log4j.Logger;
import service.DTO.TariffDTO;
import service.TariffService;
import service.TariffServiceImpl;
import utils.EntityManagerFactorySingleton;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;


/**
 * Created by Alexey on 11.06.2015.
 */
@WebServlet(name = "TariffsShowAll", urlPatterns = "/allTariffs.sec")
public class TariffShowAll extends javax.servlet.http.HttpServlet {

    Logger logger = Logger.getLogger(TariffShowAll.class);
    TariffService tariffService = new TariffServiceImpl();

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Set<TariffDTO> tariffDTOs = tariffService.getAllTariffs();
        request.setAttribute("tariffs", tariffDTOs);
        request.getRequestDispatcher("WEB-INF/pages/tariffs.jsp").forward(request,response);

    }
}



