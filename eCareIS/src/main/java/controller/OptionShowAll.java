package controller;

import org.apache.log4j.Logger;
import service.DTO.OptionDTO;
import service.OptionService;
import service.OptionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * Created by Alexey on 04.07.2015.
 */
@WebServlet(name = "OptionShowAll", urlPatterns = "/allOptions.sec")
public class OptionShowAll extends HttpServlet {

    Logger logger = Logger.getLogger(TariffShowAll.class);
    OptionService optionService = new OptionServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Set<OptionDTO> optionDTOs = optionService.getAllOptions();
        request.setAttribute("options", optionDTOs);
        request.getRequestDispatcher("WEB-INF/pages/options.jsp").forward(request,response);

    }
}
