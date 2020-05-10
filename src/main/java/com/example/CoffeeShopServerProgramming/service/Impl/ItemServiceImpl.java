package com.example.CoffeeShopServerProgramming.service.Impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.example.CoffeeShopServerProgramming.model.Item;
import com.example.CoffeeShopServerProgramming.repositories.ItemRepository;
import com.example.CoffeeShopServerProgramming.service.ItemService;



@Service
@Transactional
public class ItemServiceImpl implements ItemService {
	@Autowired
    private ItemRepository irepository;
 
    @Override
    public Iterable<Item> getAllItems() {
        return irepository.findAll();
    }
 
    @Override
    public Item getItem(long id) {
        return irepository
          .findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("Item not found"));
    }
 
    @Override
    public Item save(Item item) {
        return irepository.save(item);
    }
}