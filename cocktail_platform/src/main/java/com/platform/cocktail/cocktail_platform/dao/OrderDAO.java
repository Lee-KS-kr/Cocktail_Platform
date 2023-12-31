package com.platform.cocktail.cocktail_platform.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.platform.cocktail.cocktail_platform.domain.Menu;
import com.platform.cocktail.cocktail_platform.domain.Order;
import com.platform.cocktail.cocktail_platform.domain.OrderTemp;

@Mapper
public interface OrderDAO {

	ArrayList<Order> getOrderLists(String memberId);

	Order findOrderByOrdercode(String orderCode);

	ArrayList<OrderTemp> getOrdersByOrdercode(String orderCode);

	ArrayList<Order> getOrderListsByCode(int storeCode);

	int makeNewOrder(Order o);
	
	int insertTemporderList(OrderTemp ot);
	
	int updateOrder(Order o);

	ArrayList<Menu> getMenulistByOrdercode(String orderCode);

	ArrayList<OrderTemp> getTemporderlistByCode(int storeCode);

	Menu findMenuByNum(int menuNum);

}
