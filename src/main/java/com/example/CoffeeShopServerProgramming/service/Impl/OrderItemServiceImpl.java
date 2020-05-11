package com.example.CoffeeShopServerProgramming.service.Impl;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CoffeeShopServerProgramming.model.Order;
import com.example.CoffeeShopServerProgramming.model.OrderItem;
import com.example.CoffeeShopServerProgramming.repositories.OrderItemRepository;
import com.example.CoffeeShopServerProgramming.service.OrderItemService;




@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	private OrderItemRepository oirepository;

	
	@Override
	public OrderItem add(OrderItem item) {
		// TODO Auto-generated method stub
		return oirepository.save(item);
	}


	@Override
	public Iterable<OrderItem> getAllOrderItems() {
		// TODO Auto-generated method stub
		return oirepository.findAll();
	}

//new
	@Override
	public void update(OrderItem orderItem) {
		this.oirepository.save(orderItem);
		
	}
	
	
	

}
