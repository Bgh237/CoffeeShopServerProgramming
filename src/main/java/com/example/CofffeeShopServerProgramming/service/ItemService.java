package com.example.CofffeeShopServerProgramming.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.CofffeeShopServerProgramming.model.Item;


@Service
@Transactional
public interface ItemService {
	
	Iterable<Item> getAllItems();
	
	Item getItem(long id);
	
	Item save(Item item);
}
