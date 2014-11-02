package net.golovach.quiz.dao;

import net.golovach.quiz.dao.exception.DaoSystemException;
import net.golovach.quiz.dao.exception.NoSuchEntityException;
import net.golovach.quiz.entity.Question;
import net.golovach.quiz.entity.Theme;

import java.util.List;

public interface ThemeDao {

    public Theme selectById(String id) throws DaoSystemException, NoSuchEntityException;

    public List<Theme> selectAll() throws DaoSystemException;
}
