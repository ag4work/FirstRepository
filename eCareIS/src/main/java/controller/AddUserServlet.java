package controller;


import entity.User;
import service.DTO.UserDTO;
import service.UserService;
import service.UserServiceGenericBasedImpl;
import service.UserServiceImpl;
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
@WebServlet(name = "AddUserServlet", urlPatterns = "/addUser")
public class AddUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");

        // TODO: make that a singleton
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();

        String name = request.getParameter("name");

        UserDTO userDTO = new UserDTO();

        userDTO.setName(request.getParameter("name"));



        userDTO.setLastname(request.getParameter("lastname"));
        userDTO.setAddress(request.getParameter("address"));
        userDTO.setPassport(request.getParameter("passport"));

        DateFormat formatter1;
        formatter1 = new SimpleDateFormat("yyyy-mm-DD");
        Date userBirthday = null;
        try {
            userBirthday = (Date) formatter1.parse(request.getParameter("birthday"));
            userDTO.setBirthday(userBirthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        userDTO.setEmail(request.getParameter("email"));
        userDTO.setPassword(request.getParameter("password"));
        userDTO.setRole(Integer.parseInt(request.getParameter("usertype")));
        String errorMessages = ValidationUtil.validateResult(userDTO);
        if (!errorMessages.isEmpty()) {
            request.setAttribute("errorText", errorMessages);
            response.setContentType("text/html;charset=utf-8");
            request.getRequestDispatcher("createuser.jsp").forward(request,response);
            return;
        } else {
            UserService userService = new UserServiceGenericBasedImpl();
            userService.addUser(userDTO);
//           response.sendRedirect("/users");
            request.setAttribute("successText", "Пользователь успешно добавлен");
            response.setContentType("text/html;charset=utf-8");
            request.getRequestDispatcher("createuser.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.getRequestDispatcher("createuser.jsp").forward(req,resp);
    }
}
