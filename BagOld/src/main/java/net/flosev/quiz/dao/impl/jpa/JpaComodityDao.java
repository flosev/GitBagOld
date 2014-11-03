package net.flosev.quiz.dao.impl.jpa;

import net.flosev.quiz.dao.CommodityDao;
import net.flosev.quiz.dao.exception.DaoSystemException;
import net.flosev.quiz.dao.exception.NoSuchEntityException;
import net.flosev.quiz.entity.Commodity;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public class JpaComodityDao implements CommodityDao{
    @PersistenceContext
    private EntityManager entityManager;


    public void setEntityManager(EntityManagerFactory factory) {
        this.entityManager = factory.createEntityManager();
    }
    @Override
    public Commodity selectById(int id) throws DaoSystemException, NoSuchEntityException {
        Query query = this.entityManager.createQuery("SELECT c FROM Commodity c WHERE c.id=:id");
        query.setParameter("id", id);
        return ((Commodity)query.getSingleResult());
    }

    @Override
    public List<Commodity> selectByPrice(int price) throws DaoSystemException, NoSuchEntityException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Commodity> selectByCaption(String caption) throws DaoSystemException, NoSuchEntityException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Commodity> selectByFashion(int id) throws DaoSystemException, NoSuchEntityException {
        List list = entityManager.createQuery(
                "SELECT c FROM Commodity c WHERE c.fashion.id=:id")
                .setParameter("id", id)
                .setMaxResults(20)
                .getResultList();
        return list;
    }

    @Override
    public List<Commodity> selectByLength(int length) throws DaoSystemException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Commodity> selectAll() throws DaoSystemException {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Commodity> criteriaQuery = criteriaBuilder.createQuery(Commodity.class);
            Root<Commodity> root = criteriaQuery.from(Commodity.class);
            criteriaQuery.select(root);
            TypedQuery<Commodity> typedQuery = entityManager.createQuery(criteriaQuery);
            List<Commodity> allCommodity = typedQuery.getResultList();
            return allCommodity;
        }

}
