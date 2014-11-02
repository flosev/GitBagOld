package net.golovach.quiz.controller.question;

import net.golovach.inject.DependencyInjectionServlet;
import net.golovach.inject.Inject;
import net.golovach.quiz.dao.QuestionDao;
import net.golovach.quiz.dao.exception.DaoSystemException;
import net.golovach.quiz.dao.exception.NoSuchEntityException;
import net.golovach.quiz.entity.Question;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static net.golovach.util.ClassName.getCurrentClassName;
import static org.apache.log4j.Logger.getLogger;

public class QuestionController extends DependencyInjectionServlet {
    public static final String PARAM_ID = "id";
    public static final String ATTRIBUTE_MODEL_TO_VIEW = "question";
    public static final String PAGE_OK = "question.jsp";
    public static final String PAGE_ERROR = "questionAll.do";

    private static final Logger logger = getLogger(getCurrentClassName());

    @Inject("questionDao")
    private QuestionDao questionDao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter(PARAM_ID);
        if (idStr != null) {
            try {
                Integer id = Integer.valueOf(idStr);
                Question model = questionDao.selectById(id);
                request.setAttribute(ATTRIBUTE_MODEL_TO_VIEW, model);
                logger.trace("set attribute '" + ATTRIBUTE_MODEL_TO_VIEW + "' to " + model);
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


