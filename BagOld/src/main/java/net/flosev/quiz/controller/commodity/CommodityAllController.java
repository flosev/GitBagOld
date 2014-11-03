package net.flosev.quiz.controller.commodity;

/**
 * Created with IntelliJ IDEA.
 * User: виталик
 * Date: 13.07.14
 * Time: 16:05
 * To change this template use File | Settings | File Templates.
 */

import net.flosev.inject.DependencyInjectionServlet;
import net.flosev.inject.Inject;
import net.flosev.quiz.dao.CommodityDao;
import net.flosev.quiz.dao.FashionDao;
import net.flosev.quiz.dao.SizeDao;
import net.flosev.quiz.dao.UserDao;
import net.flosev.quiz.dao.exception.DaoSystemException;
import net.flosev.quiz.dao.exception.NoSuchEntityException;
import net.flosev.quiz.entity.Commodity;
import net.flosev.quiz.entity.Fashion;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static net.flosev.util.ClassName.getCurrentClassName;
import static org.apache.log4j.Logger.getLogger;


public class CommodityAllController extends DependencyInjectionServlet {

    public static final String ATTRIBUTE_MODEL_TO_VIEW = "quizList";
    public static final String ATTRIBUTE_MODEL_TO_TYPE = "fashion";
    public static final String ATTRIBUTE_MODEL_TO_MY = "size";
    public static final String ATTRIBUTE_MODEL_TO_Quest = "question";
    public static final String ATTRIBUTE_MODEL_TO_Commodity = "commodity";
    public static final String PAGE_OK = "WEB-INF/commodityAll.jsp";
    public static final String PAGE_ERROR = "index.jsp";

    private final Logger logger = getLogger(getCurrentClassName());

    @Inject("userDao")
    private UserDao userDao;
    @Inject("commodityDao")
    private CommodityDao commodityDao;
    @Inject("sizeDao")
    private SizeDao sizeDao;
    @Inject("fashionDao")
    private FashionDao fashionDao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            List<Commodity> model5 = commodityDao.selectAll();
            List<Fashion> model1=fashionDao.selectAll();

            /*касается commodity проекта*/
            /*List<Commodity> sizeList =   commodityDao.selectBySize(model5) ;*/
            /*Integer id = Integer.valueOf(1);*/

            /*List<Commodity> model5 = commodityDao.selectByPrice(45);*/
            /*List<Commodity> model5 = commodityDao.selectByLength(246);*/
            /*Size model6 = sizeDao.selectByLength(1);*/
            Commodity model8 = commodityDao.selectById(1);
            /*Question model7 = questionDao.selectById(1);*/

            request.setAttribute(ATTRIBUTE_MODEL_TO_Commodity, model8);
            request.setAttribute(ATTRIBUTE_MODEL_TO_TYPE, model1);

           /* request.setAttribute(ATTRIBUTE_MODEL_TO_Quest, model7);*/
            request.setAttribute(ATTRIBUTE_MODEL_TO_VIEW, model5);
            /*request.setAttribute(ATTRIBUTE_MODEL_TO_MY, model6);*/

            // OK
            request.getRequestDispatcher(PAGE_OK).forward(request, response);
            logger.debug("PAGE_OK: RequestDispatcher.forward(...) to " + PAGE_OK);
        } catch (DaoSystemException e) {
            // FAIL
            response.sendRedirect(PAGE_ERROR);
            logger.debug("PAGE_ERROR: response.sendRedirect(...) to " + PAGE_ERROR, e);

        } catch (NoSuchEntityException e) {
            response.sendRedirect(PAGE_ERROR);
            logger.debug("PAGE_ERROR: response.sendRedirect(...) to " + PAGE_ERROR, e);
            //To change body of catch statement use File | Settings | File Templates.
        }

    }
}
