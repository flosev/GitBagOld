package net.flosev.inject;

import net.flosev.util.ClassName;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.lang.reflect.Field;
import java.util.Arrays;
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
    private static final Logger logger = Logger.getLogger(ClassName.getCurrentClassName());

    @Override
    public void init() throws ServletException {
        try {
            // than inject from AppContext to all marked by @Inject fields
            List<Field> allFields = FieldReflector.collectFieldsUpToClass(this.getClass(), DependencyInjectionServlet.class);
            List<Field> injectFields = FieldReflector.filterInjectableFields(allFields);

            for (Field field : injectFields) {
                field.setAccessible(true);
                Inject annotation = field.getAnnotation(Inject.class);
                logger.debug("I find method marked by @Inject: " + field);
                String beanName = annotation.value();
                logger.debug("I must instantiate and inject '" + beanName + "'");
                Object bean = AppContext.getInstance().getBean(beanName);
                logger.debug("Instantiation - OK: '" + beanName + "'");
                if (bean == null) {
                    throw new ServletException("There isn't bean with name '" + beanName + " in application context only for " + Arrays.toString(AppContext.getInstance().getBeanDefinitionNames()));
                }
                field.set(this, bean);
            }
        } catch (Exception e) {
            throw new ServletException("Can't inject bean", e);
        }
    }
}
