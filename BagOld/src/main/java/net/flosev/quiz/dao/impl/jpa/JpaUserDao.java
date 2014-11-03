package net.flosev.quiz.dao.impl.jpa;

import net.flosev.quiz.dao.UserDao;
import net.flosev.quiz.dao.exception.DaoSystemException;
import net.flosev.quiz.dao.exception.NoSuchEntityException;
import net.flosev.quiz.entity.JpaUser;
import net.flosev.quiz.entity.User;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public  class JpaUserDao implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManagerFactory factory) {
        this.entityManager = factory.createEntityManager();
    }

    @Override
    public User selectByLogin(String login) throws DaoSystemException, NoSuchEntityException {
        Query query = this.entityManager.createQuery("SELECT u FROM JpaUser u WHERE u.login=:login");
        query.setParameter("login", login);
        return ((JpaUser) query.getSingleResult()).toUser();
    }

    @Override
    public User selectByEmail(String email) throws DaoSystemException, NoSuchEntityException {
        Query query = this.entityManager.createQuery("SELECT u FROM JpaUser u WHERE u.email=:email");
        query.setParameter("email", email);
        return ((JpaUser) query.getSingleResult()).toUser();
    }

    @Override
    public User insertNew(User user) throws DaoSystemException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> selectAll() throws DaoSystemException {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        TypedQuery<User> typedQuery = entityManager.createQuery(criteriaQuery);
        List<User> allUsers = typedQuery.getResultList();
        return allUsers;
    }
}
