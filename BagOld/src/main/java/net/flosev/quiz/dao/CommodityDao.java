package net.flosev.quiz.dao;

import net.flosev.quiz.dao.exception.DaoSystemException;
import net.flosev.quiz.dao.exception.NoSuchEntityException;
import net.flosev.quiz.entity.Commodity;

import java.util.List;

public interface CommodityDao {

    public Commodity selectById(int id) throws DaoSystemException, NoSuchEntityException;

    public List <Commodity> selectByPrice(int price)throws DaoSystemException, NoSuchEntityException;

    public List<Commodity> selectByCaption(String caption) throws DaoSystemException, NoSuchEntityException;

    public List<Commodity> selectByFashion(int id) throws DaoSystemException, NoSuchEntityException;

    public List<Commodity> selectByLength(int length) throws DaoSystemException;

    public List<Commodity> selectAll() throws DaoSystemException;
}
