package com.example.CofffeeShopServerProgramming.service.Impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CofffeeShopServerProgramming.model.Order;
import com.example.CofffeeShopServerProgramming.repositories.OrderRepository;
import com.example.CofffeeShopServerProgramming.service.OrderService;



@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderRepository orepository;
	
	@Override
	public Iterable<Order> getAllOrders() {
		return this.orepository.findAll();
	}

	@Override
	public Order create(Order order) {
		order.setDateCreated(LocalDate.now());
		return this.orepository.save(order);
	}

	@Override
	public void update(Order order) {
		this.orepository.save(order);		
	}
	
	@Override
	public void delete(Order order) {
		this.orepository.delete(order);		
	}

}
