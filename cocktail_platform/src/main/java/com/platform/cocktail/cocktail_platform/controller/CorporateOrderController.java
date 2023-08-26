package com.platform.cocktail.cocktail_platform.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.platform.cocktail.cocktail_platform.domain.MemberPerson;
import com.platform.cocktail.cocktail_platform.domain.Menu;
import com.platform.cocktail.cocktail_platform.domain.Order;
import com.platform.cocktail.cocktail_platform.domain.OrderTemp;
import com.platform.cocktail.cocktail_platform.service.OrderService;
import com.platform.cocktail.cocktail_platform.service.StoreService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("corporate/order")
public class CorporateOrderController {
	@Autowired
	private StoreService sService;
	@Autowired
	private OrderService oService;
	
	@GetMapping("login")
	public String login() {
		return "";
	}
	
	@PostMapping("login")
	public String login(int storeCode, MemberPerson m) {
		Order o = oService.makeNewOrder(storeCode, m.getMemberId());
		return "";
	}
	
	@GetMapping("menu")
	public ArrayList<Menu> menu(int storeCode, Model m) {
		ArrayList<Menu> menuList = sService.getMenulistByCode(storeCode);
		return menuList;
	}
	
	@GetMapping("menuInfo")
	public String menuInfo(int menuNum, Model m) {
		Menu menu = sService.getMenuInfoByNum(menuNum);
		m.addAttribute("menu", menu);
		return "";
	}
	
	@GetMapping("cart")
	public String cart(Model m) {
		ArrayList<Menu> menuList = new ArrayList<>();
		m.addAttribute("menuList", menuList);
		return "";
	}
	
	@GetMapping("addToCart")
	public void addToCart(int storeCode, String memberId, int[] menuNum, int[] orderCount) {
		
	}
	
	@PostMapping("orderMenu")
	public String orderMenu(int storeCode, String memberId, String orderCode, int[] menuNum, String[] menuName, int[] price, int[] orderCount) {
		oService.inputOrder(storeCode, memberId, orderCode, menuNum, menuName, price, orderCount);
		return "";
	}
	
	@GetMapping("callStaff")
	public String callStaff(String orderCode) {
		return "";
	}
	
	@PostMapping("payment")
	public String payment(int storeCode, String memberId, String orderCode) {
		oService.finishOrderByCode(orderCode);
		return "";
	}
}
