package net.golovach.quiz.controller.theme;

import net.golovach.inject.DependencyInjectionServlet;
import net.golovach.inject.Inject;
import net.golovach.quiz.dao.QuizDao;
import net.golovach.quiz.dao.ThemeDao;
import net.golovach.quiz.dao.exception.DaoSystemException;
import net.golovach.quiz.entity.Quiz;
import net.golovach.quiz.entity.Theme;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static net.golovach.util.ClassName.getCurrentClassName;
import static org.apache.log4j.Logger.getLogger;

public class ThemeAllController extends DependencyInjectionServlet {
    public static final String ATTRIBUTE_MODEL_TO_VIEW = "themeList";
    public static final String PAGE_OK = "themeAll.jsp";
    public static final String PAGE_ERROR = "index.jsp";

    private static final Logger logger = getLogger(getCurrentClassName());

    @Inject("themeDao")
    private ThemeDao themeDao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            List<Theme> model = themeDao.selectAll();
            request.setAttribute(ATTRIBUTE_MODEL_TO_VIEW, model);
            logger.trace("set attribute '" + ATTRIBUTE_MODEL_TO_VIEW + "' to " + model);
            // OK
            request.getRequestDispatcher(PAGE_OK).forward(request, response);
            logger.debug("PAGE_OK: RequestDispatcher.forward(...) to " + PAGE_OK);
        } catch (DaoSystemException e) {
            // FAIL
            response.sendRedirect(PAGE_ERROR);
            logger.debug("PAGE_ERROR: response.sendRedirect(...) to " + PAGE_ERROR, e);
        }
    }
}
