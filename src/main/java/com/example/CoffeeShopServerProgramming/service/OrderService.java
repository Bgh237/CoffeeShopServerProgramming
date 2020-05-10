package com.example.CoffeeShopServerProgramming.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.CoffeeShopServerProgramming.model.Order;



@Service
@Transactional
public interface OrderService {
	
	Iterable<Order> getAllOrders();
	
	Order create(Order order);
	
	void update(Order order);
	
	void delete(Order order);

}