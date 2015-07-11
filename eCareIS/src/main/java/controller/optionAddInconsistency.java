package controller;

import exceptions.OptionInconsistencyImpossibleException;
import org.apache.log4j.Logger;
import service.OptionService;
import service.OptionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alexey on 12.07.2015.
 */
@WebServlet(name = "optionAddInconsistency", urlPatterns = "/optionAddInconsistency.sec")
public class optionAddInconsistency extends HttpServlet {
    OptionService optionService = new OptionServiceImpl();
    Logger logger = Logger.getLogger(OptionDelete.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
        Integer baseOptionId = Integer.parseInt(request.getParameter("optionId"));
        Integer inconsistentOptionId = Integer.parseInt(request.getParameter("inconsistentOptionId"));
        try {
            optionService.addInconsistency(baseOptionId, inconsistentOptionId);
            request.setAttribute("successText", "Несовместимость успешно добавлена.");
        } catch (OptionInconsistencyImpossibleException e) {
            request.setAttribute("errorText", "Выбранная несовместимость не может быть " +
                    "добавлена в силу существующих зависистей в дереве опций.");
        }
        request.getRequestDispatcher("OptionsSetDepAndInconsistLists.sec").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
