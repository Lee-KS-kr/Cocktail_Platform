package com.platform.cocktail.cocktail_platform.service;

import java.util.ArrayList;

import com.platform.cocktail.cocktail_platform.domain.Menu;
import com.platform.cocktail.cocktail_platform.domain.Order;
import com.platform.cocktail.cocktail_platform.domain.OrderTemp;

public interface OrderService {

	ArrayList<Order> getOrderLists(String memberId);

	Order findOrderByOrdercode(String orderCode);

	ArrayList<OrderTemp> getOrdersByOrdercode(String orderCode);

	ArrayList<Order> getOrderListsByCode(int storeCode);

	int inputOrder(int storeCode, String memberid, String orderCode, int[] menuNum, String[] menuName, int[] price, int[] orderCount);

	Order makeNewOrder(int storeCode, String memberId);

	int finishOrderByCode(String orderCode);

//	ArrayList<Menu> getMenulistByOrdercode(String orderCode);

	ArrayList<OrderTemp> getTemporderlistByCode(int storeCode);

	ArrayList<Menu> getMenusByCode(String orderCode);

}
