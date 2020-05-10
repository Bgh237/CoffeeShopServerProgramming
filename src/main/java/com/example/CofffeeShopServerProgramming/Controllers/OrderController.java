package com.example.CofffeeShopServerProgramming.Controllers;

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

import com.example.CofffeeShopServerProgramming.model.Item;
import com.example.CofffeeShopServerProgramming.model.Order;
import com.example.CofffeeShopServerProgramming.model.OrderItem;
import com.example.CofffeeShopServerProgramming.service.ItemService;
import com.example.CofffeeShopServerProgramming.service.OrderItemService;
import com.example.CofffeeShopServerProgramming.service.OrderService;



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
	
	@RequestMapping(value = "/addtoorder/{id}", method = RequestMethod.GET)
	public String addToOrder(@PathVariable("id") Long itemId) {
		Item item = iservice.getItem(itemId);
		
		OrderItem itemRow = new OrderItem(order, item, 1);
		
		orderItems.add(itemRow);
		oservice.create(order);
		oiservice.add(itemRow);
		
		
		/*if(oservice.getAllOrders() == null) {
			oservice.create(order);
		} else {
			oservice.update(order);
		}*/
		return "redirect:../menu";
	}
	
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
	
	@GetMapping(value = "/checkout")
	public String checkout(Model model) {
		
		model.addAttribute("orderNo", order.getOrderId());
		oservice.update(order);
		order = new Order();
		orderItems.clear();
		return "checkout";
	}
	
	@GetMapping(value = "/cancel")
	public String cancel(Model model) {
		orderItems.clear();
		return "home";
	}
	

}