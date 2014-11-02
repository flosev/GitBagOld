package net.golovach.quiz.dao;

import net.golovach.quiz.dao.exception.DaoSystemException;
import net.golovach.quiz.dao.exception.NoSuchEntityException;
import net.golovach.quiz.entity.Question;
import net.golovach.quiz.entity.Quiz;
import net.golovach.quiz.entity.Theme;

import java.util.List;

public interface QuizDao {

    public Quiz selectById(int id) throws DaoSystemException, NoSuchEntityException;

    public List<Quiz> selectByTheme(Theme theme) throws DaoSystemException;
    
    public List<Quiz> selectAll() throws DaoSystemException;
}
