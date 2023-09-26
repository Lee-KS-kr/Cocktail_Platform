package com.platform.cocktail.cocktail_platform.controller.admin;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.platform.cocktail.cocktail_platform.service.TestService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("admin")
public class AdminController {
	@Autowired
	private TestService serv;
	
	@Value("${menulist.csvfile.path}")
	private String csvFilepath;
	
	@GetMapping("home")
	public String adminHome() {
		return "adminView/mainPage";
	}
	
	@GetMapping("insertMenus")
	public String insertMenus() throws IOException {
		log.debug("insert menus");
		serv.insertMenus(csvFilepath);
		return "redirect:/admin/home";
	}
	
	@GetMapping("deleteMenus")
	public String deleteMenus() {
		log.debug("delete menus");
		serv.deleteMenus();
		return "redirect:/admin/home";
	}
}
