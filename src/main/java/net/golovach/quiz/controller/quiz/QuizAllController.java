package net.golovach.quiz.controller.quiz;

import net.golovach.inject.DependencyInjectionServlet;
import net.golovach.inject.Inject;
import net.golovach.quiz.dao.QuizDao;
import net.golovach.quiz.dao.exception.DaoSystemException;
import net.golovach.quiz.entity.Quiz;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import static net.golovach.util.ClassName.getCurrentClassName;
import static org.apache.log4j.Logger.getLogger;


public class QuizAllController extends DependencyInjectionServlet {
    
    public static final String ATTRIBUTE_MODEL_TO_VIEW = "quizList";
    public static final String PAGE_OK = "WEB-INF/quizAll.jsp";
    public static final String PAGE_ERROR = "index.jsp";

    private final Logger logger = getLogger(getCurrentClassName());

    @Inject("quizDao")
    private QuizDao quizDao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            List<Quiz> model = quizDao.selectAll();
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
