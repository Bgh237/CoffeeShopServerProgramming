package com.example.CofffeeShopServerProgramming;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import com.example.CofffeeShopServerProgramming.model.Employee;
import com.example.CofffeeShopServerProgramming.model.Item;
import com.example.CofffeeShopServerProgramming.model.ItemCategory;
import com.example.CofffeeShopServerProgramming.model.Rota;
import com.example.CofffeeShopServerProgramming.repositories.EmployeeRepository;
import com.example.CofffeeShopServerProgramming.repositories.ItemCategoryRepository;
import com.example.CofffeeShopServerProgramming.repositories.ItemRepository;
import com.example.CofffeeShopServerProgramming.repositories.RotaRepository;



@SpringBootApplication
public class CofffeeShopServerProgrammingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CofffeeShopServerProgrammingApplication.class, args);
	}

	//used to display a custom error page
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	      return builder.sources(CofffeeShopServerProgrammingApplication.class);
	  }

	/*@Bean 
	public CommandLineRunner demo(EmployeeRepository erepository, ItemRepository irepository, ItemCategoryRepository icrepository, RotaRepository rrepository) {
		return (args) -> {
			Employee emp1 = new Employee("Simon", "Barry", "simonb@cafe.com", "0445623985", "123 Neverland Road", "MANAGER", 100.00, "sbarry", "$2y$12$KErdwjIYRrB2WrhsKUF5M.Pb9PtuWyWV4amTRVtrWmOMuW8TSZCT.");
			Employee emp2 = new Employee("Tiina", "Pöyry", "tiinap@cafe.com", "0445698575", "125 Overland Road", "EMPLOYEE", 20.00, "tpoyry", "$2y$12$0EE2aoqLshB.h/B24Tch0OTGnw.yQjLY.OrNn22yrJpCB8tfXROry");
			Employee emp3 = new Employee("Tony", "Pope", "tonyp@cafe.com", "0445684564", "12 Bell Street", "EMPLOYEE", 10.00, "tpope", "$2y$12$KvyQjV5fhkpW7FQMPG3ztOfbLUYoVhpBhjv10kwQta3/Om8vj8HAK");

			erepository.save(emp1);
			erepository.save(emp2);
			erepository.save(emp3);
			
			Rota r1 = new Rota(emp1, "8-5", "8-5", "10-7", "8-5", "10-7", "7-2", "7-2");
			Rota r2 = new Rota(emp2, "8-5", "8-5", "10-7", "8-5", "10-7", "7-2", "7-2");
			Rota r3 = new Rota(emp3, "8-5", "8-5", "10-7", "8-5", "10-7", "7-2", "7-2");
			
			rrepository.save(r1);
			rrepository.save(r2);
			rrepository.save(r3);
			
			ItemCategory Food = new ItemCategory("Food");
			ItemCategory Drink = new ItemCategory("Drink");
			
			icrepository.save(Food);
			icrepository.save(Drink);
			
			Item fd1 = new Item("Burger", "Cheese Burger", 10.00, Food);
			Item fd2 = new Item("Toastie", "Ham & Cheese Toastie", 10.00, Food);
			Item fd3 = new Item("Muffin", "Blueberry Muffin", 5.00, Food);
			Item dr1 = new Item("Flat White", "Milky coffee", 3.50, Drink);
			Item dr2 = new Item("Milkshake", "Banana Milkshake", 4.00, Drink);
			Item dr3 = new Item("Beer", "Fucking Beautiful", 3.50, Drink);
			
			irepository.save(fd1);
			irepository.save(fd2);
			irepository.save(fd3);
			irepository.save(dr1);
			irepository.save(dr2);
			irepository.save(dr3);
			
			
		};
	}*/
}
