package com.platform.cocktail.cocktail_platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.platform.cocktail.cocktail_platform.dao.MemberDAO;
import com.platform.cocktail.cocktail_platform.domain.Member;
import com.platform.cocktail.cocktail_platform.domain.MemberType;
import com.platform.cocktail.cocktail_platform.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("member")
public class MemberController {
	
	@Autowired
	MemberService mService;	
	@Autowired
	MemberDAO dao;

	@GetMapping("login")	
	public String home() {
		return "memberView/loginForm";
	}
	
	@GetMapping("join")	
	public String join() {
		return "memberView/joinForm"; 
	}
	
	@PostMapping("join")
	public String join(Member m) {
		log.debug("들어온 값 : {}", m);
		mService.insertMember(m);
		
		return "redirect:/member/login";
	}
	
	@GetMapping("findId")	
	public String findId() {
		return "memberView/findIdForm";
	}
	
	@GetMapping("findPw")	
	public String findPw() {
		return "memberView/findPwForm";
	}
	
	
	
}
