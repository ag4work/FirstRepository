package controller;

import DAO.EntityManagerFactorySingleton;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Date;

/**
 * Created by Alexey on 24.06.2015.
 */
public class StartAndDestroyServletContextListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        System.out.println(new Date() + "   Context init");
        EntityManagerFactorySingleton.getInstance();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        EntityManagerFactorySingleton.getInstance().close();
        System.out.println(new Date() + "   Context was destroyed");
    }
}
