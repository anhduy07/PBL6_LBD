package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Goods;
import com.example.demo.repository.GoodsRepository;
import com.example.demo.service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	GoodsRepository goodsRepository;

	@Override
	public List<Goods> findAllGoods() {
		return goodsRepository.findAll();
	}

	@Override
	public List<Goods> searchGoods(long idCategory, double startPrice, double endPrice) {

		if (idCategory == 0 && startPrice == -1)
			return this.findAllGoods();

		if (idCategory > 0 && startPrice > 0)
			return goodsRepository.findByCategoryIdCategoryAndPriceBetween(idCategory, startPrice, endPrice);

		if (idCategory == 0)
			return goodsRepository.findByPriceBetween(startPrice, endPrice);

		return goodsRepository.findByCategoryIdCategory(idCategory);
	}

	@Override
	public List<Goods> findAllByGoodsNameContaining(String name) {
		return goodsRepository.findAllByGoodsNameContaining(name);
	}
}
