package com.platform.cocktail.cocktail_platform.controller.personal;

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
	private StoreService sService;
	
	@GetMapping("checkReviewed")
	public boolean checkReviewed(@AuthenticationPrincipal UserDetails user, int orderCode) {
		return sService.findReviewByCodeAndId(user.getUsername(), orderCode) != null;
	}
}
