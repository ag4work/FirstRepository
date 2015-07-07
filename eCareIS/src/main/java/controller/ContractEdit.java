package controller;

import service.*;
import service.DTO.ContractDTO;
import service.DTO.OptionDTO;
import service.DTO.TariffDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Alexey on 07.07.2015.
 */
@WebServlet(name = "ContractEdit", urlPatterns = "/contractEdit.sec")
public class ContractEdit extends HttpServlet {
    ContractService contractService = new ContractServiceImpl();
    OptionService optionService = new OptionServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Integer contractId = Integer.parseInt(request.getParameter("contractId"));
        ContractDTO contractDTO;
        if (contractId!=null){
            contractDTO = contractService.getContract(contractId);
            request.setAttribute("contract", contractDTO);
        }

        TariffService tariffService = new TariffServiceImpl();
        List<TariffDTO> tariffDTOs = new ArrayList<TariffDTO>(tariffService.getAllTariffs());
        TariffDTO firstTariff;
        if (tariffDTOs!=null && tariffDTOs.size()>0){
            request.setAttribute("tariffs",tariffDTOs);
            firstTariff = tariffDTOs.get(0);
            Set<OptionDTO> firstTariffOptions = firstTariff.getPossibleOption();
            Set<OptionDTO> optionsWithFullInfo = new HashSet<OptionDTO>();
            for (OptionDTO option : firstTariffOptions){
                optionsWithFullInfo.add(optionService.getOptionById(option.getOptionId()));
            }
            request.setAttribute("chosenTariffOptions", optionsWithFullInfo);
        }
        request.getRequestDispatcher("WEB-INF/pages/ContractEdit.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
