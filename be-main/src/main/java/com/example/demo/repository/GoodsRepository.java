package com.example.demo.repository;

import com.example.demo.model.Goods;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods, Integer> {
	List<Goods> findAllByCategory_IdCategory(Integer id);

	List<Goods> findAllByGoodsNameContaining(String name);

	List<Goods> findByCategoryIdCategoryAndPriceBetween(long idCategory, double startPrice, double endPrice);

	List<Goods> findByCategoryIdCategory(long idCategory);

	List<Goods> findByPriceBetween(double startPrice, double endPrice);

	List<Goods> findByOrderBySaleOffDesc(Pageable pageable);
	
	List<Goods> findByOrderByCreatedDateDesc(Pageable pageable);
}
