package com.example.CoffeeShopServerProgramming.Controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.CoffeeShopServerProgramming.model.Employee;
import com.example.CoffeeShopServerProgramming.model.Rota;
import com.example.CoffeeShopServerProgramming.repositories.EmployeeRepository;
import com.example.CoffeeShopServerProgramming.repositories.RotaRepository;






@Controller
public class EmployeeController {
	@Autowired
	private EmployeeRepository erepository;
	
	@Autowired
	private RotaRepository rrepository;
	
	@Autowired
	private PasswordEncoder crypt;
	
	public void findEmployee() {
		List<Employee> employees = erepository.findBySurname("Barry");
	}
	
	//test for custom error page
	 @RequestMapping("/exception")
	  public void handleRequest() {
	      throw new RuntimeException("test exception");
	  }
	
	 //signup page for customers. Role is automatically set to CUSTOMER and cannot be changed
	@RequestMapping(value="/signup")
	public String addCustomer(Model model) {
		Employee cust = new Employee();
		cust.setRole("CUSTOMER");
		model.addAttribute("employee", cust);
		return "signUp";
	}
	
	//Custom login page
	@RequestMapping(value="/login")
	public String login(Model model) {
		return "login";
	} 
	
	//Home page
	@RequestMapping(value="/")
	public String homePage() {
		return "home";
	} 
	

	//List of employees can only be viewed by MANAGER
	@PreAuthorize("hasAuthority('MANAGER')")
	@RequestMapping(value="/employees")
	public String employeeList(Model model) {
		model.addAttribute("employees", erepository.findAll());
		return "employees";
	}
	
	//Add employees can only be viewed by MANAGER
	@PreAuthorize("hasAuthority('MANAGER')")
	@RequestMapping(value="/add")
	public String addEmployee(Model model) {
		model.addAttribute("employee", new Employee());
		return "addEmployee";
	}
	
	//Save employee
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String save(Employee employee){
		employee.setPasswordHash(crypt.encode(employee.getPasswordHash()));
		erepository.save(employee);
		return "redirect:employees";
	}
	
	//Delete Employee. Can only be done by MANAGER
	@PreAuthorize("hasAuthority('MANAGER')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable("id") Long employeeId, Model model) {
		erepository.deleteById(employeeId);
		return "redirect:../employees";
	}
	
	//Edit Employee. Can only be done by Manager
	@PreAuthorize("hasAuthority('MANAGER')")
	@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
	public String editEmployee(@PathVariable("id") Long employeeId, Model model) {
		model.addAttribute("employee", erepository.findById(employeeId));
		return "editEmployee";
	}
	
	//View work rota. Can only be viewed by MANAGER and EMPLOYEE. CUSTOMER cannot view this
	@PreAuthorize("hasAnyAuthority('MANAGER', 'EMPLOYEE')")
	@RequestMapping(value="/rotas")
	public String rota(Model model) {
		model.addAttribute("rotas", rrepository.findAll());
		return "rota";
	}  
	
	//Add an employee to the rota. Can only be done by MANAGER
	@PreAuthorize("hasAuthority('MANAGER')")
	@RequestMapping(value="/addrota")
	public String addRota(Model model) {
		model.addAttribute("rota", new Rota());
		model.addAttribute("employees", erepository.findAll());
		return "addRota";
	}
	
	//Delete a record from the Rota. Can only be done by MANAGER
	@PreAuthorize("hasAuthority('MANAGER')")
	@RequestMapping(value = "/deleterota/{id}", method = RequestMethod.GET)
	public String deleteRota(@PathVariable("id") Long rotaId, Model model) {
		rrepository.deleteById(rotaId);
		return "redirect:../rota";
	}
	
	//Edit the rota. Can only be done by MANAGER
	@PreAuthorize("hasAuthority('MANAGER')")
	@RequestMapping(value="/editrota/{id}", method = RequestMethod.GET)
	public String editRota(@PathVariable("id") Long rotaId, Model model) {
		model.addAttribute("rota", rrepository.findById(rotaId));
		return "editRota";
	}
	
	//Save a new entry to the rota
	@RequestMapping(value="/saverota", method = RequestMethod.POST)
	public String saveRota(Rota rota){
		rrepository.save(rota);
		return "redirect:rota";
	}

}
