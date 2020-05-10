package com.example.CofffeeShopServerProgramming.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.CofffeeShopServerProgramming.model.Order;


public interface OrderRepository extends CrudRepository<Order, Long>{

}
