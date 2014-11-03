package net.flosev.quiz.controller.user;

import net.flosev.inject.DependencyInjectionServlet;
import net.flosev.inject.Inject;
import net.flosev.quiz.dao.UserDao;
import net.flosev.quiz.dao.exception.NoSuchEntityException;
import net.flosev.quiz.dao.tx.TransactionManager;
import net.flosev.quiz.dao.tx.UnitOfWork;
import net.flosev.quiz.entity.User;
import net.flosev.quiz.validator.UserValidator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static net.flosev.util.ClassName.getCurrentClassName;
import static org.apache.log4j.Logger.getLogger;

public class RegisterNewUserController extends DependencyInjectionServlet {
    public static final String PARAM_LOGIN = "login";
    public static final String PARAM_PASSWORD = "password";
    public static final String PARAM_EMAIL = "email";

    //    public static final String ATTRIBUTE_MODEL_TO_VIEW = "quiz";
    public static final String PAGE_REGISTERED = "registered.jsp";
    public static final String PAGE_MORE_INFO = "register.jsp";

    private static final Logger logger = getLogger(getCurrentClassName());

    @Inject("txManager")
    private TransactionManager txManager;
    @Inject("userDao")
    private UserDao userDao;
    @Inject("userValidator")
    private UserValidator validator;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String login = request.getParameter(PARAM_LOGIN);
        String password = request.getParameter(PARAM_PASSWORD);
        final String email = request.getParameter(PARAM_EMAIL);

        if (login == null && password == null && email == null) {
            request.getRequestDispatcher(PAGE_MORE_INFO).forward(request, response);
            return;
        }

        final User tmp = new User(-1, login, password, email);
        final Map<String, String> errorMap = validator.validate(tmp);
        if (errorMap.isEmpty()) {
            User user;
            try {
                user = txManager.call(new UnitOfWork<User, Exception>() {
                    public User call() throws Exception {
                        try {
                            if (userDao.selectByLogin(login) != null) {
                                errorMap.put("login", "Such login exists!");
                            }
                        } catch (NoSuchEntityException e) {/*NOP*/}

                        try {
                            if (userDao.selectByEmail(email) != null) {
                                errorMap.put("email", "Such email exists!");
                            }
                        } catch (NoSuchEntityException e) {/*NOP*/}
                        //todo:
                        return userDao.insertNew(tmp);
                    }
                });

                if (errorMap.isEmpty()) {
                    // add to request - it is data for view: registered.jsp
                    request.setAttribute("user", user);
                    // add to session - it means status 'logged on'
                    request.getSession(true).setAttribute("user", user);
                    // go to view: registered.jsp
                    request.getRequestDispatcher(PAGE_REGISTERED).forward(request, response);
                    return;
                }
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }

        request.setAttribute("login", login);
        request.setAttribute("password", password);
        request.setAttribute("email", email);
        request.setAttribute("errorMap", errorMap);

        request.getRequestDispatcher(PAGE_MORE_INFO).forward(request, response);
    }
}


