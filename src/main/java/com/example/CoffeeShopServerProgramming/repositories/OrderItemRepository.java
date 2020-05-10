package com.example.CoffeeShopServerProgramming.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.CoffeeShopServerProgramming.model.OrderItem;


public interface OrderItemRepository extends CrudRepository<OrderItem, Long>{

}
