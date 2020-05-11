package com.example.CoffeeShopServerProgramming.Controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.CoffeeShopServerProgramming.model.Item;
import com.example.CoffeeShopServerProgramming.model.Order;
import com.example.CoffeeShopServerProgramming.model.OrderItem;
import com.example.CoffeeShopServerProgramming.service.ItemService;
import com.example.CoffeeShopServerProgramming.service.OrderItemService;
import com.example.CoffeeShopServerProgramming.service.OrderService;



@Controller
public class OrderController {
	private final OrderService oservice;
	private final ItemService iservice;
	private final OrderItemService oiservice;
	
	@Autowired
	public OrderController(OrderService oservice, ItemService iservice, OrderItemService oiservice) {
		this.oiservice = oiservice;
		this.iservice = iservice;
		this.oservice = oservice;
	}
	
	private Order order = new Order();
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();
	
	//Add an item to an order
	@RequestMapping(value = "/addtoorder/{id}", method = RequestMethod.GET)
	public String addToOrder(@PathVariable("id") Long itemId) {
		order.setDateCreated(LocalDate.now());
		//Find the item by ID
		Item item = iservice.getItem(itemId);
		
		//Create an order Item
		OrderItem itemRow = new OrderItem(order, item, 1);
		
		//Add the order item to the list
		orderItems.add(itemRow);
		
		//Create the order in the service
		oservice.create(order);
		
		//Add the orderItem to the orderItem service
		oiservice.add(itemRow);
		
		
		/*if(oservice.getAllOrders() == null) {
			oservice.create(order);
		} else {
			oservice.update(order);
		}*/
		return "redirect:../menu";
	}
	
	//View the cart to see what has been added to the order
	@GetMapping(value = "/cart")
	public String goToCart(Model model) {
		
		oservice.create(order);
		order.setOrderItems(orderItems);
		model.addAttribute("orderitems", order.getOrderItems());
		model.addAttribute("total", order.getTotalOrderPrice());
		model.addAttribute("quantity", order.getNumberOfProducts());
		model.addAttribute("orders", oservice.getAllOrders());
		
		return "cart";
	}
	
	//Confirm the order
	@GetMapping(value = "/checkout")
	public String checkout(Model model) {
		//Saves each item in the orderItems list to the orderItems repository
		for(OrderItem item : orderItems) {
			oiservice.update(item);
		}
		model.addAttribute("orderNo", order.getOrderId());
		oservice.update(order);
		order = new Order();
		orderItems.clear();
		return "checkout";
	}
	
	//Cancel the order
	@GetMapping(value = "/cancel")
	public String cancel(Model model) {
		orderItems.clear();
		return "home";
	}
	

}