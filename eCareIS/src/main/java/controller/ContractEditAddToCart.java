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
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Alexey on 08.07.2015.
 */
@WebServlet(name = "ContractEditAddToCart", urlPatterns = "/ContractEditAddToCart.sec")
public class ContractEditAddToCart extends HttpServlet {
    ContractService contractService = ContractServiceImpl.getInstance();
    OptionService optionService = OptionServiceImpl.getInstance();
    TariffService tariffService = TariffServiceImpl.getInstance();
    CartService cartService = new CartService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Integer contractId = Integer.parseInt(request.getParameter("contractId"));
        Integer optionId = Integer.parseInt(request.getParameter("optionId"));
        Integer tariffId = Integer.parseInt(request.getParameter("tariffId"));

        ContractDTO contractDTO = contractService.getContract(contractId);
        TariffDTO tariffDTO = tariffService.getTariffById(tariffId);
        OptionDTO optionDTO = optionService.getOptionById(optionId);

        HttpSession session = request.getSession();
        Cart cart = (Cart)session.getAttribute("cart");
        if (cart==null || !cart.getContractId().equals(contractId) ||
                !cart.getTariffDTO().equals(tariffDTO)){
            cart = new Cart();
            cartService.addOptionWithAllRequired(optionDTO,cart);
            cart.setTariffDTO(tariffDTO);
            cart.setContractId(contractId);
            session.setAttribute("cart", cart);
        }
        else {
            if (cartService.isOptionConsistentWithOptionsInCart(optionDTO,cart))
                cartService.addOptionWithAllRequired(optionDTO,cart);
            else
            {
                request.setAttribute("errorText", "You are trying to add an " +
                        "option (with all dependencies), one of them are not" +
                        "consistent with options placed in the shopping cart " +
                                "already");
//                request.setAttribute("errorText", "Выпытались добавить опцию " +
//                        "(вместе с обязательными для нее другими опциями), одна" +
//                        " из которых несовместима с опциями в корзине");

            }
        }
        request.getRequestDispatcher("contractEdit.sec").forward(request, response);

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
