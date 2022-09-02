package com.mypro.fruit.dao.impl;

import com.mypro.fruit.dao.FruitDAO;
import com.mypro.fruit.pojo.Fruit;
import com.mypro.myssm.basedao.BaseDAO;

import java.util.List;

public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {

    @Override
    public List<Fruit> getFruitList() {
        return super.executeQuery("select * from t_fruit");
    }
}
