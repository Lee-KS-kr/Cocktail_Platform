package com.platform.cocktail.cocktail_platform.controller.corporate;

import java.util.ArrayList;
import java.util.Date;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.platform.cocktail.cocktail_platform.domain.Menu;
import com.platform.cocktail.cocktail_platform.domain.Order;
import com.platform.cocktail.cocktail_platform.domain.OrderState;
import com.platform.cocktail.cocktail_platform.domain.OrderTemp;
import com.platform.cocktail.cocktail_platform.domain.Reservation;
import com.platform.cocktail.cocktail_platform.domain.ReservationState;
import com.platform.cocktail.cocktail_platform.domain.StoreInfo;
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
	
	//매출 관리 페이지 이동
	@GetMapping("salesMng")
	public String salesMng(@AuthenticationPrincipal UserDetails user, Model m) {
		StoreInfo store = sService.getStoreById(user.getUsername());
		ArrayList<Order> orderList = oService.getOrderListsByCode(store.getStoreCode());
		m.addAttribute("orderList", orderList);
		
		log.debug("orderlist {}", orderList);
		return "corporateView/salesMng";
	}
	
	@GetMapping("salesInput")
	public String salesInput(@AuthenticationPrincipal UserDetails user, Model m) {
		StoreInfo store = sService.getStoreById(user.getUsername());
		ArrayList<Menu> menulist = sService.getMenulistByCode(store.getStoreCode());
		
		m.addAttribute("store", store);
		m.addAttribute("menulist", menulist);
		return "salesinputTest";
	}
	
	@PostMapping("salesInput")
	public String salesInput(int storeCode, int[] menuNum, String[] menuName, int[] price, int[] orderCount) {
		log.debug("{}, {}, {}, {}, {}", storeCode, menuNum, menuName, price, orderCount);
		oService.inputOrder(storeCode, null, null, menuNum, menuName, price, orderCount);
		
		return "redirect:/corporate/home";
	}
	
	//예약 확인 페이지 이동
	@GetMapping("reserveList")
	public String reserveList() {
		return "corporateView/reserveMng";
	}
	
	@GetMapping("reserveAccept")
	public String reserveAccept(int storeCode, String reserveCode) {
		sService.changeReservationState(storeCode, reserveCode, ReservationState.received);
		return "redirect:/corporate/store/reserveList";
	}
	
	@GetMapping("reserveRefuse")
	public String reserveRefuse(int storeCode, String reserveCode) {
		sService.changeReservationState(storeCode, reserveCode, ReservationState.refused);
		return "redirect:/corporate/store/reserveList";
	}
	
	//예약 수기 입력 페이지 이동
	@GetMapping("reserveInput")
	public String reserveInput(@AuthenticationPrincipal UserDetails user, Model m) {
		StoreInfo store = sService.getStoreById(user.getUsername());
		m.addAttribute("storeCode", store.getStoreCode());
		return "corporateView/reserveInput";
	}
	
	//예약 수기 입력 페이지 완료
	@PostMapping("reserveInput")
	public String reserveInput(Reservation reserve, String reserveTime) {
		log.debug("time : {}", reserveTime);
		reserve.setReserveDate(reserve.getReserveDate() + " " + reserveTime);
		reserve.setReserveState(ReservationState.received);
		log.debug("reserve : {}", reserve);
		sService.insertReservation(reserve);
		return "redirect:/corporate/home";
	}
	
	//주문 관리 페이지 이동
	@GetMapping("orderList")
	public String orderList() {
		return "corporateView/orderList";
	}
	
	//메뉴 관리 페이지 이동
	@GetMapping("menuMng")
	public String menuMng() {
		return "corporateView/menuMng";
	}
	
	@GetMapping("editMenu")
	public String editMenu(int menuNum, Model m) {
		Menu menu = sService.getMenuInfoByNum(menuNum);
		m.addAttribute("menu", menu);
		
		return "corporateView/editMenu";
	}
}
