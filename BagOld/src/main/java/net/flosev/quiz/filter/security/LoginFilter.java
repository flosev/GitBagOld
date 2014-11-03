package net.flosev.quiz.filter.security;

import net.flosev.inject.DependencyInjectionFilter;
import net.flosev.inject.Inject;
import net.flosev.quiz.dao.UserDao;
import net.flosev.quiz.dao.exception.DaoException;
import net.flosev.quiz.dao.tx.TransactionManager;
import net.flosev.quiz.dao.tx.UnitOfWork;
import net.flosev.quiz.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static net.flosev.quiz.controller.SessionAttributes.USER;
import static net.flosev.quiz.filter.security.UrlCodec.decode;
import static net.flosev.util.ClassName.getCurrentClassName;
import static org.apache.log4j.Logger.getLogger;

public class LoginFilter extends DependencyInjectionFilter implements Filter {
    public static final String PARAMETER_REDIRECT_TO = "redirectTo";
    public static final String PARAMETER_LOGIN = "login";
    public static final String PARAMETER_PASSWORD = "password";

    public static final String PAGE_DENY = "index.jsp";
    public static final String PAGE_DEFAULT = "index.jsp";

    private static final Logger logger = getLogger(getCurrentClassName());

    @Inject("txManager")
    private TransactionManager txManager;
    @Inject("userDao")
    private UserDao userDao;
    
    @Override
    public void doHttpFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        // читаем из запроса login/password
        /*User fed = new User(2,"dfg","fgh","flosev@mail.ru");*/
        final String login = req.getParameter(PARAMETER_LOGIN);
        String password = req.getParameter(PARAMETER_PASSWORD);
        // проверяем, есть ли такой зарегистрированный пользователь
        if (login != null && password != null) {
            User user = null;
            try {
                user = txManager.call(new UnitOfWork<User, DaoException>() {
                    @Override
                    public User call() throws DaoException {
                        return userDao.selectByLogin(login);
                    }
                });
            } catch (DaoException | SQLException e) {
                throw new RuntimeException(e); //todo:
            }
            if (user.getPassword().equals(password)) { //todo: use mdc
                // берем сессию из Web Container, если ее нет, то принудительно создаем
                HttpSession session = req.getSession(true);
                // сохраняем пользователя в сессии
                session.setAttribute(USER, user);
                // смотрим, если в запросе параметр - куда возвращать пользователя
                String redirectToEncoded = req.getParameter(LoginFilter.PARAMETER_REDIRECT_TO);
                if (redirectToEncoded != null) {
                    String redirectToOriginal = decode(redirectToEncoded); // todo: special mechanics
                    res.sendRedirect(redirectToOriginal);
                } else {
                    res.sendRedirect(PAGE_DEFAULT);
                }
                return;
            }
        }

        res.sendRedirect(PAGE_DENY);
    }
}