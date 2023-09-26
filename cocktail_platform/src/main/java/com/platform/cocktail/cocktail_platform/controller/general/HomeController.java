package com.platform.cocktail.cocktail_platform.controller.general;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.platform.cocktail.cocktail_platform.service.TestService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	
	@GetMapping({"", "/"})
	public String home() {
		return "mainHome";
	}

}
