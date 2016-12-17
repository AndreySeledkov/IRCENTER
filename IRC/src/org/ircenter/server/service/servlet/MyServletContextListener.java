package org.ircenter.server.service.servlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Arrays;

public class MyServletContextListener implements ServletContextListener {

    private static final Log LOGGER = LogFactory.getLog(MyServletContextListener.class.getClass());

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("----------------------------------------------------------OLOLO");

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOGGER.info("---------------------------------------------------------------------------");
        LOGGER.info(Arrays.toString((new Throwable()).getStackTrace()));
        LOGGER.info("---------------------------------------------------------------------------");
        System.out.println(Arrays.toString((new Throwable()).getStackTrace()));
//        System.out.println("OLOLO----------------------------------------------------------");
    }
}
