package com.platform.cocktail.cocktail_platform.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.platform.cocktail.cocktail_platform.domain.Order;
import com.platform.cocktail.cocktail_platform.domain.OrderTemp;
import com.platform.cocktail.cocktail_platform.domain.Reservation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("corporate/store")
public class CorporateStoreController {
	
	@GetMapping("salesMng")
	public String salesMng(int storeCode, Model m) {
		ArrayList<Order> orderList = new ArrayList<>();
		m.addAttribute("orderList", orderList);
		return "";
	}
	
	@GetMapping("salesInput")
	public String salesInput(int storeCode) {
		return "";
	}
	
	@PostMapping("salesInput")
	public String salesInput(OrderTemp[] tempArr) {
		return "";
	}
	
	@GetMapping("reserveList")
	public String reserveList(int storeCode, Model m) {
		ArrayList<Reservation> reserveList = new ArrayList<>();
		m.addAttribute("reserveList", reserveList);
		return "";
	}
	
	@GetMapping("reserveAccept")
	public String reserveAccept(int storeCode, String reserveCode) {
		return "";
	}
	
	@GetMapping("reserveRefuse")
	public String reserveRefuse(int storeCode, String reserveCode) {
		return "";
	}
	
	@GetMapping("reserveInput")
	public String reserveInput(int storeCode) {
		return "";
	}
	
	@PostMapping("reserveInput")
	public String reserveInput(Reservation reserve) {
		return "";
	}
	
	@GetMapping("orderList")
	public String orderList(Order order, Model m) {
		ArrayList<OrderTemp> orderList = new ArrayList<>();
		m.addAttribute("orderList", orderList);
		return "";
	}
	
	@GetMapping("orderAccept")
	public String orderAccept(String orderCode) {
		return "";
	}
	
	@GetMapping("orderRefuse")
	public String orderRefuse(String orderCode) {
		return "";
	}
}
