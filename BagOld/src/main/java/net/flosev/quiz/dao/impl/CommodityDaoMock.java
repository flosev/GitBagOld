package net.flosev.quiz.dao.impl;

import net.flosev.quiz.dao.CommodityDao;
import net.flosev.quiz.dao.FashionDao;
import net.flosev.quiz.dao.exception.DaoSystemException;
import net.flosev.quiz.dao.exception.NoSuchEntityException;
import net.flosev.quiz.entity.Commodity;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommodityDaoMock implements CommodityDao{
    private Map<Integer, Commodity> memory = new HashMap<>();
    private DataSource dataSource;
    /*private ArrayList<String> types;*/
    public CommodityDaoMock() {
        /*SizeDao sizes = new SizeDaoMock();*/
        FashionDao fashions= new FashionDaoMock();
        /*List<String>types= new ArrayList<>(Arrays.asList("Сумки","Кейсы","Рюкзаки"));*/

       /* try {
            this.memory.put(1, new Commodity(1, "C27", asList(234,345,546), 34, fashions.selectByCaption("сумки") ));
            this.memory.put(2, new Commodity(2, "C47", asList(246,567,890), 45,  fashions.selectByCaption("сумки") ));
            this.memory.put(3, new Commodity(3, "C37", asList(439,670,213), 45,  fashions.selectByCaption("рюкзаки")));
            this.memory.put(4, new Commodity(4, "C67", asList(45,34,78),  67,  fashions.selectByCaption("кейсы")));
            this.memory.put(5, new Commodity(5, "C57", asList(421,678,214), 58,  fashions.selectByCaption("рюкзаки")));
            this.memory.put(6, new Commodity(6, "C97", asList(430,620,24), 56,  fashions.selectByCaption("suitcases")));
        } catch (DaoSystemException | NoSuchEntityException e) {
            throw new Error(e);
        }*/
    }



    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
   public Commodity selectById(int id) throws DaoSystemException, NoSuchEntityException {
        if (!memory.containsKey(id)) {
            throw new NoSuchEntityException("No Quiz for id == '" + id + "', only for " + memory.keySet());
        }
        return memory.get(id);
    }



    @Override
    public List <Commodity> selectByPrice(int price) throws DaoSystemException, NoSuchEntityException {
        ArrayList<Commodity> result = new ArrayList<>();
        for (Commodity commodity : memory.values()) {
            if (commodity.getPrice()==price) {
                result.add(commodity);
            }
        }
        return result;
    }

    @Override
    public List<Commodity> selectByCaption(String caption) throws DaoSystemException, NoSuchEntityException {
        ArrayList<Commodity> result = new ArrayList<>();
        for (Commodity commodity : memory.values()) {
            /*if (commodity.getFashion().getCaption() ==caption) {*/
            if (commodity.getFashion().getCaption() ==caption) {
                result.add(commodity);
            }
        }
        return result;
    }
    /*public List<Commodity> selectBySize(Size sizes) throws DaoSystemException {
        ArrayList<Commodity> result = new ArrayList<>();
        for (Commodity commodity : memory.values()) {
            if (commodity.getSizes().contains(sizes)) {
                result.add(commodity);
            }
        }
        return result;
    }*/

    @Override
    public List<Commodity> selectByFashion(int id) throws DaoSystemException, NoSuchEntityException {

            ArrayList<Commodity> result = new ArrayList<>();
            for (Commodity commodity : memory.values()) {
                if (commodity.getFashion().getId() ==id) {
                    result.add(commodity);
                }
            }
            return result;
        }


    @Override
    public List<Commodity> selectByLength(int length) throws DaoSystemException {
        /*ArrayList<Commodity> result = new ArrayList<>();
        for (Commodity commodity : memory.values()) {
            List<Integer> Et=commodity.getSizes();
            if (Et.get(0) ==length) {
                result.add(commodity);
            }
        }
        return result;*/
        return null;
    }



    @Override
    public List<Commodity> selectAll() throws DaoSystemException {
        return new ArrayList<>(memory.values());
    }

}
