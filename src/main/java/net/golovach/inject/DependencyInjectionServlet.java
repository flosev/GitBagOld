package net.golovach.inject;

import net.golovach.util.ClassName;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Общий предок для всех сервлетов, которые хотят использовать магию DependencyInjection.
 * todo: как используем Spring
 * todo: как используем Reflection API
 * todo: как используем @Inject
 * todo: ссылки на DI материалы
 * todo: ссылки на IoC материалы
 */
public class DependencyInjectionServlet extends HttpServlet {
    private static final String APP_CTX_PATH = "contextConfigLocation";

    private static final Logger logger = Logger.getLogger(ClassName.getCurrentClassName());

    @Override
    public void init() throws ServletException {
        String appCtxPath = this.getServletContext().getInitParameter(APP_CTX_PATH);
        logger.debug("load " + APP_CTX_PATH + " -> " + appCtxPath);

        if (appCtxPath == null) {
            logger.error("I need init param " + APP_CTX_PATH);
            throw new ServletException(APP_CTX_PATH + " init param == null");
        }

        try {
            // load AppContext
            ApplicationContext appCtx = new ClassPathXmlApplicationContext(appCtxPath);
            // than inject from AppContext to all marked by @Inject fields
            List<Field> allFields = FieldReflector.collectUpTo(this.getClass(), DependencyInjectionServlet.class);
            List<Field> injectFields = FieldReflector.filterInject(allFields);

            for (Field field : injectFields) {
                field.setAccessible(true);
                Inject annotation = field.getAnnotation(Inject.class);
                logger.debug("I find method marked by @Inject: " + field);
                String beanName = annotation.value();
                logger.debug("I must instantiate and inject '" + beanName + "'");
                Object bean = appCtx.getBean(beanName);
                logger.debug("Instantiation - OK: '" + beanName + "'");
                if (bean == null) {
                    throw new ServletException("There isn't bean with name '" + beanName + " in " + APP_CTX_PATH);
                }
                field.set(this, bean);
            }
        } catch (Exception e) {
            throw new ServletException("Can't inject from " + APP_CTX_PATH, e);
        }
    }
}
