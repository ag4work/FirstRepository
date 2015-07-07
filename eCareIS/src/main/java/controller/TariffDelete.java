package controller;

import entity.Contract;
import org.apache.log4j.Logger;
import service.ContractService;
import service.ContractServiceImpl;
import service.DTO.ContractDTO;
import service.DTO.TariffDTO;
import service.TariffService;
import service.TariffServiceImpl;
import utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alexey on 06.07.2015.
 */
@WebServlet(name = "TariffDelete", urlPatterns = "/deleteTariff.sec")
public class TariffDelete extends HttpServlet {
    Logger logger = Logger.getLogger(TariffShowAll.class);
    TariffService tariffService = new TariffServiceImpl();
    ContractService contractService = new ContractServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Integer tariffToDeleteId = Integer.parseInt(request.getParameter("tariffId"));
        String command = request.getParameter("command");
        if (tariffToDeleteId == Constants.DEFAULT_TARIFF_ID){
            request.setAttribute("errorText", "Нельзя удалять этот тариф." +
                    " Он используется как базовый при подключении " +
                    "нового пользователя. Также при удалении других тарифов " +
                    "пользователи автоматически переключаются с " +
                    "удаляемого тарифа на тариф по умолчанию.");
            request.getRequestDispatcher("allTariffs.sec").forward(request, response);
        }
        else {
            // если подтв удаление, то удаляем
            // иначе показываем страничку с текстом подтв удаления
            Set<ContractDTO> contractsByTariff = contractService.getContractsByTariff(tariffToDeleteId);

            TariffDTO tariffDTO = tariffService.getTariffById(tariffToDeleteId);

            if (command != null && command.equals("deleteConfirmed")) {
                tariffService.removeTariffAndMoveContractsToBaseTariff(tariffToDeleteId);
                Set<ContractDTO> updatedContractDTOs = new HashSet<ContractDTO>();
                for (ContractDTO contractDTO : contractsByTariff)
                    updatedContractDTOs.add(contractService.getContract(contractDTO.getContractId()));
                contractsByTariff = updatedContractDTOs;
                request.setAttribute("tariffDeleted", true);
                request.setAttribute("successText", "Тариф \""
                        + tariffDTO.getTitle()+
                        "\" удален. Контракты, привязанные к нему переведены" +
                        " на тариф \"Базовый\"");
            }


            request.setAttribute("contractSet", contractsByTariff);
            request.setAttribute("tariff", tariffDTO);
            request.getRequestDispatcher("WEB-INF/pages/tariffDeleteConfirmation.jsp").forward(request, response);

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
