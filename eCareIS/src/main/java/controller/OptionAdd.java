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

/**
 * Created by Alexey on 04.07.2015.
 */
@WebServlet(name = "OptionAdd", urlPatterns = "/addOption.sec")
public class OptionAdd extends HttpServlet {
    Logger logger = Logger.getLogger(UserAdd.class);
    OptionService optionService = new OptionServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
        String title = request.getParameter("title");
        Integer monthlyCost =  Integer.parseInt(request.getParameter("monthlyCost"));
        Integer activationCharge = Integer.parseInt(request.getParameter("activationCharge"));
        OptionDTO optionDTO = new OptionDTO();
        optionDTO.setTitle(title);
        optionDTO.setMonthlyCost(monthlyCost);
        optionDTO.setActivationCharge(activationCharge);
        optionService.addOption(optionDTO);
        response.sendRedirect("allOptions.sec");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
