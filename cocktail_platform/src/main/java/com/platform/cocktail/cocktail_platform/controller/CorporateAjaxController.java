package com.platform.cocktail.cocktail_platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platform.cocktail.cocktail_platform.api.mail_send.service.EmailService;
import com.platform.cocktail.cocktail_platform.domain.MemberType;
import com.platform.cocktail.cocktail_platform.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("corporate/member")
public class CorporateAjaxController {
	
	@Autowired
	private MemberService service;
	
	@Autowired
	private EmailService emailService;
	
	@GetMapping("checkId")
	public boolean checkId(String memberId) {
		return service.findMemberById(memberId, MemberType.clientMem) == null;
	}
	
	@PostMapping("emailConfirm")
	public String emailConfirm(String email) throws Exception {
		String confirm = emailService.sendSimpleMessage(email);
		return confirm;
	}
	
}
