package com.example.CoffeeShopServerProgramming.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.CoffeeShopServerProgramming.model.Employee;
import com.example.CoffeeShopServerProgramming.model.Item;
import com.example.CoffeeShopServerProgramming.model.Order;
import com.example.CoffeeShopServerProgramming.model.Rota;
import com.example.CoffeeShopServerProgramming.repositories.EmployeeRepository;
import com.example.CoffeeShopServerProgramming.repositories.ItemRepository;
import com.example.CoffeeShopServerProgramming.repositories.OrderRepository;
import com.example.CoffeeShopServerProgramming.repositories.RotaRepository;



@RestController
public class RESTController {

	@Autowired
	private EmployeeRepository erepo;
	
	@Autowired 
	private ItemRepository irepo;
	
	@Autowired
	private OrderRepository orepo;
	
	@Autowired
	private RotaRepository rrepo;
	
	@RequestMapping(value="/employee", method= RequestMethod.GET)
	public @ResponseBody List<Employee> employeeRest() {
		return (List<Employee>) erepo.findAll();
	}
	
	@RequestMapping(value="/employee/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Employee> findEmployeeRest(@PathVariable("id") Long employeeId) {
		return erepo.findById(employeeId);
	}
	
	@RequestMapping(value="/item", method= RequestMethod.GET)
	public @ResponseBody List<Item> itemRest() {
		return (List<Item>) irepo.findAll();
	}
	
	@RequestMapping(value="/item/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Item> findItemRest(@PathVariable("id") Long itemId) {
		return irepo.findById(itemId);
	}
	
	@RequestMapping(value="/rota", method= RequestMethod.GET)
	public @ResponseBody List<Rota> rotaRest() {
		return (List<Rota>) rrepo.findAll();
	}
	
	@RequestMapping(value="/order", method= RequestMethod.GET)
	public @ResponseBody List<Order> orderRest() {
		return (List<Order>) orepo.findAll();
	}
	
	@RequestMapping(value="/order/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Order> findOrderRest(@PathVariable("id") Long orderId) {
		return orepo.findById(orderId);
	}

}
