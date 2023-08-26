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

import com.platform.cocktail.cocktail_platform.domain.Order;
import com.platform.cocktail.cocktail_platform.domain.OrderState;
import com.platform.cocktail.cocktail_platform.domain.OrderTemp;
import com.platform.cocktail.cocktail_platform.domain.Reservation;
import com.platform.cocktail.cocktail_platform.domain.ReservationState;
import com.platform.cocktail.cocktail_platform.service.OrderService;
import com.platform.cocktail.cocktail_platform.service.StoreService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("corporate/store")
public class CorporateStoreController {
	@Autowired
	private OrderService oService;
	@Autowired
	private StoreService sService;
	
	@GetMapping("salesMng")
	public String salesMng(int storeCode, Model m) {
		ArrayList<Order> orderList = oService.getOrderListsByCode(storeCode);
		m.addAttribute("orderList", orderList);
		return "";
	}
	
	@GetMapping("salesInput")
	public String salesInput(){//@AuthenticationPrincipal UserDetails user, int storeCode) {
		return "salesinputTest";
	}
	
	@PostMapping("salesInput")
	public String salesInput(int storeCode, int[] menuNum, String[] menuName, int[] price, int[] orderCount) {
		log.debug("{}, {}, {}, {}, {}", storeCode, menuNum, menuName, price, orderCount);
		oService.inputOrder(storeCode, null, null, menuNum, menuName, price, orderCount);
		
		return "redirect:/";
	}
	
	@GetMapping("reserveList")
	public String reserveList(int storeCode, Model m) {
		ArrayList<Reservation> reserveList = sService.getReservelistByCode(storeCode);
		m.addAttribute("reserveList", reserveList);
		return "";
	}
	
	@GetMapping("reserveAccept")
	public String reserveAccept(int storeCode, String reserveCode) {
		sService.changeReservationState(storeCode, reserveCode, ReservationState.received);
		return "";
	}
	
	@GetMapping("reserveRefuse")
	public String reserveRefuse(int storeCode, String reserveCode) {
		sService.changeReservationState(storeCode, reserveCode, ReservationState.refuesed);
		return "";
	}
	
	@GetMapping("reserveInput")
	public String reserveInput(int storeCode) {
		return "";
	}
	
	@PostMapping("reserveInput")
	public String reserveInput(Reservation reserve) {
		sService.insertReservation(reserve);
		return "";
	}
	
	@GetMapping("orderList")
	public String orderList(int storeCode, Model m) {
		ArrayList<OrderTemp> orderList = oService.getTemporderlistByCode(storeCode);
		m.addAttribute("orderList", orderList);
		return "";
	}
	
	@GetMapping("orderAccept")
	public String orderAccept(int tempOrderkey) {
		sService.changeOrderState(tempOrderkey, OrderState.received);
		return "";
	}
	
	@GetMapping("orderRefuse")
	public String orderRefuse(int tempOrderkey) {
		sService.changeOrderState(tempOrderkey, OrderState.refuesed);
		return "";
	}
}
