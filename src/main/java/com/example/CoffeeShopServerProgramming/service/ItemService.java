package com.example.CoffeeShopServerProgramming.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.CoffeeShopServerProgramming.model.Item;


@Service
@Transactional
public interface ItemService {
	
//Service for my items	
	
	Iterable<Item> getAllItems();
	
	Item getItem(long id);
	
	Item save(Item item);
}
