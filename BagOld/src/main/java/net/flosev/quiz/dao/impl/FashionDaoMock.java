package net.flosev.quiz.dao.impl;

import net.flosev.quiz.dao.FashionDao;
import net.flosev.quiz.dao.exception.DaoSystemException;
import net.flosev.quiz.dao.exception.NoSuchEntityException;
import net.flosev.quiz.entity.Fashion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FashionDaoMock implements FashionDao {

    public Map<Integer, Fashion> memory = new HashMap<>();

    /*public List<Fashion>memory = new ArrayList<>();*/
    public FashionDaoMock() {
        this.memory.put(1, new Fashion(1,"сумки"));
        this.memory.put(2, new Fashion(2,"кейсы"));
        this.memory.put(3, new Fashion(3,"suitcases"));
        this.memory.put(4, new Fashion(4,"рюкзаки"));

    }

    @Override
    public Fashion selectById(int id) throws DaoSystemException, NoSuchEntityException {
        if (!memory.containsKey(id)) {
            throw new NoSuchEntityException("No Id for type == '" + id + "', only for " + memory.keySet());
        }
        return memory.get(id);
    }
    @Override
    public Fashion selectByCaption(String caption) throws DaoSystemException, NoSuchEntityException {
                Fashion result = new Fashion(0, "0");
        for (Fashion typee : memory.values()) {
            if (typee.getCaption()==caption) {
                result=typee;
            }
        }
        return result;
    }
    /*public Fashion selectByCaption(String caption) throws DaoSystemException, NoSuchEntityException {
                if (!memory.containsValue(caption)) {
                throw new NoSuchEntityException("No Fashion for type == '" + caption + "', only for " + memory.values());
            }
            return memory.get(caption);
    }*/

    @Override
    public List<Fashion> selectAll() throws DaoSystemException {
        return new ArrayList<>( memory.values());
    }
}
