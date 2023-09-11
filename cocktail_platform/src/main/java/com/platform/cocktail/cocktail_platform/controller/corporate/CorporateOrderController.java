package com.platform.cocktail.cocktail_platform.controller.corporate;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.platform.cocktail.cocktail_platform.domain.Member;
import com.platform.cocktail.cocktail_platform.domain.Menu;
import com.platform.cocktail.cocktail_platform.domain.Order;
import com.platform.cocktail.cocktail_platform.domain.OrderTemp;
import com.platform.cocktail.cocktail_platform.service.OrderService;
import com.platform.cocktail.cocktail_platform.service.StoreService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("corporate/order")
public class CorporateOrderController {
	@Autowired
	private StoreService sService;
	@Autowired
	private OrderService oService;
	
	@GetMapping("login")
	public String login() {
		return "corporateView/orderMain";
	}
	
	@PostMapping("login")
	public String login(int storeCode, Member mem, Model m) {
		Order o = oService.makeNewOrder(storeCode, mem.getMemberId());
		m.addAttribute("order", o);
		return "corporateView/order";
	}
	
	@GetMapping("menu")
	public ArrayList<Menu> menu(int storeCode, Model m) {
		ArrayList<Menu> menuList = sService.getMenulistByCode(storeCode);
		return menuList;
	}
	
	@GetMapping("menuInfo")
	public String menuInfo(int menuNum, Model m) {
		Menu menu = sService.getMenuInfoByNum(menuNum);
		m.addAttribute("menu", menu);
		return "";
	}
	
	@GetMapping("cart")
	public String cart(Model m,
			@CookieValue(name="cart", defaultValue="0") String cart) throws Exception {
		cart = URLDecoder.decode(cart, "UTF-8");
		if(cart.equals("0")) {
			m.addAttribute("err", "카트에 담긴 음식이 없습니다.");
		} else {
			String[] menus = cart.split(",");
			String[][] carts = new String[menus.length][2];
			ArrayList<Menu> menuList = sService.getMenulistByNum(menus);
			
			for(int i = 0; i < menus.length; i++)
				carts[i] = menus[i].split("_");
			
			m.addAttribute("menuList", menuList);
			m.addAttribute("carts", carts);
		}
		
		return "";
	}
	
	@GetMapping("addToCart")
	public void addToCart(int storeCode, String memberId, int menuNum, int orderCount,
						@CookieValue(name="cart", defaultValue="0") String cart,
						HttpServletResponse res) throws Exception {
		cart = URLDecoder.decode(cart, "UTF-8");

		findCookie:
		if(!cart.equals("0")) {
			String[] orders = cart.split(",");
			for(int i = 0; i < orders.length; i++) {
				String[] ms = orders[i].split("_");
				if(ms[0].equals(String.valueOf(menuNum))) {
					int count = Integer.parseInt(ms[1]) + orderCount;
					ms[1] = String.valueOf(count);
					orders[i] = ms[0] + "_" + ms[1];
					cart = StringUtils.join(orders);
					break findCookie;
				}
			}
			
			String temp = menuNum + "_" + orderCount;
			cart += temp;
			
		} else {
			String temp = menuNum + "_" + orderCount;
			cart += temp;
		}
		
		Cookie cookie1 = new Cookie("cart", cart);
		cookie1.setMaxAge(24 * 60 * 60);
		res.addCookie(cookie1);
	}
	
	@PostMapping("orderMenu")
	public String orderMenu(int storeCode, String memberId, String orderCode, 
						@CookieValue(name="cart", defaultValue="0") String cart,
						HttpServletResponse res, Model m) throws Exception {
		
		cart = URLDecoder.decode(cart, "UTF-8");
		if(cart.equals("0")) {
			m.addAttribute("err", "카트에 담긴 음식이 없습니다.");
		} else {
			String[] menus = cart.split(",");
			ArrayList<Menu> menuList = sService.getMenulistByNum(menus);
			
		}
		
		return "";
	}
	
	@GetMapping("callStaff")
	public String callStaff(String orderCode) {
		return "";
	}
	
	@PostMapping("payment")
	public String payment(int storeCode, String memberId, String orderCode) {
		oService.finishOrderByCode(orderCode);
		return "";
	}
}
