package net.flosev.inject;


import net.flosev.util.ClassName;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppContext implements ServletContextListener {
    private static final String APP_CTX_PATH = "contextConfigLocation";
    private static final Logger logger = Logger.getLogger(ClassName.getCurrentClassName());

    // ApplicationContext implementations are Thread-safe (see Spring docs)
    private static ClassPathXmlApplicationContext appCtx;

    public static ApplicationContext getInstance() {
        return appCtx;
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext servletContext = event.getServletContext();
        String appCtxPath = servletContext.getInitParameter(APP_CTX_PATH);
        logger.debug("Found init param " + APP_CTX_PATH + " = " + appCtxPath + " in web.xml");

        if (appCtxPath == null) {
            String msg = "I need init param " + APP_CTX_PATH + " in web.xml";
            logger.error(msg);
            throw new RuntimeException(msg); //todo
        }
        appCtx = new ClassPathXmlApplicationContext(appCtxPath);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        appCtx.destroy();

    }

}
