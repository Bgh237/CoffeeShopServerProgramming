package com.example.CoffeeShopServerProgramming.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.CoffeeShopServerProgramming.model.Order;
import com.example.CoffeeShopServerProgramming.model.OrderItem;



@Service
@Transactional
public interface OrderItemService {

	//Service for my order items
	
	OrderItem add(OrderItem item);
	
	Iterable<OrderItem> getAllOrderItems();
	
}

	
