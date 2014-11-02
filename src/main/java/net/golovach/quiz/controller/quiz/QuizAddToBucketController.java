package net.golovach.quiz.controller.quiz;

import net.golovach.inject.DependencyInjectionServlet;
import net.golovach.inject.Inject;
import net.golovach.quiz.dao.QuizDao;
import net.golovach.quiz.dao.exception.DaoSystemException;
import net.golovach.quiz.dao.exception.NoSuchEntityException;
import net.golovach.quiz.entity.Quiz;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Collections.copy;
import static java.util.Collections.singletonList;
import static java.util.Collections.unmodifiableList;
import static net.golovach.quiz.controller.SessionAttributes.QUIZES_IN_BUCKET;
import static net.golovach.util.ClassName.getCurrentClassName;
import static org.apache.log4j.Logger.getLogger;

public class QuizAddToBucketController extends DependencyInjectionServlet {
    public static final String PARAM_ID = "id";
    public static final String PAGE_ERROR = "quizAll.do";

    private static final Logger logger = getLogger(getCurrentClassName());

    @Inject("quizDao")
    private QuizDao quizDao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter(PARAM_ID);
        if (idStr != null) {
            try {
                Integer id = Integer.valueOf(idStr);
                Quiz quiz = quizDao.selectById(id);

                HttpSession session = request.getSession(true);
                List<Quiz> oldBucket = (List<Quiz>) session.getAttribute(QUIZES_IN_BUCKET);
                if (oldBucket == null) {
                    logger.debug("add FIRST quiz to session. Attribute = " + QUIZES_IN_BUCKET + ", quizId = " + id);
                    session.setAttribute(QUIZES_IN_BUCKET, singletonList(quiz));
                } else {
                    if (!oldBucket.contains(quiz)) {
                        List<Quiz> newBucket = new ArrayList<>(oldBucket);
                        newBucket.add(quiz);
                        logger.debug("add NEXT quiz to session. Attribute = " + QUIZES_IN_BUCKET + ", quizId = " + id);
                        session.setAttribute(QUIZES_IN_BUCKET, unmodifiableList(newBucket));
                    } else {
                        logger.debug("TRY to add quiz to session but has one (so don't add duplicates). Attribute = " + QUIZES_IN_BUCKET + ", quizId = " + id);
                    }
                }
                // OK
                String newLocation = "quiz.do?id=" + id;
                response.sendRedirect(newLocation);
                logger.debug("PAGE_OK: response.sendRedirect(...) to " + newLocation);
                return;
            } catch (NoSuchEntityException e) {
                logger.debug(e);
                e.printStackTrace();
            } catch (DaoSystemException e) {
                logger.warn(e);
                e.printStackTrace();
            }
        }
        // FAIL
        response.sendRedirect(PAGE_ERROR);
        logger.debug("PAGE_ERROR: response.sendRedirect(...) to " + PAGE_ERROR);
    }
}



