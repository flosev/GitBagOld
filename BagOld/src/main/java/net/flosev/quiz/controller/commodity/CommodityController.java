package net.flosev.quiz.controller.commodity;

import net.flosev.inject.DependencyInjectionServlet;
import net.flosev.inject.Inject;
import net.flosev.quiz.controller.Controller;
import net.flosev.quiz.dao.CommodityDao;
import net.flosev.quiz.dao.exception.DaoSystemException;
import net.flosev.quiz.dao.exception.NoSuchEntityException;
import net.flosev.quiz.entity.Commodity;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static net.flosev.util.ClassName.getCurrentClassName;
import static org.apache.log4j.Logger.getLogger;

@Controller
public class CommodityController extends DependencyInjectionServlet {
    public static final String PARAM_ID = "id";
    public static final String ATTRIBUTE_QUIZ_LIST = "commodity";
    public static final String PAGE_OK = "commodity.jsp";
    public static final String PAGE_ERROR = "commodityAll.do";

    private static final Logger logger = getLogger(getCurrentClassName());

    @Inject("commodityDao")
    private CommodityDao commodityDao;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter(PARAM_ID);
        if (idStr != null) {
            try {
                Integer id = Integer.valueOf(idStr);
                Commodity commodity = commodityDao.selectById(id);
                request.setAttribute(ATTRIBUTE_QUIZ_LIST, commodity);
                logger.trace("set attribute '" + ATTRIBUTE_QUIZ_LIST + "' to " + commodity);
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

