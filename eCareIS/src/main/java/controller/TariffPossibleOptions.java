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
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alexey on 04.07.2015.
 */
@WebServlet(name = "TariffPossibleOptions", urlPatterns = "/TariffPossibleOptions.sec")
public class TariffPossibleOptions extends HttpServlet {
    Logger logger = Logger.getLogger(TariffShowAll.class);
    OptionService optionService = OptionServiceImpl.getInstance();
    TariffService tariffService = TariffServiceImpl.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Integer tariffId = Integer.parseInt(request.getParameter("tariffId"));
        TariffDTO tariffDTO = tariffService.getTariffById(tariffId);
        Set<OptionDTO> allOptions = optionService.getAllOptions();
        Set<OptionDTO> tariffPossibleOptions = tariffService.
                getTariffById(tariffId).getPossibleOption();
        Set<OptionDTO> tariffPossibleOptionsWithSets = new HashSet<OptionDTO>();
        for (OptionDTO optionDTOWithoutSets : tariffPossibleOptions){
            tariffPossibleOptionsWithSets.add(optionService.getOptionById(
                    optionDTOWithoutSets.getOptionId()));
        }

        allOptions.removeAll(tariffPossibleOptionsWithSets);

        request.setAttribute("tariffPossibleOptions", tariffPossibleOptionsWithSets);
        request.setAttribute("allOtherOptions", allOptions);
        request.setAttribute("tariff", tariffDTO);
        request.getRequestDispatcher("WEB-INF/pages/tariffChoosePossibleOption.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
