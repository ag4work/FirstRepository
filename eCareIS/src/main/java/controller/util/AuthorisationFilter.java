package controller.util;

import entity.Contract;
import org.apache.log4j.Logger;
import service.ContractService;
import service.ContractServiceImpl;
import service.DTO.ContractDTO;
import utils.Constants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Alexey on 28.06.2015.
 */

@WebFilter(filterName = "AuthorisationFilter", urlPatterns = "*.sec")
public class AuthorisationFilter implements Filter {
    ContractService contractService = new ContractServiceImpl();
    private final static Logger logger = Logger.getLogger(AuthorisationFilter.class);

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        try
        {

            HttpSession session = request.getSession(false);

            String uri = request.getRequestURI();
            logger.info("Requested : " + uri);

            if (session==null) {
                logger.info("Request denied: no session");
                response.sendRedirect("accdenied.jsp");
                return;
            }
            else
            {
                logger.info("session is not null");
                SessionUserInfo sessionUserInfo = (SessionUserInfo)session.
                        getAttribute(Constants.SESSION_USER_INFO_STR);
                if (sessionUserInfo.getRole()==Constants.ADMIN){
                    logger.info("Admin: passed");
                    chain.doFilter(req, resp);
                    return;
                }
                if (sessionUserInfo.getRole()==Constants.CLIENT){
                    logger.info("Client");
                    if (!isUrlAllowed(uri)){
                        logger.info(uri + " is not allowed for the client");
                        request.setAttribute("errorText","У вас нет доступа к запрашиваемой странице.");
                        request.getRequestDispatcher("accdenied.jsp").forward(request, response);
//                        response.sendRedirect("accdenied.jsp");
                        return;
                    }
                    else
                    {
                        logger.info("Requested secure url is allowed");

                        Integer sessContractId = sessionUserInfo.getContracId();
                        logger.info("Requested sessContractId:"+sessContractId);
                        if (sessContractId==null){
                            logger.error("sessContractId is null");
                            response.sendRedirect("accdenied.jsp");
                            return;
                        }

                        String reqContractIdStr = request.getParameter("contractId");
                        if (reqContractIdStr!=null) {
                            logger.info("reqContractIdStr is not null");
                            Integer reqContractId = Integer.parseInt(reqContractIdStr);
                            if (!sessContractId.equals(reqContractId)) {
                                logger.warn("Session and request contractId are not the same");
                                response.sendRedirect("accdenied.jsp");
                                return;
                            }
                            logger.info("Session and request contractId are the same");
                        }
                        ContractDTO contractDTO = contractService.getContract(sessContractId);
                        if (!contractDTO.getBlockedByStaff()){
                               logger.info("Client is not blocked and was passed");
                               chain.doFilter(req, resp);
                        }
                        else
                        {
                            logger.warn("Client is blocked by staff");
                            if (isUrlAllowedForBlocked(uri)){
                                logger.info("Client Passed! Url is allowed for blocked by staff client");
                                chain.doFilter(req, resp);
                            }
                            else {
                                logger.warn("Url is not allowed for blocked by staff client. Access denied.");
//                                response.sendRedirect("accdenied.jsp");
                                request.setAttribute("errorText","Ваш контракт заблокирован оператором. Запрашиваемая операция недоступна.");
                                request.getRequestDispatcher("accdenied.jsp").forward(request, response);

                            }
                        }
                    }
                }
            }

        }
        catch (Exception e){
            logger.error("Something went wrong in authorization filter:"+e);
            response.sendRedirect("accdenied.jsp");
        }
    }

    public void init(FilterConfig config) throws ServletException {
        logger.info("SessionFilter initialization");
    }

    public static  boolean isUrlAllowed(String url){
        for (String allowedString : Constants.URLS_ALLOWED_FOR_CLIENT){
            if (url.contains(allowedString)) return true;
        }
        return false;
    }

    public static  boolean isUrlAllowedForBlocked(String url){
        for (String allowedString : Constants.URLS_ALLOWED_FOR_BLOCKED_BY_STUFF_CLIENT){
            if (url.contains(allowedString)) return true;
        }
        return false;
    }


}
