package com.platform.cocktail.cocktail_platform.controller.personal;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platform.cocktail.cocktail_platform.domain.Cocktails;
import com.platform.cocktail.cocktail_platform.domain.Ingredients;
import com.platform.cocktail.cocktail_platform.domain.Menu;
import com.platform.cocktail.cocktail_platform.domain.StoreInfo;
import com.platform.cocktail.cocktail_platform.domain.StoreReview;
import com.platform.cocktail.cocktail_platform.domain.Taste;
import com.platform.cocktail.cocktail_platform.service.CocktailService;
import com.platform.cocktail.cocktail_platform.service.MemberService;
import com.platform.cocktail.cocktail_platform.service.RecommendService;
import com.platform.cocktail.cocktail_platform.service.StoreService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("personal")
public class PersonalAjaxController {
	@Autowired
	private MemberService mService;
	
	@Autowired
	private StoreService sService;
	
	@Autowired
	private CocktailService cService;
	
	@Autowired
	private RecommendService rService;
	
	@GetMapping("member/checkReviewed")
	public boolean checkReviewed(@AuthenticationPrincipal UserDetails user, int orderCode) {
		return sService.findReviewByCodeAndId(user.getUsername(), orderCode) != null;
	}
	
	@GetMapping("store/menuList")
	public ArrayList<Menu> menuList(int storeCode){
		ArrayList<Menu> list = sService.getMenulistByCode(storeCode);
		log.debug("{}", list);
		return list;
	}
	
	@GetMapping("store/reviewList")
	public ArrayList<StoreReview> reviewList(int storeCode){
		ArrayList<StoreReview> list = sService.getReviewlistByCode(storeCode);
		return list;
	}
	
	@GetMapping("searchStore")
	public ArrayList<StoreInfo> searchStore(String searchWord){
		log.debug("category : {} searchword : {} ", searchWord);
		ArrayList<StoreInfo> list = cService.searchStore(searchWord);
		log.debug("list : {}", list);
		return list;
	}
	
	@GetMapping("searchCocktail")
	public ArrayList<Cocktails> searchCocktail(String searchWord){
		log.debug("category : {} searchword : {} ", searchWord);
		ArrayList<Cocktails> list = cService.searchCocktail(searchWord);
		log.debug("list : {}", list);
		return list;
	}
	
	@GetMapping("searchIngredient")
	public ArrayList<Ingredients> searchIngredient(String searchWord){
		log.debug("category : {} searchword : {} ", searchWord);
		ArrayList<Ingredients> list = cService.searchIngredient(searchWord);
		log.debug("list : {}", list);
		return list;
	}
	
	@GetMapping("getBestCocktails")
	public ArrayList<Menu> getBestCocktails(){
		ArrayList<Menu> list = rService.getTop3Cocktails();
		getStoreNames(list);
		
		return list;
	}
	
	@GetMapping("customizedRecommendation")
	public ArrayList<Menu> customizedRecommendation(@AuthenticationPrincipal UserDetails user){
		Taste t = mService.findTasteById(user.getUsername());
		ArrayList<Menu> list = rService.CustomizedRecommendation(t);
		getStoreNames(list);
		
		return list;
	}
	
	@GetMapping("weatherRecommend")
	public ArrayList<Menu> weatherRecommend(String filter) {
		ArrayList<Menu> list = rService.weatherRecommend(filter);
		getStoreNames(list);
		
		return list;
	}
	
	@GetMapping("ageRecommend")
	public ArrayList<Menu>  ageRecommend(String filter) {
		ArrayList<Menu> list = rService.ageRecommend(filter);
		getStoreNames(list);
		
		return list;
	}
	
	@GetMapping("companionRecommend")
	public ArrayList<Menu>  companionRecommend(String filter) {
		ArrayList<Menu> list = rService.companionRecommend(filter);
		getStoreNames(list);
		
		return list;
	}
	
	@GetMapping("eventRecommend")
	public ArrayList<Menu> eventRecommend(String filter) {
		ArrayList<Menu> list = rService.eventRecommend(filter);
		getStoreNames(list);
		
		return list;
	}
	
	public ArrayList<Menu> getStoreNames(ArrayList<Menu> list){
		for (Menu m : list) 
			m.setStoreName(sService.getStoreinfoByCode(m.getStoreCode()).getStoreName());
		
		return list;
	}
}
