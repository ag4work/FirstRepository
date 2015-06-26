package controller;

import service.DTO.UserDTO;
import service.UserService;
import service.UserServiceGenericBasedImpl;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Alexey on 24.06.2015.
 */
@WebServlet(name = "UsersServlet", urlPatterns = "/users")
public class UsersServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        UserService userService = new UserServiceGenericBasedImpl();
        List<UserDTO> userDTOList = userService.getAllUsers();
        request.setAttribute("userList", userDTOList);
        request.getRequestDispatcher("users.jsp").forward(request,response);
    }

}
