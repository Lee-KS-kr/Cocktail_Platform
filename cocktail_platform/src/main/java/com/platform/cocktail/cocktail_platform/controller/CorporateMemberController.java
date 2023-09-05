package com.platform.cocktail.cocktail_platform.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.platform.cocktail.cocktail_platform.domain.Member;
import com.platform.cocktail.cocktail_platform.domain.MemberType;
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
	
//
//	@GetMapping("join")
//	public String join() {
//		return "corporateView/joinForm";
//	}
//	
//	@PostMapping("join")
//	public String join(Member m) {
//		m.setMemberType(MemberType.ROLE_PERSONAL);
//		log.debug("들어온 값 : {}", m);
//		mService.insertMember(m);
//		
//		return "redirect:/member/login";
//	}
//
//	
//	@GetMapping("loginForm")
//	public String login() {
//		return "corporateView/loginForm";
//	}
//	
//	@GetMapping("findId")
//	public String findId() {
//		return "";
//	}
//	
//	@PostMapping("findId")
//	public String findId(String email, Model m) {
//		Member mem = mService.findMemberByEmail(email);
//		m.addAttribute("memberId", mem.getMemberId());
//		return "";
//	}
//	
//	@GetMapping("resetPw")
//	public String resetPw() {
//		return "";
//	}
//	
//	@PostMapping("resetPw")
//	public String resetPw(Member m) {
//		mService.resetPw(m);
//		return "";
//	}
	
	@GetMapping("mypage")
	public String mypage(@AuthenticationPrincipal UserDetails user) {
		return "corporateView/corpMyPage";
	}
}
