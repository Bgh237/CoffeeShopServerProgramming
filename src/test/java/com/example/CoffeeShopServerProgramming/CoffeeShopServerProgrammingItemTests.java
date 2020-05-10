package com.example.CoffeeShopServerProgramming;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.CoffeeShopServerProgramming.model.Item;
import com.example.CoffeeShopServerProgramming.model.ItemCategory;
import com.example.CoffeeShopServerProgramming.repositories.ItemCategoryRepository;
import com.example.CoffeeShopServerProgramming.repositories.ItemRepository;



@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CoffeeShopServerProgrammingItemTests {

	@Autowired
	private ItemRepository irepository;
	
	@Autowired
	private ItemCategoryRepository icrepository;
	
	
	@Test
	public void findByNameShouldReturnItem() {
		List<Item> items = irepository.findByName("Burger");
		
		assertThat(items).hasSize(1);
		assertThat(items.get(0).getPrice()).isEqualTo(10.00);
	}
	
	@Test
	public void addNewItem() {
		Item item = new Item("Greek Salad", "Greek Salad", 10.00, new ItemCategory ("Salad"));
		
		irepository.save(item);
		assertThat(item.getItemId()).isNotNull();
		assertThat(item.getName()).isEqualTo("Greek Salad");
		assertThat(item.getCategory().getCategory()).isEqualTo("Salad");
	}
	
	@Test
	public void deleteEmployee() {
		Item item = new Item("Salad", "Greek Salad", 10.00, new ItemCategory ("Salad"));
		irepository.delete(item);
		assertThat(item.getItemId()).isNull();
	}
	
	
}
