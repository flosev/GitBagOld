package net.flosev.quiz.filter.security;

import net.flosev.quiz.filter.BaseFilter;
import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static net.flosev.quiz.controller.SessionAttributes.USER;
import static net.flosev.quiz.filter.security.UrlCodec.encode;
import static net.flosev.util.ClassName.getCurrentClassName;
import static org.apache.log4j.Logger.getLogger;

public class SecurityFilter extends BaseFilter implements Filter {
    private static final Logger logger = getLogger(getCurrentClassName());

    @Override
    public void doHttpFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        //todo:
        if (req.getRequestURI().contains("login.do") || req.getRequestURI().contains("register.do")) {
            chain.doFilter(req, res);
            return;
        }

        logger.trace("doHttpFilter call");
        if (req.getSession() != null && req.getSession().getAttribute(USER) != null) {
            // авторизирован: сессия существует и содержит пользователя
            // просто пропускаем запрос дальше
            chain.doFilter(req, res);
            logger.trace("chain.doFilter(req, res);");
        } else {
            // неавторизирован:
            // 1. делаем "внешний redirect"  на login.jsp
            // 2. в URL передаем путь куда шли
            // 3. Путь закодирован по схеме {'?' -> '!', '=' -> '#', '&' -> '@'}
            // res.sendRedirect("login.jsp?redirectTo=вот-куда-мы-шли!параметрA#значениеA@параметрB#значениеB");
            String codedOriginPage = req.getRequestURI() + encode((req.getQueryString() == null) ? "" : "?" + req.getQueryString());
            String redirectTo = "login.jsp?redirectTo=" + codedOriginPage;
            res.sendRedirect(redirectTo);
            logger.trace("res.sendRedirect(" + redirectTo + ");");
        }
    }
}