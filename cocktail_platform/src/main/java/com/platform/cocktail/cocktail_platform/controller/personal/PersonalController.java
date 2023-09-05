package com.platform.cocktail.cocktail_platform.controller.personal;

import java.util.ArrayList;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.platform.cocktail.cocktail_platform.domain.Cocktails;
import com.platform.cocktail.cocktail_platform.domain.Ingredients;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("personal")
public class PersonalController {
	@GetMapping("home")
	public String home() {
		return "personalView/mainHome";
	}
	
	@GetMapping("search")
	public String search(String searchFilter, String searchWord, Model m) {
		ArrayList<Cocktails> cocktailList = new ArrayList<>();
		ArrayList<Ingredients> ingredientList = new ArrayList<>();
		m.addAttribute("cocktailList", cocktailList);
		m.addAttribute("ingredientList", ingredientList);
		return "";
	}
	
	@GetMapping("cocktails")
	public String cocktails(@AuthenticationPrincipal UserDetails user, Model m) {
		// 추천 알고리즘 구현 이후
		ArrayList<Cocktails> cocktailList = new ArrayList<>();
		m.addAttribute("cocktailList", cocktailList);
		return "";
	}
}
