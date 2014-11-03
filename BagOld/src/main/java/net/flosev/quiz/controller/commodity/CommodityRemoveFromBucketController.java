package net.flosev.quiz.controller.commodity;


import net.flosev.inject.DependencyInjectionServlet;
import net.flosev.quiz.entity.Commodity;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.util.Collections.unmodifiableList;
import static net.flosev.quiz.controller.SessionAttributes.QUIZES_IN_BUCKET;
import static net.flosev.util.ClassName.getCurrentClassName;
import static org.apache.log4j.Logger.getLogger;

public class CommodityRemoveFromBucketController extends DependencyInjectionServlet {
    public static final String PARAM_ID = "id";
    public static final String PARAM_REDIRECT_TO_ID = "redirectToId";
    public static final String PAGE_ERROR = "quizAll.do"; //todo: or index?

    private static final Logger logger = getLogger(getCurrentClassName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter(PARAM_ID);
        String idRedirectToStr = request.getParameter(PARAM_REDIRECT_TO_ID);
        if (idStr != null && idRedirectToStr != null) {
            Integer removeQuizId = Integer.valueOf(idStr);
            Integer redirectToQuizId = Integer.valueOf(idRedirectToStr);

            HttpSession session = request.getSession(false);
            if (session != null) {
                List<Commodity> oldBucket = (List<Commodity>) session.getAttribute(QUIZES_IN_BUCKET);
                if (oldBucket != null) {
                    //todo: not-mutable bucket
                    List<Commodity> newBucket = new ArrayList<>(oldBucket);
                    for (Iterator<Commodity> iter = newBucket.iterator(); ; iter.hasNext()) {
                        if (iter.next().getId() == removeQuizId) {
                            iter.remove();
                            session.setAttribute(QUIZES_IN_BUCKET, unmodifiableList(newBucket));
                            break;
                        }
                    }
                    logger.debug("add NEXT quiz to session. Attribute = " + QUIZES_IN_BUCKET + ", quizId = " + removeQuizId);
                }
            }
            // OK
            String newLocation = "quiz.do?id=" + redirectToQuizId;
            response.sendRedirect(newLocation);
            logger.debug("PAGE_OK: response.sendRedirect(...) to " + newLocation);
            return;
        }
        // FAIL
        response.sendRedirect(PAGE_ERROR);
        logger.debug("PAGE_ERROR: response.sendRedirect(...) to " + PAGE_ERROR);
    }
}
