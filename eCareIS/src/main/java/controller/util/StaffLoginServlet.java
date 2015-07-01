package controller.util;

import entity.Contract;
import service.*;
import service.DTO.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Alexey on 28.06.2015.
 */


@WebServlet(name = "StaffLoginServlet", urlPatterns = "/staffLogin")
public class StaffLoginServlet extends HttpServlet {

    final String loginPageURL = "/login.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserService userService = new UserServiceGenericBasedImpl();
        UserDTO userDTO = userService.userWithEmailAndPasswordExists(email,password);

        if (userDTO!=null){
            HttpSession session = request.getSession();
            session.setAttribute("userName", userDTO.getName());
            response.sendRedirect("users.sec");
//            request.getRequestDispatcher("/users.sec").forward(request,response);
        }
        else{
            request.setAttribute("errorText","Пользователя с такими паролем и логином не существует");
            request.getRequestDispatcher(loginPageURL).forward(request, response);
        }

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.getRequestDispatcher(loginPageURL).forward(req, resp);
    }



}
