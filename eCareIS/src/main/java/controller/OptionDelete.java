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
 * Created by Alexey on 10.07.2015.
 */
@WebServlet(name = "OptionDelete", urlPatterns = "/optionDelete.sec")
public class OptionDelete extends HttpServlet {
    Logger logger = Logger.getLogger(OptionDelete.class);
    OptionService optionService = OptionServiceImpl.getInstance();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");

        try {
            Integer optionId = Integer.parseInt(request.getParameter("optionId"));
            logger.info("try to delete option: "+optionId);
            optionService.delete(optionId);
            request.setAttribute("successText", "Опция успешно удалена.");
        }
        catch (Exception e) {
            logger.error("something went wrong while trying to delete option");
            request.setAttribute("errorText", "Во время удаления опции возникла неполадка.");

        } finally {
            request.getRequestDispatcher("allOptions.sec").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
