package controller;

import org.apache.log4j.Logger;
import service.DTO.OptionDTO;
import service.DTO.TariffDTO;
import service.OptionService;
import service.OptionServiceImpl;
import service.TariffService;
import service.TariffServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alexey on 05.07.2015.
 */
@WebServlet(name = "TariffPossibleOptionsEdit", urlPatterns = "/TariffPossibleOptionsEdit.sec" )
public class TariffPossibleOptionsEdit extends HttpServlet {
    Logger logger = Logger.getLogger(TariffShowAll.class);
    OptionService optionService = new OptionServiceImpl();
    TariffService tariffService = new TariffServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Integer tariffId = Integer.parseInt(request.getParameter("tariffId"));
        Integer optionId = Integer.parseInt(request.getParameter("optionId"));
        String command = request.getParameter("command");
        if (command.equals("add")) {
            tariffService.addOptionAsPossibleForTariff(tariffId, optionId);
        }
        if (command.equals("remove")) {
            tariffService.removeOptionAsPossibleForTariff(tariffId, optionId);
        }
        request.getRequestDispatcher("TariffPossibleOptions.sec").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
