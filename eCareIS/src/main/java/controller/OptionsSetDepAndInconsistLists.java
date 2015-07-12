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
    OptionService optionService = OptionServiceImpl.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Integer optionId = Integer.parseInt(request.getParameter("optionId"));
        OptionDTO currentOption = optionService.getOptionById(optionId);
        Set<OptionDTO> dependentOptions = currentOption.getDependentOption();
        Set<OptionDTO> inconsistentOptions = currentOption.getInconsistentOption();
        Set<OptionDTO> allOtherOptions = optionService.getAllOptions();
        allOtherOptions.removeAll(dependentOptions);
        allOtherOptions.remove(currentOption);
        allOtherOptions.removeAll(inconsistentOptions);

        request.setAttribute("currentOption", currentOption);
        request.setAttribute("allOtherOptions", allOtherOptions);
        request.getRequestDispatcher("WEB-INF/pages/optionEdit.jsp").forward(request,response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
