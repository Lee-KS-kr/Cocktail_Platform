package com.platform.cocktail.cocktail_platform.controller.general;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		
		if(m.getMemberType() == MemberType.ROLE_PERSONAL)
			return "redirect:/personal/member/taste";
		else
			return "redirect:/";
	}
	
	@GetMapping("findId")	
	public String findId() {
		return "memberView/findIdForm";
	}	
	
	@PostMapping("findId")
	public String findId(String email, Model m) {
		Member mem = mService.findMemberByEmail(email);
		m.addAttribute("memberId", mem.getMemberId());
		return "memberView/findIdForm";
	}
	
	@GetMapping("resetPw")	
	public String findPw() {
		return "memberView/findPwForm";
	}
	
	@PostMapping("resetPw")
	public String resetPw(Member m) {
		mService.resetPw(m);
		return "redirect:/";
	}
	
}
