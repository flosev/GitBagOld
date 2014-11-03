package net.flosev.quiz.dao;

import net.flosev.quiz.dao.exception.DaoSystemException;
import net.flosev.quiz.dao.exception.NoSuchEntityException;
import net.flosev.quiz.entity.Fashion;

import java.util.List;



    public interface FashionDao {

        public Fashion selectByCaption(String caption) throws DaoSystemException, NoSuchEntityException;

        public Fashion selectById(int id) throws DaoSystemException, NoSuchEntityException;

        public List<Fashion> selectAll() throws DaoSystemException;
    }


