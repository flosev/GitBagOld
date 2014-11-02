package net.golovach.quiz.dao;

import net.golovach.quiz.dao.exception.DaoSystemException;
import net.golovach.quiz.dao.exception.NoSuchEntityException;
import net.golovach.quiz.entity.Question;

import java.util.List;

public interface QuestionDao {

    public Question selectById(int id) throws DaoSystemException, NoSuchEntityException;

    public List<Question> selectAll() throws DaoSystemException;
}
