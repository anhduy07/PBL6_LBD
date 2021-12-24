package com.example.demo.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Category;
import com.example.demo.model.Goods;
import com.example.demo.repository.BillRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.GoodsRepository;
import com.example.demo.service.BillService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.GoodsService;
import com.example.demo.service.UserService;

@Controller
@RestController
@CrossOrigin
@RequestMapping("/goods")
@Transactional
public class GoodsController {
	@Autowired
	GoodsService goodsService;
	@Autowired
	UserService userService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	BillService billService;
	@Autowired
	BillRepository billRepository;

	@Autowired
	private GoodsRepository goodsRepository;
	@Autowired
	CategoryRepository categoryRepository;

	public static Boolean check = false;

	@GetMapping("/getAll")
	public ResponseEntity<List<Goods>> getAllGoods() {
		List<Goods> goodsList = goodsService.findAllGoods();
		if (goodsList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(goodsList, HttpStatus.OK);
	}

	@GetMapping("/getSaleOff")
	public ResponseEntity<List<Goods>> getSaleOff() {
		List<Goods> goodsList = goodsRepository.findByOrderBySaleOffDesc(PageRequest.of(0, 8));
		return new ResponseEntity<>(goodsList, HttpStatus.OK);
	}

	@GetMapping("/getHotTrend")
	public ResponseEntity<List<Goods>> getHotTrend() {
		List<Goods> goodsList = goodsRepository.findByOrderByCreatedDateDesc(PageRequest.of(0, 8));
		return new ResponseEntity<>(goodsList, HttpStatus.OK);
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Goods> findById(@PathVariable("id") int id) {
		Goods goods = goodsRepository.findById(id).get();
		if (goods == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(goods, HttpStatus.OK);
	}

	@GetMapping("/search")
	public List<Goods> searchGoods(@RequestParam(name = "idCategory", defaultValue = "0") long categoryId,
			@RequestParam(name = "startPrice", defaultValue = "-1") double startPrice,
			@RequestParam(name = "endPrice", defaultValue = "0") double endPrice) {

		return goodsService.searchGoods(categoryId, startPrice, endPrice);
	}

	@GetMapping("/getAllTypeGoods")
	public List<Category> getAllTypeGoods() {

		return categoryRepository.findAll();
	}

	@GetMapping("/inputSearch/{value}")
	public ResponseEntity<List<Goods>> inputSearch(@PathVariable("value") String value) {
		List<Goods> goodsList = goodsService.findAllByGoodsNameContaining(value);
		return new ResponseEntity<>(goodsList, HttpStatus.OK);
	}

	@PostMapping("/saveGoods")
	public Goods saveGoods(@RequestBody Goods goods) {

		if (goods.getIdGoods() == 0)
			goods.setCreatedDate(LocalDate.now());
		return goodsRepository.save(goods);
	}

	@DeleteMapping("/deleteGoods/{id}")
	public void deleteGoods(@PathVariable("id") int id) {

		goodsRepository.deleteById(id);
	}

}
