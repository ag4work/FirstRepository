package controller;


import entity.User;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Alexey on 24.06.2015.
 */
@WebServlet(name = "AddUserServlet", urlPatterns = "/addUser")
public class AddUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
        User user = new User();
        user.setName(request.getParameter("name"));
        user.setLastname(request.getParameter("lastname"));
        user.setAddress(request.getParameter("address"));
        user.setPassport(request.getParameter("passport"));

        DateFormat formatter1;
        formatter1 = new SimpleDateFormat("yyyy-mm-DD");
        Date userBirthday = null;
        try {
            userBirthday = (Date) formatter1.parse(request.getParameter("birthday"));
            user.setBirthday(userBirthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        user.setRole(Integer.parseInt(request.getParameter("role")));
        UserService userService = new UserServiceImpl();
        userService.addUser(user);
        response.sendRedirect("/users");
    }


}
