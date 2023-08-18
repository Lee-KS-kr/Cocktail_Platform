package com.platform.cocktail.cocktail_platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("personal")
public class PersonalController {
	@GetMapping("home")
	public String home() {
		return "personalView/mainHome";
	}
	
	@GetMapping("login")
	public String login() {
		return "";
	}
	
	@GetMapping("join")
	public String join() {
		return "";
	}
}
