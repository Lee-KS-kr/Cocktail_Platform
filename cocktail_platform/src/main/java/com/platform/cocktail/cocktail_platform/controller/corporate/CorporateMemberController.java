package com.platform.cocktail.cocktail_platform.controller.corporate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@GetMapping("mypage")
	public String mypage(@AuthenticationPrincipal UserDetails user) {
		return "corporateView/corpMyPage";
	}
	
	@GetMapping("insertStoreinfo")
	public String insertStoreinfo(String memberId) {
		return "corporateView/storeInfoForm";
	}
	
	@PostMapping("insertStoreinfo")
	public String insertStoreinfo(StoreInfo info) {
		sService.insertStoreinfo(info);
		return "redirect:/";
	}
}
