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
public class ThemeEditController extends DependencyInjectionServlet {
    public static final String PARAM_ID = "id";
    public static final String PARAM_CAPTION = "caption";

    public static final String ATTRIBUTE_THEME = "theme";
    public static final String ATTRIBUTE_QUIZ_LIST = "quizList";
    public static final String PAGE_OK = "themeEdit.jsp";
    public static final String PAGE_ERROR = "themeAll.do";

    private static final Logger logger = getLogger(getCurrentClassName());

    @Inject("themeDao")
    private ThemeDao themeDao;
    @Inject("quizDao")
    private QuizDao quizDao;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter(PARAM_ID);
        String caption = req.getParameter(PARAM_CAPTION);
        if (id != null) {
            try {
                Theme theme = themeDao.selectById(id);
                List<Quiz> quizList = quizDao.selectByTheme(theme);
                req.setAttribute(ATTRIBUTE_THEME, theme);
                req.setAttribute(ATTRIBUTE_QUIZ_LIST, quizList);
                logger.trace("set attribute '" + ATTRIBUTE_THEME + "' to " + theme);
                // OK
                req.getRequestDispatcher(PAGE_OK).forward(req, resp);
                logger.debug("PAGE_OK: RequestDispatcher.forward(...) to " + PAGE_OK);
                return;
            } catch (NoSuchEntityException e) {
                logger.debug(e);
            } catch (DaoSystemException e) {
                logger.warn(e);
            }
        }
        // FAIL
        resp.sendRedirect(PAGE_ERROR);
        logger.debug("PAGE_ERROR: resp.sendRedirect(...) to " + PAGE_ERROR);
    }
}


