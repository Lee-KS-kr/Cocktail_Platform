package com.platform.cocktail.cocktail_platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("corporate")
public class CorporateController {
	@GetMapping("home")
	public String home() {
		return "corporateView/mainHome";
	}
}
