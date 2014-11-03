package net.flosev.quiz.dao;

import net.flosev.quiz.dao.exception.DaoSystemException;
import net.flosev.quiz.dao.exception.NoSuchEntityException;
import net.flosev.quiz.entity.User;

import java.util.List;

public interface UserDao {

    public User selectByLogin(String login) throws DaoSystemException, NoSuchEntityException;

    public User selectByEmail(String email) throws DaoSystemException, NoSuchEntityException;

    public User insertNew(User user) throws DaoSystemException;

    public List<User> selectAll() throws DaoSystemException;
}
