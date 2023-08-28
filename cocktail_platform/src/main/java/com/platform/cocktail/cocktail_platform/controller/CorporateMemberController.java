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
import com.platform.cocktail.cocktail_platform.domain.MemberPerson;
import com.platform.cocktail.cocktail_platform.domain.MemberType;
import com.platform.cocktail.cocktail_platform.domain.Order;
import com.platform.cocktail.cocktail_platform.domain.StoreInfo;
import com.platform.cocktail.cocktail_platform.service.MemberService;
import com.platform.cocktail.cocktail_platform.service.StoreService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("corporate/member")
public class CorporateMemberController {
	@Autowired
	private MemberService mService;
	@Autowired
	private StoreService sService;
	
	@GetMapping("join")
	public String join() {
		return "";
	}
	
	@PostMapping("join")
	public String join(MemberCorporate m) {
		log.debug("들어온 값 : {}", m);
		mService.insertMember(m);
		
		return "";
	}

	@GetMapping("login")
	public String login() {
		return "corporateView/loginForm";
	}
	
	@GetMapping("findId")
	public String findId() {
		return "";
	}
	
	@PostMapping("findId")
	public String findId(String email, Model m) {
		MemberCorporate mem = (MemberCorporate) mService.findMemberByEmail(email, MemberType.clientMem);
		m.addAttribute("memberId", mem.getMemberId());
		return "";
	}
	
	@GetMapping("resetPw")
	public String resetPw() {
		return "";
	}
	
	@PostMapping("resetPw")
	public String resetPw(MemberCorporate m) {
		mService.resetPw(m);
		return "";
	}
	
	@GetMapping("editPrivacyInfo")
	public String editPrivacyInfo(@AuthenticationPrincipal UserDetails user, Model m) {
		MemberCorporate mem = (MemberCorporate) mService.findMemberById(user.getUsername(), MemberType.clientMem);
		mem.setMemberId(user.getUsername());
		m.addAttribute("member", mem);
		return "";
	}
	
	@PostMapping("editPrivacyInfo")
	public String editPrivacyInfo(MemberCorporate mem, @AuthenticationPrincipal UserDetails user, Model m) {
		mem.setMemberId(user.getUsername());
		mService.updateMember(mem);
		
		return "";
	}
	
	@GetMapping("storePage")
	public String storePage(@AuthenticationPrincipal UserDetails user, Model m) {
		StoreInfo store = sService.getStoreById(user.getUsername());
		m.addAttribute("store", store);
		return "";
	}
	
	@GetMapping("editStorepage")
	public String editStorepage(@AuthenticationPrincipal UserDetails user, Model m) {
		StoreInfo store = sService.getStoreById(user.getUsername());
		m.addAttribute("store", store);
		return "";
	}
	
	@PostMapping("editStorepage")
	public String editStorepage(@AuthenticationPrincipal UserDetails user, StoreInfo store) {
		store.setMemberId(user.getUsername());
		sService.updateStoreinfo(store);
		return "";
	}
}
