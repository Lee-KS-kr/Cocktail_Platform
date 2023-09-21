package com.platform.cocktail.cocktail_platform.controller.corporate;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.platform.cocktail.cocktail_platform.domain.Member;
import com.platform.cocktail.cocktail_platform.domain.Menu;
import com.platform.cocktail.cocktail_platform.domain.Order;
import com.platform.cocktail.cocktail_platform.domain.StoreInfo;
import com.platform.cocktail.cocktail_platform.service.MemberService;
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
	@Autowired
	private MemberService mService;
	private Member loginMember;
	
	@GetMapping("login")
	public String login(@AuthenticationPrincipal UserDetails user, Model m) {
		StoreInfo store = sService.getStoreById(user.getUsername());
		m.addAttribute("store", store);
		return "corporateView/orderMain";
	}
	
	@PostMapping("login")
	public String login(@AuthenticationPrincipal UserDetails user, String memberId, String memberPw, Model m) {
		StoreInfo store = sService.getStoreById(user.getUsername());
		
		log.debug("{}, {}, {}", store.getStoreCode(), memberId, memberPw);
		if(!memberId.isEmpty() && !memberPw.isEmpty()) {
			Member mem = mService.loginToOrder(memberId, memberPw);
			if(mem ==  null) {
				m.addAttribute("err", "아이디와 비밀번호를 다시 확인해주세요.");
				return "redirect:/corporate/order/login";
			}
			
			this.loginMember = mem;
			log.debug("this member : {}", this.loginMember);
		}
		
		Order o = oService.makeNewOrder(store.getStoreCode(), memberId);
		log.debug("order : {}", o);
		m.addAttribute("order", o);
		return "corporateView/order";
	}
	
	@GetMapping("menu")
	public String menu() {
		return "corporateView/order";
	}
	
	@ResponseBody
	@PostMapping("menu")
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
		
		return "corporateView/";
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
			int[] menuNum = new int[menus.length];
			int[] price = new int[menus.length]; 
			int[] orderCount = new int[menus.length]; 
			String[] menuName = new String[menus.length];

			ArrayList<Menu> menuList = sService.getMenulistByNum(menus);
			for(int i = 0; i < menus.length; i++) {
				String[] str = menus[i].split("_");
				int num = Integer.parseInt(str[0]);
				menuNum[i] = num;
				orderCount[i] = Integer.parseInt(str[1]);
				
				menuName[i] = menuList.stream().filter(x -> x.getMenuNum() == num).collect(Collectors.toList()).get(0).getMenuName();
				price[i] = menuList.stream().filter(x -> x.getMenuNum() == num).collect(Collectors.toList()).get(0).getPrice();
			}
			
			oService.inputOrder(storeCode, memberId, orderCode, menuNum, menuName, price, orderCount);
			
			cart = "0";
			Cookie cookie1 = new Cookie("cart", cart);
			cookie1.setMaxAge(0);
			res.addCookie(cookie1);
		}
		
		return "corporateView/";
	}
	
	@GetMapping("callStaff")
	public String callStaff(String orderCode) {
		return "corporateView/";
	}
	
	@PostMapping("payment")
	public String payment(int storeCode, String memberId, String orderCode) {
		oService.finishOrderByCode(orderCode);
		return "corporateView/";
	}
}
