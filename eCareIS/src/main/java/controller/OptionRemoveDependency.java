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
 * Created by Alexey on 11.07.2015.
 */
@WebServlet(name = "OptionRemoveDependency", urlPatterns = "/deleteOptionDependency.sec")
public class OptionRemoveDependency extends HttpServlet {
    OptionService optionService = OptionServiceImpl.getInstance();
    Logger logger = Logger.getLogger(OptionDelete.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
        Integer baseOptionId = Integer.parseInt(request.getParameter("optionId"));
        Integer dependentOptionId = Integer.parseInt(request.getParameter("dependentOptionId"));
        try {
            optionService.removeDependency(baseOptionId, dependentOptionId);
            request.setAttribute("successText", "Зависимость успешно удалена");
        }
        catch (Exception e){
            logger.warn("Ошибка при удалении зависимости опции id("+
                            dependentOptionId+") для базовой опции id("+
                    baseOptionId+")");
            request.setAttribute("errorText", "При удалении зависимости возникла ошибка");
        }
        request.getRequestDispatcher("OptionsSetDepAndInconsistLists.sec").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
