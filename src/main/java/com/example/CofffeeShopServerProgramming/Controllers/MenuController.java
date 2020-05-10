package com.example.CofffeeShopServerProgramming.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.CofffeeShopServerProgramming.model.Item;
import com.example.CofffeeShopServerProgramming.model.Order;
import com.example.CofffeeShopServerProgramming.repositories.ItemCategoryRepository;
import com.example.CofffeeShopServerProgramming.repositories.ItemRepository;



@Controller
public class MenuController {
	@Autowired
	private ItemRepository irepository;
	
	@Autowired
	private ItemCategoryRepository icrepository;
	
	@RequestMapping(value="/menu")
	public String menu(Model model) {
		Order order = new Order();
		model.addAttribute("items", irepository.findAll());
		model.addAttribute("order", order);
		return "menu";
	}  
	
	@RequestMapping(value="/additem")
	public String addFood(Model model) {
		model.addAttribute("item", new Item());
		model.addAttribute("categories", icrepository.findAll());
		return "addItem";
	}
	
	@RequestMapping(value="/saveitem", method = RequestMethod.POST)
	public String saveFood(Item item){
		irepository.save(item);
		return "redirect:menu";
	}
	
	@PreAuthorize("hasAuthority('MANAGER')")
	@RequestMapping(value = "/deleteitem/{id}", method = RequestMethod.GET)
	public String deleteItem(@PathVariable("id") Long itemId, Model model) {
		irepository.deleteById(itemId);
		return "redirect:../menu";
	}
	
	@PreAuthorize("hasAuthority('MANAGER')")
	@RequestMapping(value="/edititem/{id}", method = RequestMethod.GET)
	public String editFood(@PathVariable("id") Long itemId, Model model) {
		model.addAttribute("item", irepository.findById(itemId));
		model.addAttribute("categories", icrepository.findAll());
		return "editItem";
	}
}
