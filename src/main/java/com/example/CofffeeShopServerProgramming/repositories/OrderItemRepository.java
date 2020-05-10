package com.example.CofffeeShopServerProgramming.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.CofffeeShopServerProgramming.model.OrderItem;


public interface OrderItemRepository extends CrudRepository<OrderItem, Long>{

}
