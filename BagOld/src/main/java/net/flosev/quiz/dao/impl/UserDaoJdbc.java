package net.flosev.quiz.dao.impl;

import net.flosev.quiz.dao.UserDao;
import net.flosev.quiz.dao.exception.DaoSystemException;
import net.flosev.quiz.dao.exception.NoSuchEntityException;
import net.flosev.quiz.entity.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoJdbc implements UserDao {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Map<Integer, User> memory = new HashMap<>();

    public UserDaoJdbc() {
        this.memory.put(1, new User(2, "frank", "123", "fd@gf"));
        this.memory.put(2, new User(3, "eric", "456", "fd@hj"));
    }
    @Override
    public User selectByLogin(String byLogin) throws DaoSystemException, NoSuchEntityException {
        // todo: correct?
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, login, password, email FROM Users WHERE login='" + byLogin + "'");
            if (!rs.next()) {
                throw new NoSuchEntityException("No User for login='" + byLogin + "'");
            } else {
                int id = rs.getInt("id");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String email = rs.getString("email");
                if (rs.next()) {
                    throw new DaoSystemException("DB contains more than 1 user in 'Users' for login='" + byLogin + "'.");
                }
                return new User(id, login, password, email);
            }
        } catch (SQLException e) {
            throw new DaoSystemException("Some JDBC error.", e);
        }
    }

    @Override
    public User selectByEmail(String byEmail) throws DaoSystemException, NoSuchEntityException {
        // todo: correct?
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, login, password, email FROM Users WHERE email='" + byEmail + "'");
            if (!rs.next()) {
                throw new NoSuchEntityException("No User for email='" + byEmail + "'");
            } else {
                int id = rs.getInt("id");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String email = rs.getString("email");
                if (rs.next()) {
                    throw new DaoSystemException("DB contains more than 1 user in 'Users' for email='" + byEmail + "'.");
                }
                return new User(id, login, password, email);
            }
        } catch (SQLException e) {
            throw new DaoSystemException("Some JDBC error.", e);
        }
    }

    @Override
    public User insertNew(User user) throws DaoSystemException {
        // todo: correct?
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO Users (login, password, email) VALUES ('" + user.getLogin() + "', '" + user.getPassword() + "', '" + user.getEmail() + "')");
            return selectByLogin(user.getLogin()); //todo
        } catch (SQLException | NoSuchEntityException e) {
            throw new DaoSystemException("Some JDBC error.", e);
        }
    }
    @Override
    public List<User> selectAll() throws DaoSystemException {
        return new ArrayList<>(memory.values());
    }
}
