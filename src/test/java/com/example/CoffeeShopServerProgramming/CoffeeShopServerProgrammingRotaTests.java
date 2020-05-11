package com.example.CoffeeShopServerProgramming;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.CoffeeShopServerProgramming.model.Employee;
import com.example.CoffeeShopServerProgramming.model.Rota;
import com.example.CoffeeShopServerProgramming.repositories.RotaRepository;



@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CoffeeShopServerProgrammingRotaTests {
	//The following code is used to test that I can add and delete from the rota
	@Autowired
	private RotaRepository rrepository;
	
	@Test
	public void addNewRota() {
		Employee emp1 =  new Employee("Dave", "Gordon", "daveg@cafe.com", "0445623963", "127 Neverland Road", "EMPLOYEE", 15.00, "dgordon", "$2y$12$YFoOeLsK651.rgEc2M9DQ.BwbhGYdmsLejloVkHiz32kdZjTOi0/y");
		Rota r1 = new Rota(emp1, "8-5", "8-5", "10-7", "8-5", "10-7", "7-2", "7-2");
		
		rrepository.save(r1);
		assertThat(r1.getId()).isNotNull();
		assertThat(r1.getEmployee().getFirstName()).isEqualTo("Dave");
		assertThat(r1.getMon()).isEqualTo("8-5");
	}
	
	@Test
	public void deleteRota() {
		Employee emp1 =  new Employee("Dave", "Gordon", "daveg@cafe.com", "0445623963", "127 Neverland Road", "EMPLOYEE", 15.00, "dgordon", "$2y$12$YFoOeLsK651.rgEc2M9DQ.BwbhGYdmsLejloVkHiz32kdZjTOi0/y");
		Rota r1 = new Rota(emp1, "8-5", "8-5", "10-7", "8-5", "10-7", "7-2", "7-2");
		rrepository.delete(r1);
		assertThat(r1.getId()).isNull();
	}
	
	
}