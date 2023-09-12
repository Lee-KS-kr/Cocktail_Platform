package com.platform.cocktail.cocktail_platform.controller.personal;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platform.cocktail.cocktail_platform.domain.Menu;
import com.platform.cocktail.cocktail_platform.domain.StoreReview;
import com.platform.cocktail.cocktail_platform.service.StoreService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("personal")
public class PersonalAjaxController {
	
	@Autowired
	private StoreService sService;
	
	@GetMapping("member/checkReviewed")
	public boolean checkReviewed(@AuthenticationPrincipal UserDetails user, int orderCode) {
		return sService.findReviewByCodeAndId(user.getUsername(), orderCode) != null;
	}
	
	@GetMapping("store/menuList")
	public ArrayList<Menu> menuList(int storeCode){
		ArrayList<Menu> list = sService.getMenulistByCode(storeCode);
		return list;
	}
	
	@GetMapping("store/reviewList")
	public ArrayList<StoreReview> reviewList(int storeCode){
		ArrayList<StoreReview> list = sService.getReviewlistByCode(storeCode);
		return list;
	}
	
}
