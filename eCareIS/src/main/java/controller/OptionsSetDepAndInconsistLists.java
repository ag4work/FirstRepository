package controller;

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
 * Created by Alexey on 08.07.2015.
 */
@WebServlet(name = "OptionsSetDepAndInconsistLists", urlPatterns = "/OptionsSetDepAndInconsistLists.sec")
public class OptionsSetDepAndInconsistLists extends HttpServlet {
    OptionService optionService = new OptionServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Integer optionId = Integer.parseInt(request.getParameter("optionId"));
        Set<OptionDTO> options = optionService.getAllOptions();
        request.setAttribute("currentOption", optionService.getOptionById(optionId));
        request.setAttribute("options", options);
        request.getRequestDispatcher("WEB-INF/pages/optionEdit.jsp").forward(request,response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
