package com.platform.cocktail.cocktail_platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("personal")
public class PersonalController {
	@GetMapping("home")
	public String home() {
		return "personalView/mainHome";
	}
}
