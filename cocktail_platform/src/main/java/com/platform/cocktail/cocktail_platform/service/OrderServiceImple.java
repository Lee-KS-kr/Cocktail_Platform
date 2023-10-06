package com.platform.cocktail.cocktail_platform.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.tomcat.util.buf.StringUtils;
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
		
		for (OrderTemp ot : list) 
			dao.insertTemporderList(ot);
				
		String str = "";
		int totalprice = 0;
		for (int i = 0; i < orderCount.length; i++) {
			str += menuName[i];
			if(i != orderCount.length - 1)
				str += ",";
			totalprice += price[i] * orderCount[i];
		}
		o.setMenuList(str);
		o.setTotalPrice(totalprice);
		int n = dao.updateOrder(o);
		
		return n;
	}

	@Override
	public Order makeNewOrder(int storeCode, String memberId) {
		Order o = new Order();
		o.setStoreCode(storeCode);
		o.setMemberId(memberId);
		
		dao.makeNewOrder(o);
		o = findOrderByOrdercode(o.getOrderCode());
		log.debug("{}", o);
		return o;
	}
	
	@Override
	public int finishOrderByCode(String orderCode) {
		Order o = findOrderByOrdercode(orderCode);
		log.debug("order {}", o);
		o.setOrderState(OrderState.finished);
		int n = dao.updateOrder(o);
		
		return n;
	}

	public ArrayList<OrderTemp> makeTempOrderList(Order o, int[] menuNum, String[] menuName, int[] price, int[] orderCount){
		StringBuilder menuList = new StringBuilder();
		int totalPrice = 0;
		
		ArrayList<OrderTemp> list = new ArrayList<>();
		for (int i = 0; i < menuNum.length; i++) {
			OrderTemp t = 
				OrderTemp.builder()
					.orderCode(o.getOrderCode())
					.storeCode(o.getStoreCode())
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

	@Override
	public ArrayList<Menu> getMenusByCode(String orderCode) {
		ArrayList<Menu> list = new ArrayList<>();
		ArrayList<OrderTemp> tempList = dao.getOrdersByOrdercode(orderCode);
		log.debug("{}", tempList);
		for (OrderTemp t : tempList) {
			Menu m = dao.findMenuByNum(t.getMenuNum());
			list.add(m);
			log.debug("{}", m);
		}
		log.debug("{}", list);
		
		return list;
	}

}
