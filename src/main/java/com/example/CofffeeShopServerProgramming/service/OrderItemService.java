package com.example.CofffeeShopServerProgramming.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.CofffeeShopServerProgramming.model.OrderItem;



@Service
@Transactional
public interface OrderItemService {
	
	OrderItem add(OrderItem item);
	
	Iterable<OrderItem> getAllOrderItems();
	
}

	
