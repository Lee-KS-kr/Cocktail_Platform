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
	@Autowired
	private TestService serv;
	@Value("${menulist.csvfile.path}")
	private String csvFilepath;
	
	@GetMapping({"", "/"})
	public String home() {
		return "mainHome";
	}
	
	@GetMapping("insertMenus")
	public String insertMenus() throws IOException {
		log.debug("insert menus");
		serv.insertMenus(csvFilepath);
		return "redirect:/";
	}
	
	@GetMapping("deleteMenus")
	public String deleteMenus() {
		log.debug("delete menus");
		serv.deleteMenus();
		return "redirect:/";
	}
}
