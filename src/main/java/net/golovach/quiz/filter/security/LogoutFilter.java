package net.golovach.quiz.filter.security;

import net.golovach.quiz.entity.User;
import net.golovach.quiz.filter.BaseFilter;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static net.golovach.quiz.controller.SessionAttributes.USER;
import static net.golovach.quiz.filter.security.LoginFilter.PARAMETER_REDIRECT_TO;
import static net.golovach.util.ClassName.getCurrentClassName;
import static org.apache.log4j.Logger.getLogger;

public class LogoutFilter extends BaseFilter implements Filter {
    public static final String BASE_PAGE = "index.jsp";

    private static final Logger logger = getLogger(getCurrentClassName());

    @Override
    public void doHttpFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute(USER);
            session.removeAttribute(USER);
            if (user != null) {
                logger.info("LOGOUT - OK. user = " + user);
            } else {
                logger.debug("Somebody try logout but not logged in. " + req.getRemoteAddr());
            }
        }
        String locationRedirectTo = req.getParameter(PARAMETER_REDIRECT_TO);
        if (locationRedirectTo != null) {
            // todo: special mechanics
            locationRedirectTo = locationRedirectTo.replace(";", "?id=");
            logger.debug("sendRedirect to" + locationRedirectTo);
            res.sendRedirect(locationRedirectTo);
        } else {
            logger.warn("sendRedirect to base page = '" + BASE_PAGE + "'. Not correct request. Request doesn't have parameter '" + PARAMETER_REDIRECT_TO + "'");
            res.sendRedirect(BASE_PAGE);
        }
    }

    @Override
    public void destroy() {
        // NOP
    }
}
