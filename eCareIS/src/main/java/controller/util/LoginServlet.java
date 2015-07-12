package controller.util;

import org.apache.log4j.Logger;
import service.*;
import service.DTO.ContractDTO;
import service.DTO.UserDTO;
import utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

/**
 * Created by Alexey on 28.06.2015.
 */


@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    final String loginPageURL = "/login.jsp";
    ContractService contractService = ContractServiceImpl.getInstance();
    Logger logger = Logger.getLogger(LoginServlet.class);
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        try {
            String command = request.getParameter("command");
            if (command == null) {
                request.getRequestDispatcher(loginPageURL).forward(request, response);
                return;
            }

            if (command.equals("staffLogin")) {
                String password = request.getParameter("password");
                String email = request.getParameter("email");
                //todo make validation
                UserService userService = UserServiceGenericBasedImpl.getInstance();
                UserDTO userDTO = userService.userWithEmailAndPasswordExists(email, password);
                if (userDTO != null && userDTO.getRole() == Constants.ADMIN) {
                    HttpSession session = request.getSession();
                    SessionUserInfo sessionUserInfo = new SessionUserInfo(userDTO.getRole(), null);
                    session.setAttribute(Constants.SESSION_USER_INFO_STR, sessionUserInfo);
                    response.sendRedirect("users.sec");
                } else {
                    request.setAttribute("errorText", "Сотрудника с такими логином и паролем не существует");
                    request.getRequestDispatcher(loginPageURL).forward(request, response);
                }
            } else if (command.equals("clientLogin")) {
//            todo make validation
                String password = request.getParameter("password");
                Long phonenumber = Long.parseLong(request.getParameter("phonenumber"));
                ContractDTO clientContractDTO = contractService.getContractByPhonenumber(phonenumber);
                if (clientContractDTO != null && clientContractDTO.getUserDTO()
                        .getPassword().equals(password) &&
                        clientContractDTO.getUserDTO().getRole() == Constants.CLIENT) {
                    Integer contractId = clientContractDTO.getContractId();
                    Integer userRole = clientContractDTO.getUserDTO().getRole();
                    HttpSession session = request.getSession();
                    SessionUserInfo sessionUserInfo = new SessionUserInfo(
                            userRole, contractId);
                    session.setAttribute(Constants.SESSION_USER_INFO_STR, sessionUserInfo);
                    response.sendRedirect("clientContractDashboard.sec");
                } else {
                    request.setAttribute("errorText", "Клиента с такими номером и паролем не существует");
                    request.getRequestDispatcher(loginPageURL).forward(request, response);
                }

            }
        } catch (Exception e) {
                logger.error("Something went wrong in LoginServlet:" + e);
                response.sendRedirect("error.jsp");

        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.getRequestDispatcher(loginPageURL).forward(req, resp);
    }



}
