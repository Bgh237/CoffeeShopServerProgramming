package com.example.CoffeeShopServerProgramming.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.CoffeeShopServerProgramming.model.Order;
import com.example.CoffeeShopServerProgramming.model.OrderItem;



@Service
@Transactional
public interface OrderItemService {
	
	OrderItem add(OrderItem item);
	
	Iterable<OrderItem> getAllOrderItems();
	
	void update(OrderItem orderItem);
	
}

	
