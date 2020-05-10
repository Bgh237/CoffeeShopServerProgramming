package com.example.CofffeeShopServerProgramming.Controllers;

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

import com.example.CofffeeShopServerProgramming.model.Employee;
import com.example.CofffeeShopServerProgramming.model.Rota;
import com.example.CofffeeShopServerProgramming.repositories.EmployeeRepository;
import com.example.CofffeeShopServerProgramming.repositories.RotaRepository;






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
	
	@RequestMapping(value="/signup")
	public String addCustomer(Model model) {
		Employee cust = new Employee();
		cust.setRole("CUSTOMER");
		model.addAttribute("employee", cust);
		return "signUp";
	}
	
	@RequestMapping(value="/login")
	public String login(Model model) {
		return "login";
	} 
	
	@RequestMapping(value="/")
	public String homePage() {
		return "home";
	} 
	

	
	@PreAuthorize("hasAuthority('MANAGER')")
	@RequestMapping(value="/employees")
	public String employeeList(Model model) {
		model.addAttribute("employees", erepository.findAll());
		return "employees";
	}
	
	@PreAuthorize("hasAuthority('MANAGER')")
	@RequestMapping(value="/add")
	public String addEmployee(Model model) {
		model.addAttribute("employee", new Employee());
		return "addEmployee";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String save(Employee employee){
		employee.setPasswordHash(crypt.encode(employee.getPasswordHash()));
		erepository.save(employee);
		return "redirect:employees";
	}
	
	@PreAuthorize("hasAuthority('MANAGER')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable("id") Long employeeId, Model model) {
		erepository.deleteById(employeeId);
		return "redirect:../employees";
	}
	
	@PreAuthorize("hasAuthority('MANAGER')")
	@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
	public String editEmployee(@PathVariable("id") Long employeeId, Model model) {
		model.addAttribute("employee", erepository.findById(employeeId));
		return "editEmployee";
	}
	
	@PreAuthorize("hasAnyAuthority('MANAGER', 'EMPLOYEE')")
	@RequestMapping(value="/rotas")
	public String rota(Model model) {
		model.addAttribute("rotas", rrepository.findAll());
		return "rota";
	}  
	
	@PreAuthorize("hasAuthority('MANAGER')")
	@RequestMapping(value="/addrota")
	public String addRota(Model model) {
		model.addAttribute("rota", new Rota());
		model.addAttribute("employees", erepository.findAll());
		return "addRota";
	}
	
	@PreAuthorize("hasAuthority('MANAGER')")
	@RequestMapping(value = "/deleterota/{id}", method = RequestMethod.GET)
	public String deleteRota(@PathVariable("id") Long rotaId, Model model) {
		rrepository.deleteById(rotaId);
		return "redirect:../rota";
	}
	
	@PreAuthorize("hasAuthority('MANAGER')")
	@RequestMapping(value="/editrota/{id}", method = RequestMethod.GET)
	public String editRota(@PathVariable("id") Long rotaId, Model model) {
		model.addAttribute("rota", rrepository.findById(rotaId));
		return "editRota";
	}
	
	@RequestMapping(value="/saverota", method = RequestMethod.POST)
	public String saveRota(Rota rota){
		rrepository.save(rota);
		return "redirect:rota";
	}

}
