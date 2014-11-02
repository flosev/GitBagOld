package net.golovach.quiz.filter.security;

import net.golovach.quiz.entity.User;
import net.golovach.quiz.filter.BaseFilter;
import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static net.golovach.quiz.controller.SessionAttributes.USER;
import static net.golovach.quiz.filter.security.UrlCodec.decode;
import static net.golovach.util.ClassName.getCurrentClassName;
import static org.apache.log4j.Logger.getLogger;

public class LoginFilter extends BaseFilter implements Filter {
    public static final String PARAMETER_REDIRECT_TO = "redirectTo";
    public static final String PARAMETER_LOGIN = "login";
    public static final String PARAMETER_PASSWORD = "password";

    public static final String PAGE_DENY = "index.jsp";
    public static final String PAGE_DEFAULT = "index.jsp";

    private static final Logger logger = getLogger(getCurrentClassName());

    private static final Map<String, User> mem = new ConcurrentHashMap<>();

    static {
        mem.put("Mike", new User("Mike", "123"));
        mem.put("Sara", new User("Sara", "123"));
    }

    @Override
    public void doHttpFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        // читаем из запроса login/password
        String login = req.getParameter(PARAMETER_LOGIN);
        String password = req.getParameter(PARAMETER_PASSWORD);
        User user = new User(login, password);
        // проверяем, есть ли такой зарегистрированный пользователь
        if (login != null && password != null && user.equals(mem.get(login))) {
            logger.info("LOGIN - OK, user = " + user); //todo: use mdc
            // берем сессию из Web Container, если ее нет, то принудительно создаем
            HttpSession session = req.getSession(true);
            // сохраняем пользователя в сессии
            session.setAttribute(USER, user);
            // смотрим, если в запросе параметр - куда возвращать пользователя
            String redirectToEncoded = req.getParameter(LoginFilter.PARAMETER_REDIRECT_TO);
            logger.trace("locationRedirectTo = '" + redirectToEncoded + "'");
            if (redirectToEncoded != null) {
                // todo: special mechanics
                String redirectToOriginal = decode(redirectToEncoded);

                logger.trace("have info in request about redirection after login, sendRedirect to" + redirectToOriginal);
                res.sendRedirect(redirectToOriginal);
            } else {
                logger.trace("have NOT info in request parameter(" + PARAMETER_REDIRECT_TO + ") about redirection after login, sendRedirect to default page = " + PAGE_DEFAULT);
                res.sendRedirect(PAGE_DEFAULT);
            }
        } else {
            logger.trace("Login - FAIL, redirect to deny page = '" + PAGE_DENY);
            res.sendRedirect(PAGE_DENY);
        }
    }
}