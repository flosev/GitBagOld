package net.flosev.quiz.dao.impl.jpa;


import net.flosev.quiz.dao.FashionDao;
import net.flosev.quiz.dao.exception.DaoSystemException;
import net.flosev.quiz.dao.exception.NoSuchEntityException;
import net.flosev.quiz.entity.Fashion;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaFashionDao implements FashionDao {

    @PersistenceContext
    private EntityManager entityManager;


    public void setEntityManager(EntityManagerFactory factory) {
        this.entityManager = factory.createEntityManager();
    }


        @Override
        public Fashion selectById(int id) throws DaoSystemException, NoSuchEntityException {
            Query query = this.entityManager.createQuery("SELECT f FROM Fashion f WHERE f.id=:id");
            query.setParameter("id", id);
            return ((Fashion)query.getSingleResult());
           /* if (!memory.containsKey(id)) {
                throw new NoSuchEntityException("No Id for type == '" + id + "', only for " + memory.keySet());
            }
            return memory.get(id);*/
        }
        @Override
        public Fashion selectByCaption(String caption) throws DaoSystemException, NoSuchEntityException {
            Query query = this.entityManager.createQuery("SELECT f FROM Fashion f WHERE f.caption=:caption");
            query.setParameter("caption", caption);
            return ((Fashion)query.getSingleResult());
        }
            /*Fashion result = new Fashion(0, "0");
            for (Fashion typee : memory.values()) {
                if (typee.getCaption()==caption) {
                    result=typee;
                }
            }
            return result;*/

    /*public Fashion selectByCaption(String caption) throws DaoSystemException, NoSuchEntityException {
                if (!memory.containsValue(caption)) {
                throw new NoSuchEntityException("No Fashion for type == '" + caption + "', only for " + memory.values());
            }
            return memory.get(caption);
    }*/

        @Override
        public List<Fashion> selectAll() throws DaoSystemException {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Fashion> criteriaQuery = criteriaBuilder.createQuery(Fashion.class);
            Root<Fashion> root = criteriaQuery.from(Fashion.class);
            criteriaQuery.select(root);
            TypedQuery<Fashion> typedQuery = entityManager.createQuery(criteriaQuery);
            List<Fashion> allFashion = typedQuery.getResultList();
            return allFashion;
        }
    }


