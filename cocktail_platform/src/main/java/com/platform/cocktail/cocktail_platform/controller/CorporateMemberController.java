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

import com.platform.cocktail.cocktail_platform.api.mail_send.service.EmailService;
import com.platform.cocktail.cocktail_platform.domain.MemberCorporate;
import com.platform.cocktail.cocktail_platform.domain.Order;
import com.platform.cocktail.cocktail_platform.domain.StoreInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("corporate/member")
public class CorporateMemberController {
	
	@Autowired
	EmailService emailService;
	
	@GetMapping("join")
	public String join() {
		return "";
	}
	
	@PostMapping("join")
	public String join(MemberCorporate m) {
		return "";
	}
	
	@ResponseBody
	@GetMapping("checkId")
	public boolean checkId(String memberId) {
		boolean canUse = true;
		return canUse;
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
	
//	@PostMapping("login")
//	public String login(MemberCorporate m) {
//		return "";
//	}
	
	@GetMapping("findId")
	public String findId() {
		return "";
	}
	
	@PostMapping("findId")
	public String findId(Model m) {
		return "";
	}
	
	@GetMapping("resetPw")
	public String resetPw() {
		return "";
	}
	
	@PostMapping("resetPw")
	public String resetPw(Model m) {
		return "";
	}
	
	@GetMapping("storePage")
	public String storePage(@AuthenticationPrincipal UserDetails user, Model m) {
		StoreInfo store = new StoreInfo();
		m.addAttribute("store", store);
		return "";
	}
	
	@GetMapping("editStorepage")
	public String editStorepage(@AuthenticationPrincipal UserDetails user, Model m) {
		StoreInfo store = new StoreInfo();
		m.addAttribute("store", store);
		return "";
	}
	
	@PostMapping("editStorepage")
	public String editStorepage(StoreInfo store) {
		return "";
	}
}
