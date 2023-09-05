package com.platform.cocktail.cocktail_platform.controller.general;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping({"", "/"})
	public String home() {
		return "mainHome";
	}
}