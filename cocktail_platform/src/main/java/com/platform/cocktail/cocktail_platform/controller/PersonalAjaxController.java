package com.platform.cocktail.cocktail_platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platform.cocktail.cocktail_platform.api.mail_send.service.EmailService;
import com.platform.cocktail.cocktail_platform.service.MemberService;
import com.platform.cocktail.cocktail_platform.service.StoreService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("personal/member")
public class PersonalAjaxController {
	
	@Autowired
	private MemberService mService;
	
	@Autowired
	private StoreService sService;
	
	@Autowired
	private EmailService emailService;
	
	@GetMapping("checkId")
	public boolean checkId(String memberId) {
		return mService.findMemberById(memberId) == null;
	}
	
	@GetMapping("checkReviewed")
	public boolean checkReviewed(@AuthenticationPrincipal UserDetails user, int orderCode) {
		return sService.findReviewByCodeAndId(user.getUsername(), orderCode) != null;
	}
	
	@PostMapping("emailConfirm")
	public String emailConfirm(String email) throws Exception {
		String confirm = emailService.sendSimpleMessage(email);
		return confirm;
	}
}
