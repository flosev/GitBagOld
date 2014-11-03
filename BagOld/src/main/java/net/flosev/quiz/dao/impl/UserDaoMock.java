package net.flosev.quiz.dao.impl;

import net.flosev.quiz.dao.UserDao;
import net.flosev.quiz.dao.exception.DaoSystemException;
import net.flosev.quiz.dao.exception.NoSuchEntityException;
import net.flosev.quiz.entity.User;

import javax.sql.DataSource;
import java.util.*;




public class UserDaoMock implements UserDao{
    private Map<Integer, User> memory = new HashMap<>();
    private DataSource dataSource;

    public UserDaoMock() {
        this.memory.put(1, new User(2, "frank", "123", "fd@gf"));
        this.memory.put(2, new User(3, "eric", "456", "fd@hj"));
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User selectByLogin(String byLogin) throws DaoSystemException, NoSuchEntityException {
        // todo: correct?

         try {       return new User(2, "frank", "123", "fd@gf");
         } catch (Exception e) {
             throw new NoSuchEntityException("Some JDBC error.", e);
         }
    }

    @Override
    public User selectByEmail(String byEmail) throws DaoSystemException, NoSuchEntityException {
        // todo: correct?
        try {  return new User(2, "frank", "123", "fd@gf");


        } catch (Exception e) {
            throw new NoSuchEntityException("Some JDBC error.", e);
        }


    }



    @Override
    public User insertNew(User user) throws DaoSystemException {
        // todo: correct?
        try {
        return selectByLogin(user.getLogin()); //todo
        } catch ( NoSuchEntityException e) {
            throw new DaoSystemException("Some JDBC error.", e);
        }

    }

    @Override
    public List<User> selectAll() throws DaoSystemException {
        return new ArrayList<>(memory.values());
    }
}
