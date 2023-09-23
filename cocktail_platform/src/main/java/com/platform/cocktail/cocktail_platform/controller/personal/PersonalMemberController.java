package com.platform.cocktail.cocktail_platform.controller.personal;

import java.util.ArrayList;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.platform.cocktail.cocktail_platform.domain.Member;
import com.platform.cocktail.cocktail_platform.domain.Menu;
import com.platform.cocktail.cocktail_platform.domain.Order;
import com.platform.cocktail.cocktail_platform.domain.OrderState;
import com.platform.cocktail.cocktail_platform.domain.OrderTemp;
import com.platform.cocktail.cocktail_platform.domain.Reservation;
import com.platform.cocktail.cocktail_platform.domain.StoreInfo;
import com.platform.cocktail.cocktail_platform.domain.StoreReview;
import com.platform.cocktail.cocktail_platform.domain.Taste;
import com.platform.cocktail.cocktail_platform.service.MemberService;
import com.platform.cocktail.cocktail_platform.service.OrderService;
import com.platform.cocktail.cocktail_platform.service.StoreService;
import com.platform.cocktail.cocktail_platform.service.TestService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("personal/member")
public class PersonalMemberController {
	@Autowired
	private MemberService mService;
	@Autowired
	private OrderService oService;
	@Autowired
	private StoreService sService;
	@Autowired
	private TestService tService;
	
	//취향 설정 화면
	@GetMapping("taste")
	public String taste(String memberId, Model m) {
		m.addAttribute("memberId", memberId);
		return "personalView/taste";
	}
	
	//취향 설정
	@PostMapping("taste")
	public String taste(Taste t) {
		//t.setMemberId(memberId);
		log.debug("{}", t);
		mService.insertTaste(t);
		return "redirect:/";
	}
	
//	@GetMapping("loginForm")
//	public String login() {
//		return "personalView/login";
//	}
//	
//	@GetMapping("findId")
//	public String findId() {
//		return "personalView/FindID";
//	}
//	
//	@PostMapping("findId")
//	public String findId(String email, Model m) {
//		Member mem = mService.findMemberByEmail(email);
//		m.addAttribute("memberId", mem.getMemberId());
//		return "redirect:/personal/findId";
//	}
	
	//비밀번호 찾기 화면
//	@GetMapping("resetPw")
//	public String resetPw() {
//		return "personalView/FindPw";
//	}
//	
//	//비밀번호 재설정
//	@PostMapping("resetPw")
//	public String resetPw(Member m) {
//		mService.resetPw(m);
//		return "redirect:/personal/home";
//	}
	
	//마이페이지
	@GetMapping("myPage")
	public String myPage(@AuthenticationPrincipal UserDetails user, Model m) {
		Member mem = mService.findMemberById(user.getUsername());
		Taste t = mService.findTasteById(user.getUsername());
		
		m.addAttribute("member", mem);
		m.addAttribute("taste", t);
		
		log.debug("{} : {}", mem, t);
		
		return "personalView/myPage";
	}
	
	//개인정보 수정 페이지
	@GetMapping("editPrivacyInfo")
	public String editPrivacyInfo(@AuthenticationPrincipal UserDetails user, Model m) {
		Member mem = mService.findMemberById(user.getUsername());
		mem.setMemberId(user.getUsername());
		m.addAttribute("member", mem);
		return "personalView/editPrivacyInfo";
	}
	
	//개인정보 수정
	@PostMapping("editPrivacyInfo")
	public String editPrivacyInfo(Member mem, @AuthenticationPrincipal UserDetails user, Model m) {
		mem.setMemberId(user.getUsername());
		mService.updateMember(mem);
		
		return "redirect:/personal/member/myPage";
	}
	
	//예약 목록
	@GetMapping("reserveList")
	public String reserveList(@AuthenticationPrincipal UserDetails user, Model m) {
		ArrayList<Reservation> reserveList = sService.getReservelistById(user.getUsername());
		m.addAttribute("reserveList", reserveList);
		return "personalView/reserveList";
	}
	
	//예약 상세 내역
	@GetMapping("reserveInfo")
	public String reserveInfo(String reserveCode, Model m) {
		Reservation reservation = sService.getReservationByCode(reserveCode);
		m.addAttribute("reservation", reservation);
		return "personalView/reserveInfo";
	}
	
	//주문,취소 목록
	@GetMapping("orderList")
	public String orderLists(@AuthenticationPrincipal UserDetails user, Model m) {
		ArrayList<Order> list = oService.getOrderLists(user.getUsername());
		for (Order o : list) {
			StoreInfo s = sService.getStoreinfoByCode(o.getStoreCode());
			o.setStoreName(s.getStoreName());
		}
		
		m.addAttribute("orderList", list);
		return "personalView/OrderList";
	}
	
	//주문 상세 내역
	@GetMapping("orderInfo")
	public String orderInfo(String orderCode, Model m) {
		Order o = oService.findOrderByOrdercode(orderCode);
		ArrayList<OrderTemp> list = oService.getOrdersByOrdercode(orderCode);
		
		m.addAttribute("order", o);
		m.addAttribute("orderList", list);
		return "personalView/OrderDetails";
	}
	
	//취향 재설정 화면
	@GetMapping("resetTaste")
	public String resetTaste() {
		return "personalView/resetTaste";
	}
	
	//취향 재설정
	@PostMapping("resetTaste")
	public String resetTaste(@AuthenticationPrincipal UserDetails user, Taste t) {
		t.setMemberId(user.getUsername());
		mService.updateTaste(t);
		return "redirect:/personal/member/myPage";
	}
	
	//주문 후 평가
	@GetMapping("evaluation")
	public String evaluation(String orderCode, Model m) {
		Order o = oService.findOrderByOrdercode(orderCode);
		StoreInfo store = sService.getStoreinfoByCode(o.getStoreCode());
		ArrayList<Menu> list = oService.getMenusByCode(orderCode);
		
		m.addAttribute("list", list);
		m.addAttribute("store", store);
		log.debug("store {}, list {}", store, list);
		return "personalView/reviewWrite";
	}
	
	//주문 후 평가 제출
	@PostMapping("evaluation")
	public String evaluation(@AuthenticationPrincipal UserDetails user, StoreReview review
			, int[] menuNum, String[] weather, String[] ageGroup, String[] companion, String[] event) {
		if(menuNum.length == 1) {
			String str = StringUtils.join(weather);
			weather = new String[1];
			weather[0] = str;
			
			str = StringUtils.join(ageGroup);
			ageGroup = new String[1];
			ageGroup[0] = str;
			
			str = StringUtils.join(companion);
			companion = new String[1];
			companion[0] = str;
			
			str = StringUtils.join(event);
			event = new String[1];
			event[0] = str;
		}
		
		review.setMemberid(user.getUsername());
		log.debug("review {}", review);
		sService.insertReview(review, menuNum, weather, ageGroup, companion, event);
		return "redirect:/personal/member/reserveList";
	}
	
	@PostMapping("orderInputTest")
	public String orderInputTest(@AuthenticationPrincipal UserDetails user, Order o) {
		o.setMemberId(user.getUsername());
		o.setOrderState(OrderState.finished);
		log.debug("order : {}", o);
		tService.inputOrderTest(o);
		return "redirect:/personal/member/orderList";
	}
}
