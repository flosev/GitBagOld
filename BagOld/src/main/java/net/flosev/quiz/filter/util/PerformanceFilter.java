package net.flosev.quiz.filter.util;

import net.flosev.quiz.filter.BaseFilter;
import org.apache.log4j.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpUtils;
import java.io.IOException;

import static net.flosev.util.ClassName.getCurrentClassName;
import static org.apache.log4j.Logger.getLogger;

/**
 * Фильтр замеряет время реакции на каждый запрос
 * 1. Поставленный первым в цепочке фильтров ЗАМЕРЯЕТ
 * также время работы других фильтров при обработке запроса.
 * |----------| -> |--------| ->    -> |--------| -> |-----------|
 * |TimeFilter|    |Filter-A|   ...    |Filter-Z|    |JSP/Servlet|
 * |----------| <- |--------| <-    <- |--------| <- |-----------|
 *
 * 2. Поставленный последним в цепочке фильтров НЕ ЗАМЕРЯЕТ
 * время работы других фильтров при обработке запроса.
 * |--------| -> |--------| ->    -> |----------| -> |-----------|
 * |Filter-A|    |Filter-Z|   ...    |TimeFilter|    |JSP/Servlet|
 * |--------| <- |--------| <-    <- |----------| <- |-----------|
 */
public class PerformanceFilter extends BaseFilter {
    private static final Logger logger = getLogger(getCurrentClassName());

    @Override
    public void doHttpFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        // тут мы находимся ДО входа в систему
        long inTime = System.nanoTime();
        try {
            // это ВХОД в систему
            chain.doFilter(req, res);
        } finally {
            // тут мы находимся ПОСЛЕ выхода из системы
            long outTime = System.nanoTime();
            long dT = outTime - inTime;
            logger.debug(String.format("requestURL = '%s', queryString = '%s', dT = %,d", HttpUtils.getRequestURL(req), req.getQueryString(), dT));
        }
    }
}
