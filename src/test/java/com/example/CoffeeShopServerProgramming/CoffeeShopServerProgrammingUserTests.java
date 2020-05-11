package com.example.CoffeeShopServerProgramming;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.CoffeeShopServerProgramming.model.Employee;
import com.example.CoffeeShopServerProgramming.repositories.EmployeeRepository;



@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CoffeeShopServerProgrammingUserTests {
	//The following code is used to test that I can search add and delete employees
	
	@Autowired
	private EmployeeRepository erepository;
	
	
	@Test
	public void findByEmailShouldReturnUser() {
		List<Employee> employees = erepository.findByEmail("simonb@cafe.com");
		
		assertThat(employees).hasSize(1);
		assertThat(employees.get(0).getSurname()).isEqualTo("Barry");
	}
	
	@Test
	public void addNewEmployee() {
		Employee emp = new Employee("Dave", "Gordon", "daveg@cafe.com", "0445623963", "127 Neverland Road", "EMPLOYEE", 15.00, "dgordon", "$2y$12$YFoOeLsK651.rgEc2M9DQ.BwbhGYdmsLejloVkHiz32kdZjTOi0/y");
		
		erepository.save(emp);
		assertThat(emp.getId()).isNotNull();
		assertThat(emp.getFirstName()).isEqualTo("Dave");
	}
	
	@Test
	public void deleteEmployee() {
		Employee emp = new Employee("Dave", "Gordon", "daveg@cafe.com", "0445623963", "127 Neverland Road", "EMPLOYEE", 15.00, "dgordon", "$2y$12$YFoOeLsK651.rgEc2M9DQ.BwbhGYdmsLejloVkHiz32kdZjTOi0/y");
		erepository.delete(emp);
		assertThat(emp.getId()).isNull();
	}
	
	
}
