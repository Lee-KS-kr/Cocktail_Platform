package com.platform.cocktail.cocktail_platform.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.platform.cocktail.cocktail_platform.domain.MemberPerson;
import com.platform.cocktail.cocktail_platform.domain.Menu;
import com.platform.cocktail.cocktail_platform.domain.OrderTemp;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("corporate/order")
public class CorporateOrderController {
	@GetMapping("login")
	public String login() {
		return "";
	}
	
	@PostMapping("login")
	public String login(MemberPerson m) {
		return "";
	}
	
	@GetMapping("menu")
	public ArrayList<Menu> menu(int storeCode) {
		ArrayList<Menu> menuList = new ArrayList<>();
		return menuList;
	}
	
	@GetMapping("menuInfo")
	public String menuInfo(int menuNum, Model m) {
		Menu menu = new Menu();
		m.addAttribute("menu", menu);
		return "";
	}
	
	@GetMapping("cart")
	public String cart(Model m) {
		ArrayList<Menu> menuList = new ArrayList<>();
		m.addAttribute("menuList", menuList);
		return "";
	}
	
	@PostMapping("orderMenu")
	public String orderMenu(OrderTemp[] orderArr) {
		return "";
	}
	
	@GetMapping("callStaff")
	public String callStaff(String orderCode) {
		return "";
	}
	
	@PostMapping("payment")
	public String payment(OrderTemp[] orderArr) {
		return "";
	}
	
	@GetMapping("evaluation")
	public String evaluation(String orderCode, Model m) {
		ArrayList<Menu> menuList = new ArrayList<>();
		m.addAttribute("menuList", menuList);
		return "";
	}
	
	@PostMapping("evaluation")
	public String evaluation(String orderCode, int[] menuNum, String[] review) {
		return "";
	}
}
