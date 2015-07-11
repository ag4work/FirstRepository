package controller;

import exceptions.CycleInOptionsDependencyException;
import exceptions.InconsistentOptionDependency;
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
@WebServlet(name = "OptionAddDependency", urlPatterns = "/optionAddDependency.sec")
public class OptionAddDependency extends HttpServlet {
    OptionService optionService = new OptionServiceImpl();
    Logger logger = Logger.getLogger(OptionDelete.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf8");
        Integer baseOptionId = Integer.parseInt(request.getParameter("optionId"));
        Integer dependentOptionId = Integer.parseInt(request.getParameter("dependentOptionId"));
        try {
            optionService.addDependency(baseOptionId,dependentOptionId);
            request.setAttribute("successText", "Зависимость успешно добавлена.");
        } catch (InconsistentOptionDependency inconsistentOptionDependency) {
            request.setAttribute("errorText", "Зависимость не может быть добавлена в силу имеющихся установленных несовместимостей между опциями." +
                    "Проверяется несовместимость между 1. объединением множеств опций(зависимых и требующихся) для выбранной зависимой опции и 2." +
                    "множеством всех требующихся точек для базовой опции");
            inconsistentOptionDependency.printStackTrace();
        } catch (CycleInOptionsDependencyException e) {
            request.setAttribute("errorText", "Зависимость не может быть добавлена. Это привело бы к зацикливанию зависимостей.");
            e.printStackTrace();
        }

        request.getRequestDispatcher("OptionsSetDepAndInconsistLists.sec").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
