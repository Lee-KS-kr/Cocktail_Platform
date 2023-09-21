package com.platform.cocktail.cocktail_platform.controller.corporate;

import java.net.URLDecoder;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platform.cocktail.cocktail_platform.domain.Category;
import com.platform.cocktail.cocktail_platform.domain.Menu;
import com.platform.cocktail.cocktail_platform.service.StoreService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("corporate/order")
public class CorporateAjaxOrderController {
	
	@PostMapping("recommendList")
	public ArrayList<Menu> recommendList(int storeCode){
		ArrayList<Menu> list = new ArrayList<>();
		return list;
	}
	
	@PostMapping("menuCategory")
	public ArrayList<Category> menuCategory(int storeCode){
		ArrayList<Category> list = new ArrayList<>();
		list.add(Category.food);
		list.add(Category.beverage);
		list.add(Category.side);
		
		return list;
	}

}
