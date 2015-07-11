package controller;

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
@WebServlet(name = "OptionRemoveInconsistent", urlPatterns = "/removeInconsistentOption.sec")
public class OptionRemoveInconsistent extends HttpServlet {
    OptionService optionService = new OptionServiceImpl();
    Logger logger = Logger.getLogger(OptionDelete.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
        Integer baseOptionId = Integer.parseInt(request.getParameter("optionId"));
        Integer inconsistentOptionId = Integer.parseInt(request.getParameter("inconsistentOptionId"));
        optionService.removeInconsistency(baseOptionId,inconsistentOptionId);
        request.setAttribute("successText", "Несовместимость успешно удалена");
        request.getRequestDispatcher("OptionsSetDepAndInconsistLists.sec").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
