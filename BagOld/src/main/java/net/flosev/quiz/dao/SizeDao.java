package net.flosev.quiz.dao;

/**
 * Created with IntelliJ IDEA.
 * User: виталик
 * Date: 21.06.14
 * Time: 17:28
 * To change this template use File | Settings | File Templates.
 */

import net.flosev.quiz.dao.exception.DaoSystemException;
import net.flosev.quiz.dao.exception.NoSuchEntityException;
import net.flosev.quiz.entity.Size;

import java.util.List;

public interface SizeDao {

    public Size selectByLength(int length) throws DaoSystemException, NoSuchEntityException;

    public Size selectByWidth(int width) throws DaoSystemException, NoSuchEntityException;

    public List<Size> selectAll() throws DaoSystemException;
}
