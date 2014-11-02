package net.golovach.example.servlet;

import net.golovach.inject.Inject;
import net.golovach.quiz.dao.QuizDao;
import net.golovach.quiz.dao.exception.DaoSystemException;
import net.golovach.quiz.dao.exception.NoSuchEntityException;
import net.golovach.quiz.entity.Quiz;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuizController extends HttpServlet {
    // We need some Dependency Injection magic here
    @Inject("quizDao")
    private QuizDao quizDao;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // mysite.com/quiz.do?id=123&moreinfo=KLM
        String idStr = req.getParameter("id");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                Quiz quiz = quizDao.selectById(id);
                req.setAttribute("quiz", quiz);
                // OK, go to VIEW
                req.getRequestDispatcher("quiz.jsp").forward(req, res);
                return;
            } catch (NumberFormatException | NoSuchEntityException | DaoSystemException e) {/*NOP*/}{
                /*NOP*/
            }
        }
        // BAD, go to ERROR PAGE
        res.sendRedirect("index.jsp");
    }
}
