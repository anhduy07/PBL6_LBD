package com.example.demo.service;

import com.example.demo.model.Goods;

import java.util.List;

public interface GoodsService {
    List<Goods> findAllGoods();
    List<Goods> searchGoods(long idCategory, double startPrice, double endPrice);
 
   
    List<Goods> findAllByGoodsNameContaining(String name);
}
