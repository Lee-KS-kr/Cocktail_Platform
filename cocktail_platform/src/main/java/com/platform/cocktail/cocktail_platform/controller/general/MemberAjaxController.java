package com.platform.cocktail.cocktail_platform.controller.general;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platform.cocktail.cocktail_platform.api.mail_send.service.EmailService;
import com.platform.cocktail.cocktail_platform.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("member")
public class MemberAjaxController {
	
	@Autowired
	private MemberService service;
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping("checkId")
	public int checkId(String memberId) {
		log.debug("check id : {}", memberId);
		return service.findMemberById(memberId) == null ? 0 : 1;
	}

	@PostMapping("checkEmail")
	public int checkEmail(String email) {
		log.debug("check email : {}", email);
		return service.findMemberByEmail(email) == null ? 0 : 1;
	}
	
	@PostMapping("emailConfirm")
	public String emailConfirm(String email) throws Exception {
		log.debug("email confirm : {}", email);
		String confirm = emailService.sendSimpleMessage(email);
		return confirm;
	}
	
}
