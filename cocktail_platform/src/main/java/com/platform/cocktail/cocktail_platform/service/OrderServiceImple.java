package com.platform.cocktail.cocktail_platform.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.platform.cocktail.cocktail_platform.dao.OrderDAO;
import com.platform.cocktail.cocktail_platform.domain.Menu;
import com.platform.cocktail.cocktail_platform.domain.Order;
import com.platform.cocktail.cocktail_platform.domain.OrderState;
import com.platform.cocktail.cocktail_platform.domain.OrderTemp;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class OrderServiceImple implements OrderService {
	@Autowired
	private OrderDAO dao;

	@Override
	public ArrayList<Order> getOrderLists(String memberId) {
		ArrayList<Order> list = dao.getOrderLists(memberId);
		return list;
	}

	@Override
	public Order findOrderByOrdercode(String orderCode) {
		Order o = dao.findOrderByOrdercode(orderCode);
		return o;
	}

	@Override
	public ArrayList<OrderTemp> getOrdersByOrdercode(String orderCode) {
		ArrayList<OrderTemp> list = dao.getOrdersByOrdercode(orderCode);
		return list;
	}

	@Override
	public ArrayList<Order> getOrderListsByCode(int storeCode) {
		ArrayList<Order> list = dao.getOrderListsByCode(storeCode);
		return list;
	}

	@Override
	public int inputOrder(int storeCode, String memberid, String orderCode, int[] menuNum, String[] menuName, int[] price, int[] orderCount) {
		Order o = null;
		if(orderCode != null)
			o = findOrderByOrdercode(orderCode);
		else 
			o = makeNewOrder(storeCode, memberid);
		
		ArrayList<OrderTemp> list = makeTempOrderList(o, menuNum, menuName, price, orderCount);
		
		dao.insertTemporderList(list);
		int n = dao.updateOrder(o);
		
		return n;
	}

	@Override
	public Order makeNewOrder(int storeCode, String memberId) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("storeCode", storeCode);
		map.put("memberid", memberId);
		
		String code = dao.makeNewOrder(map);
		Order o = findOrderByOrdercode(code);
		return o;
	}
	
	@Override
	public int finishOrderByCode(String orderCode) {
		Order o = findOrderByOrdercode(orderCode);
		o.setOrderState(OrderState.finished);
		int n = dao.updateOrder(o);
		
		return n;
	}

	@Override
	public ArrayList<Menu> getMenulistByOrdercode(String orderCode) {
		ArrayList<Menu> list = dao.getMenulistByOrdercode(orderCode);
		return list;
	}

	public ArrayList<OrderTemp> makeTempOrderList(Order o, int[] menuNum, String[] menuName, int[] price, int[] orderCount){
		StringBuilder menuList = new StringBuilder();
		int totalPrice = 0;
		
		ArrayList<OrderTemp> list = new ArrayList<>();
		for (int i = 0; i < menuNum.length; i++) {
			OrderTemp t = 
				OrderTemp.builder()
					.orderCode(o.getOrderCode())
					.menuNum(menuNum[i])
					.menuName(menuName[i])
					.pricePerOne(price[i])
					.orderCount(orderCount[i])
					.build();
			
			menuList.append(t.getMenuName());
			totalPrice += t.getPricePerOne() * t.getOrderCount();
			list.add(t);
		}
		
		o.setMenuList(menuList.toString());
		o.setTotalPrice(totalPrice);
		
		return list;
	}

	@Override
	public ArrayList<OrderTemp> getTemporderlistByCode(int storeCode) {
		ArrayList<OrderTemp> list = dao.getTemporderlistByCode(storeCode);
		return list;
	}

}
