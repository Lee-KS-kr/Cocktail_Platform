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
import org.springframework.web.bind.annotation.ResponseBody;

import com.platform.cocktail.cocktail_platform.api.mail_send.service.EmailService;
import com.platform.cocktail.cocktail_platform.domain.Member;
import com.platform.cocktail.cocktail_platform.domain.MemberType;
import com.platform.cocktail.cocktail_platform.domain.Menu;
import com.platform.cocktail.cocktail_platform.domain.Order;
import com.platform.cocktail.cocktail_platform.domain.OrderTemp;
import com.platform.cocktail.cocktail_platform.domain.StoreReview;
import com.platform.cocktail.cocktail_platform.domain.Taste;
import com.platform.cocktail.cocktail_platform.service.MemberService;
import com.platform.cocktail.cocktail_platform.service.OrderService;
import com.platform.cocktail.cocktail_platform.service.StoreService;

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
	
	//취향 설정 화면
	@GetMapping("taste")
	public String taste(String memberId, Model m) {
		m.addAttribute("memberId", memberId);
		return "personalView/taste";
	}
	
	//취향 설정
	@PostMapping("taste")
	public String taste(String memberId, Taste t) {
		t.setMemberId(memberId);
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
		
		return "personalView/mypPage";
	}
	
	//개인정보 수정 페이지
	@GetMapping("editPrivacyInfo")
	public String editPrivacyInfo(@AuthenticationPrincipal UserDetails user, Model m) {
		Member mem = mService.findMemberById(user.getUsername());
		mem.setMemberId(user.getUsername());
		m.addAttribute("member", mem);
		return "";
	}
	
	//개인정보 수정
	@PostMapping("editPrivacyInfo")
	public String editPrivacyInfo(Member mem, @AuthenticationPrincipal UserDetails user, Model m) {
		mem.setMemberId(user.getUsername());
		mService.updateMember(mem);
		
		return "redirect:/personal/myPage";
	}
	
	//주문,취소 목록
	@GetMapping("orderLists")
	public String orderLists(@AuthenticationPrincipal UserDetails user, Model m) {
		ArrayList<Order> list = oService.getOrderLists(user.getUsername());
		m.addAttribute("orderList", list);
		return "";
	}
	
	//주문 상세 내역
	@GetMapping("orderInfo")
	public String orderInfo(String orderCode, Model m) {
		Order o = oService.findOrderByOrdercode(orderCode);
		ArrayList<OrderTemp> list = oService.getOrdersByOrdercode(orderCode);
		
		m.addAttribute("order", o);
		m.addAttribute("orderList", list);
		return "";
	}
	
	//취향 재설정 화면
	@GetMapping("resetTaste")
	public String resetTaste() {
		return "";
	}
	
	//취향 재설정
	@PostMapping("resetTaste")
	public String resetTaste(@AuthenticationPrincipal UserDetails user, Taste t) {
		t.setMemberId(user.getUsername());
		mService.updateTaste(t);
		return "";
	}
	
	//주문 후 평가
	@GetMapping("evaluation")
	public String evaluation(String orderCode, Model m) {
		ArrayList<Menu> menuList = oService.getMenulistByOrdercode(orderCode);
		m.addAttribute("menuList", menuList);
		return "";
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
		sService.insertReview(review, menuNum, weather, ageGroup, companion, event);
		return "";
	}
	
	//회원 탈퇴 페이지
	@GetMapping("quitMember")
	public String quitMember() {
		return "";
	}
	
	//회원 탈퇴
	@PostMapping("quitMember")
	public String quitMember(@AuthenticationPrincipal UserDetails user) {
		mService.unableMember(user.getUsername());
		return "";
	}
}
