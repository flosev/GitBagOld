package net.flosev.quiz.controller.commodity;

import net.flosev.inject.DependencyInjectionServlet;
import net.flosev.inject.Inject;
import net.flosev.quiz.dao.CommodityDao;
import net.flosev.quiz.dao.exception.DaoException;
import net.flosev.quiz.dao.exception.NoSuchEntityException;
import net.flosev.quiz.dao.tx.TransactionManager;
import net.flosev.quiz.dao.tx.UnitOfWork;
import net.flosev.quiz.entity.Commodity;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.singletonList;
import static java.util.Collections.unmodifiableList;
import static net.flosev.quiz.controller.SessionAttributes.QUIZES_IN_BUCKET;
import static net.flosev.util.ClassName.getCurrentClassName;
import static org.apache.log4j.Logger.getLogger;

public class CommodityAddToBucketController extends DependencyInjectionServlet {
    public static final String PARAM_ID = "id";
    public static final String PAGE_ERROR = "quizAll.do";

    private static final Logger logger = getLogger(getCurrentClassName());

    @Inject("txManager")
    private TransactionManager txManager;
    @Inject("quizDao")
    private CommodityDao commodityDao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter(PARAM_ID);
        if (idStr != null) {
            try {
                final Integer id = Integer.valueOf(idStr);
                Commodity commodity = txManager.call(new UnitOfWork<Commodity, DaoException>() {
                    public Commodity call() throws DaoException {
                        return commodityDao.selectById(id);
                    }
                });

                HttpSession session = request.getSession(true);
                List<Commodity> oldBucket = (List<Commodity>) session.getAttribute(QUIZES_IN_BUCKET);
                //todo: Task: mutable collection in session
                if (oldBucket == null) {
                    logger.debug("add FIRST quiz to session. Attribute = " + QUIZES_IN_BUCKET + ", quizId = " + id);
                    session.setAttribute(QUIZES_IN_BUCKET, singletonList(commodity));
                } else {
                    if (!oldBucket.contains(commodity)) {
                        List<Commodity> newBucket = new ArrayList<>(oldBucket);
                        // todo: check uniques?
                        // todo: use AtomicReference for atomic change?
                        newBucket.add(commodity);
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
            } catch (DaoException | SQLException e) {
                logger.warn(e);
                e.printStackTrace();
            }
        }
        // FAIL
        response.sendRedirect(PAGE_ERROR);
        logger.debug("PAGE_ERROR: response.sendRedirect(...) to " + PAGE_ERROR);
    }
}
