package com.example.CoffeeShopServerProgramming.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.CoffeeShopServerProgramming.model.Order;


public interface OrderRepository extends CrudRepository<Order, Long>{

}
