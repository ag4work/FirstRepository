package controller;

import org.apache.log4j.Logger;

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
    private final static Logger logger = Logger.getLogger(AuthorisationFilter.class);

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession(false);

        String uri = request.getRequestURI();
        logger.info("Requested : " + uri);

        if (session==null) {
            logger.info("Request denied: no session");
            response.sendRedirect("staffLogin");
        } else {
            if (session.getAttribute("userName") == null) {
                logger.info("Request denied: authorisation failed");
                response.sendRedirect("staffLogin");
            } else {
                chain.doFilter(req, resp);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {
        logger.info("SessionFilter initialization");
    }

}
