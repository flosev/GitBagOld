package net.golovach.quiz.controller.theme;

import net.golovach.inject.DependencyInjectionServlet;
import net.golovach.inject.Inject;
import net.golovach.quiz.controller.Controller;
import net.golovach.quiz.dao.QuizDao;
import net.golovach.quiz.dao.ThemeDao;
import net.golovach.quiz.dao.exception.DaoSystemException;
import net.golovach.quiz.dao.exception.NoSuchEntityException;
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

@Controller
public class ThemeController extends DependencyInjectionServlet {
    public static final String PARAM_ID = "id";
    public static final String ATTRIBUTE_THEME = "theme";
    public static final String ATTRIBUTE_QUIZ_LIST = "quizList";
    public static final String PAGE_OK = "theme.jsp";
    public static final String PAGE_ERROR = "themeAll.do";

    private static final Logger logger = getLogger(getCurrentClassName());

    @Inject("themeDao")
    private ThemeDao themeDao;
    @Inject("quizDao")
    private QuizDao quizDao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter(PARAM_ID);
        if (id != null) {
            try {
                Theme theme = themeDao.selectById(id);
                List<Quiz> quizList = quizDao.selectByTheme(theme);
                request.setAttribute(ATTRIBUTE_THEME, theme);
                request.setAttribute(ATTRIBUTE_QUIZ_LIST, quizList);
                logger.trace("set attribute '" + ATTRIBUTE_THEME + "' to " + theme);
                // OK
                request.getRequestDispatcher(PAGE_OK).forward(request, response);
                logger.debug("PAGE_OK: RequestDispatcher.forward(...) to " + PAGE_OK);
                return;
            } catch (NoSuchEntityException e) {
                logger.debug(e);
            } catch (DaoSystemException e) {
                logger.warn(e);
            }
        }
        // FAIL
        response.sendRedirect(PAGE_ERROR);
        logger.debug("PAGE_ERROR: response.sendRedirect(...) to " + PAGE_ERROR);
    }
}


