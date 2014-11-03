package net.flosev.quiz.controller.commodity;

import net.flosev.inject.DependencyInjectionServlet;
import net.flosev.inject.Inject;
import net.flosev.quiz.dao.*;
import net.flosev.quiz.dao.exception.DaoSystemException;
import net.flosev.quiz.dao.exception.NoSuchEntityException;
import net.flosev.quiz.entity.*;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static net.flosev.util.ClassName.getCurrentClassName;
import static org.apache.log4j.Logger.getLogger;


    public class CommodityFashionController extends DependencyInjectionServlet {


        public static final String ATTRIBUTE_MODEL_TO_TYPE = "fashion";
        public static final String ATTRIBUTE_MODEL_TO_TYPEALL = "fashion1";
        public static final String PARAM_CAPTION = "cp";
        public static final String PARAM_ID = "id";
        public static final String ATTRIBUTE_MODEL_TO_Commodity = "commodity";
        public static final String PAGE_OK = "commodityFashion.jsp";
        public static final String PAGE_ERROR = "index.jsp";

        private final Logger logger = getLogger(getCurrentClassName());


        @Inject("commodityDao")
        private CommodityDao commodityDao;
        @Inject("sizeDao")
        private SizeDao sizeDao;
        @Inject("fashionDao")
        private FashionDao fashionDao;

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            /*String cpStr = request.getParameter(PARAM_CAPTION);*/
            String idStr = request.getParameter(PARAM_ID);
            if (idStr != null) {
            try {
                /*String cp = String.valueOf(cpStr);
                List<Commodity> model1 = commodityDao.selectByCaption(cp) ;*/
                /*List<Commodity> model1 = commodityDao.selectAll() ;*/

                Integer id = Integer.valueOf(idStr);
                List<Commodity> model1 = commodityDao.selectByFashion(id) ;
                List<Fashion> model8=fashionDao.selectAll();

            /*касается commodity проекта*/
            /*List<Commodity> sizeList =   commodityDao.selectBySize(model5) ;*/
            /*Integer id = Integer.valueOf(1);*/
            /*List<Commodity> model5 = commodityDao.selectByPrice(45);*/
            /*List<Commodity> model5 = commodityDao.selectByLength(246);*/
            /*Size model6 = sizeDao.selectByLength(1);*/



                request.setAttribute(ATTRIBUTE_MODEL_TO_TYPEALL, model8);
                request.setAttribute(ATTRIBUTE_MODEL_TO_TYPE, model1);

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
