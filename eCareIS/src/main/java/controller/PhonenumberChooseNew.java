package controller;

import org.apache.log4j.Logger;
import service.DTO.UserDTO;
import service.UserService;
import service.UserServiceGenericBasedImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Alexey on 01.07.2015.
 */
@WebServlet(name = "PhonenumberChooseNew", urlPatterns = "/phonenumber_choosenew.sec")
public class PhonenumberChooseNew extends HttpServlet {
    Logger logger = Logger.getLogger(PhonenumberChooseNew.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
//        request.setAttribute("userList", userDTOList);
        String id = (String)request.getParameter("id");
        logger.info("id = " + id);
        request.setAttribute("userId",id);
        request.getRequestDispatcher("WEB-INF/pages/phonenumber_choose.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
