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
import org.springframework.web.bind.annotation.ResponseBody;

import com.platform.cocktail.cocktail_platform.domain.Member;
import com.platform.cocktail.cocktail_platform.domain.MemberPerson;
import com.platform.cocktail.cocktail_platform.domain.Order;
import com.platform.cocktail.cocktail_platform.domain.OrderTemp;
import com.platform.cocktail.cocktail_platform.domain.Taste;
import com.platform.cocktail.cocktail_platform.service.EmailService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("personal/member")
public class PersonalMemberController {
	
	@Autowired
	EmailService emailService;
	
	@GetMapping("join")
	public String join() {
		return "";
	}
	
	@PostMapping("join")
	public String join(MemberPerson m) {
		log.debug("들어온 값 : {}", m);
		return "redirect:/personal/home";
	}
	
	@ResponseBody
	@GetMapping("checkId")
	public boolean checkId(String memberId) {
		return true;
	}
	
	@ResponseBody
	@PostMapping("emailConfirm")
	public String emailConfirm(String email) throws Exception {
		String confirm = emailService.sendSimpleMessage(email);
		return confirm;
	}
	
	@GetMapping("login")
	public String login() {
		return "";
	}
//	
//	@PostMapping("login")
//	public String login(MemberPerson m) {
//		log.debug("들어온 값 : {}", m);
//		return "";
//	}
//	
	@GetMapping("findId")
	public String findId() {
		return "";
	}
	
	@PostMapping("findId")
	public String findId(Model m) {
		MemberPerson mem = new MemberPerson();
		m.addAttribute("memberId", mem.getMemberId());
		return "";
	}
	
	@GetMapping("resetPw")
	public String resetPw() {
		return "";
	}
	
	@PostMapping("resetPw")
	public String resetPw(String memberId) {
		return "";
	}
	
	@GetMapping("myPage")
	public String myPage(@AuthenticationPrincipal UserDetails user, Model m) {
		MemberPerson mem = new MemberPerson();
		mem.setMemberId(user.getUsername());
		m.addAttribute("member", mem);
		return "";
	}
	
	@GetMapping("editPrivacyInfo")
	public String editPrivacyInfo(@AuthenticationPrincipal UserDetails user, Model m) {
		MemberPerson mem = new MemberPerson();
		mem.setMemberId(user.getUsername());
		m.addAttribute("member", mem);
		return "";
	}
	
	@PostMapping("editPrivacyInfo")
	public String editPrivacyInfo(MemberPerson mem, @AuthenticationPrincipal UserDetails user, Model m) {
		return "";
	}
	
	@GetMapping("orderLists")
	public String orderLists(@AuthenticationPrincipal UserDetails user, Model m) {
		ArrayList<Order> list = new ArrayList<>();
		m.addAttribute("orderList", list);
		return "";
	}
	
	@GetMapping("orderInfo")
	public String orderInfo(String orderCode, Model m) {
		Order o = new Order();
		ArrayList<OrderTemp> list = new ArrayList<>();
		
		m.addAttribute("order", o);
		m.addAttribute("orderList", list);
		return "";
	}
	
	@GetMapping("resetTaste")
	public String resetTaste() {
		return "";
	}
	
	@PostMapping("resetTaste")
	public String resetTaste(Taste t) {
		return "";
	}
	
	@GetMapping("quitMember")
	public String quitMember() {
		return "";
	}
	
	@PostMapping("quitMember")
	public String quitMember(@AuthenticationPrincipal UserDetails user) {
		return "";
	}
}
