package controller;


import org.apache.log4j.Logger;
import service.DTO.TariffDTO;
import service.TariffService;
import service.TariffServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alexey on 11.06.2015.
 */
@WebServlet(name = "TariffsAddNew", urlPatterns = "/addTariff.sec")
public class TariffAdd extends HttpServlet {
    Logger logger = Logger.getLogger(TariffAdd.class);

    TariffService tariffService = new TariffServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("TariffAdd started working");
        request.setCharacterEncoding("utf8");

        int price = Integer.parseInt(request.getParameter("price"));
        String title = request.getParameter("title");

        TariffDTO tariffDTO = new TariffDTO();
        tariffDTO.setTitle(title);
        tariffDTO.setPrice(price);

        tariffService.addTariff(tariffDTO);

        response.sendRedirect("allTariffs.sec");

    }
}
