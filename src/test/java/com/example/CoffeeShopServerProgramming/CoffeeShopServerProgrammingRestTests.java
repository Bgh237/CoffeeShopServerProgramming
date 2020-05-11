package com.example.CoffeeShopServerProgramming;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CoffeeShopServerProgrammingRestTests {
	//The following code checks that the custom api endpoints I have created are running and return JSON
	@Autowired
	  private WebApplicationContext context;

	  private MockMvc mvc;

	  @Before
	  public void setup() {
	    mvc = MockMvcBuilders
	            .webAppContextSetup(context)
	            .apply(springSecurity())
	            .alwaysDo(print())
	            .build();
	  }
	  
	@Test
	@WithMockUser(authorities={"EMPLOYEE"})
	public void testRotaRestAPI() throws Exception {
		mvc
			.perform(get("/rota"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(content().string(containsString("mon")));
	}
	
	@Test
	@WithMockUser(authorities={"EMPLOYEE"})
	public void testEmployeeRestAPI() throws Exception {
		mvc
			.perform(get("/employee"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(content().string(containsString("firstName")));
	}
	
	@Test
	@WithMockUser(authorities={"EMPLOYEE"})
	public void testEmployeeNumberRestAPI() throws Exception {
		mvc
			.perform(get("/employee/1"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(content().string(containsString("Simon")));
	}
	
	
	
	@Test
	@WithMockUser(authorities={"EMPLOYEE"})
	public void testItemRestAPI() throws Exception {
		mvc
			.perform(get("/item"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(content().string(containsString("description")));
	}
	
	@Test
	@WithMockUser(authorities={"EMPLOYEE"})
	public void testItemNumberRestAPI() throws Exception {
		mvc
			.perform(get("/item/9"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(content().string(containsString("Burger")));
	}	

}
