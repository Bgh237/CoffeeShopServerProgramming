package com.example.CofffeeShopServerProgramming.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.CofffeeShopServerProgramming.model.Item;




public interface ItemRepository extends CrudRepository<Item, Long> {
	List<Item> findByName(String name);

}
