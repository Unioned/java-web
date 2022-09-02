package com.mypro.fruit.dao;

import com.mypro.fruit.pojo.Fruit;

import java.util.List;

public interface FruitDAO {
    //获取所有库存列表信息
    List<Fruit> getFruitList(String keyword,Integer pageNo);

    //通过id得到水果单元
    Fruit getFruitByFid(Integer fid);

    //修改指定库存记录
    void updateFruit(Fruit fruit);

    //根据fid删除指定库存记录
    void delFruit(Integer fid);

    //添加新库存记录
    void addFruit(Fruit fruit);

    //获取库存记录条数
    int getFruitCount(String keyword);
}
