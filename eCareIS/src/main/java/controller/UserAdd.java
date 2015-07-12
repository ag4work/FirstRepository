package controller;


import entity.User;
import org.apache.log4j.Logger;
import service.DTO.UserDTO;
import service.UserService;
import service.UserServiceGenericBasedImpl;
import utils.DateValidationUtilString;
import utils.ValidationUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Alexey on 24.06.2015.
 */
@WebServlet(name = "AddUserServlet", urlPatterns = "/addUser.sec")
public class UserAdd extends HttpServlet {

    Logger logger = Logger.getLogger(UserAdd.class);

    final String createUserJSPURL = "WEB-INF/pages/createuser.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");

        String name = request.getParameter("name");

        UserDTO userDTO = new UserDTO();

        userDTO.setName(request.getParameter("name"));
        userDTO.setLastname(request.getParameter("lastname"));
        userDTO.setAddress(request.getParameter("address"));
        userDTO.setPassport(request.getParameter("passport"));

        String inputedDateString = request.getParameter("birthday");
        if (inputedDateString!=null) {
            DateFormat dateFormatter;
            dateFormatter = new SimpleDateFormat("yyyy-mm-DD");
            Date userBirthday;
            try {
                userBirthday = (Date) dateFormatter.parse(inputedDateString);
                userDTO.setBirthday(userBirthday);
            } catch (ParseException e) {
                //   e.printStackTrace();
                logger.error(e);
            }
        } else {
            userDTO.setBirthday(null);  // that will be handled by validator
        }
        userDTO.setEmail(request.getParameter("email"));
        userDTO.setPassword(request.getParameter("password"));
        userDTO.setRole(Integer.parseInt(request.getParameter("usertype")));
        String errorMessages = ValidationUtil.validateResult(userDTO) +
                ValidationUtil.validateResult(new DateValidationUtilString(inputedDateString));

        if (!errorMessages.isEmpty()) {
            request.setAttribute("errorText", errorMessages);
            response.setContentType("text/html;charset=utf-8");
//            request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/pages/createuser.jsp").forward(request,response);
            request.getRequestDispatcher(createUserJSPURL).forward(request,response);
            return;
        } else {
            try {
                UserService userService = UserServiceGenericBasedImpl.getInstance();
                userService.addUser(userDTO);
                request.setAttribute("successText", "Пользователь успешно добавлен");
            }
            catch (Exception e) {
                logger.error(e);
                request.setAttribute("errorText", "Пользователя добавить не удалось." +
                        "Проблема вскоре будет решена");

            }
            response.setContentType("text/html;charset=utf-8");
            request.getRequestDispatcher(createUserJSPURL).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.getRequestDispatcher(createUserJSPURL).forward(req,resp);
    }



}
