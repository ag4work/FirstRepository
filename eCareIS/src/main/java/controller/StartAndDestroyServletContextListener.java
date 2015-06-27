package controller;

import DAO.EntityManagerFactorySingleton;
import org.apache.log4j.Logger;
import utils.ValidationUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Date;

/**
 * Created by Alexey on 24.06.2015.
 */
public class StartAndDestroyServletContextListener implements ServletContextListener{
    Logger logger = Logger.getLogger(StartAndDestroyServletContextListener.class);
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        logger.info("Servlet context initialization");
        EntityManagerFactorySingleton.getInstance(); //initialise here
        ValidationUtil.getValidatorInstance(); //initialise here
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        EntityManagerFactorySingleton.getInstance().close();
        logger.info("Servlet context  was destroyed");
    }
}
