package net.flosev.quiz.dao.impl;

import net.flosev.quiz.dao.SizeDao;
import net.flosev.quiz.dao.exception.DaoSystemException;
import net.flosev.quiz.dao.exception.NoSuchEntityException;
import net.flosev.quiz.entity.Size;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: виталик
 * Date: 21.06.14
 * Time: 17:46
 * To change this template use File | Settings | File Templates.
 */
public class SizeDaoMock implements SizeDao {
    public Map<Integer, Size> memory = new HashMap<>();

    public SizeDaoMock() {
        List<Size> emptyList = Collections.emptyList();

        Size s47 = new Size(Size.class.getName(),23,34,23);
        Size s27 = new Size("s27",34,56,67);
        Size s57 = new Size("S57",12,34,23);
        Size s67 = new Size("S67",14,56,67);

        this.memory.put(1, s47);
        this.memory.put(2, s27);
        this.memory.put(3, s57);
        this.memory.put(4, s67);

    }

    @Override
    public Size selectByLength(int length) throws DaoSystemException, NoSuchEntityException {
        if (!memory.containsKey(length)) {
            throw new NoSuchEntityException("No Theme for id == '" + length + "', only for " + memory.keySet());
        }
        return memory.get(length);
    }

    @Override
    public Size selectByWidth(int width) throws DaoSystemException, NoSuchEntityException {
        if (!memory.containsKey(width)) {
            throw new NoSuchEntityException("No Theme for id == '" + width + "', only for " + memory.keySet());
        }
        return memory.get(width);
    }

    @Override
    public List<Size> selectAll() throws DaoSystemException {
        return new ArrayList<>(memory.values());
    }
}
