package com.platform.cocktail.cocktail_platform.controller.corporate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platform.cocktail.cocktail_platform.api.mail_send.service.EmailService;
import com.platform.cocktail.cocktail_platform.domain.Member;
import com.platform.cocktail.cocktail_platform.domain.StoreInfo;
import com.platform.cocktail.cocktail_platform.service.MemberService;
import com.platform.cocktail.cocktail_platform.service.StoreService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("corporate/member")
public class CorporateAjaxController {
	
	@Autowired
	private MemberService service;
	
	@Autowired
	private StoreService sService;

	@GetMapping("getPrivacy")
	public Member getPrivacy(@AuthenticationPrincipal UserDetails user) {
		Member m = service.findMemberById(user.getUsername());
		m.setMemberId(user.getUsername());
		return m;
	}
	
	@PostMapping("editPrivacyInfo")
	public void editPrivacyInfo(Member m, @AuthenticationPrincipal UserDetails user) {
		m.setMemberId(user.getUsername());
		service.updateMember(m);
	}
	
	@GetMapping("getStoreinfo")
	public StoreInfo editStorepage(@AuthenticationPrincipal UserDetails user) {
		StoreInfo store = sService.getStoreById(user.getUsername());
		return store;
	}
	
	@PostMapping("editStorepage")
	public void editStorepage(@AuthenticationPrincipal UserDetails user, StoreInfo store) {
		store.setMemberId(user.getUsername());
		sService.updateStoreinfo(store);
	}
	
}
