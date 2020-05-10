package com.example.CoffeeShopServerProgramming;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
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
public class CoffeeShopServerProgrammingSecurityTests {
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
  public void loginAvailableForAll() throws Exception {
    mvc
            .perform(get("/login"))
            .andExpect(status().isOk());
  }
    
  // The succesful version of this would require me to put the logins so I have left it off but 
  // to test just add the correct username and password and change unauthenticated to authenticated
  
  @Test
	public void testUnsuccessfulLogin() throws Exception {
		mvc
	    	.perform(formLogin("/login").user("xxx").password("xxx"))
			.andExpect(unauthenticated());
	}
  
  @Test
	public void testUserNeedsToBeAuthenticated() throws Exception {
		mvc.perform(get("/menu")).andDo(print())
			.andExpect(unauthenticated());
	}
  
  @Test
  @WithMockUser(authorities={"MANAGER"})
	public void testUserIsAuthorised() throws Exception {
		mvc.perform(get("/employees")).andDo(print())
			.andExpect(status().isOk());
	}
  
  @Test
  @WithMockUser(roles={"EMPLOYEE"})
	public void testUserIsNotAuthorised() throws Exception {
		mvc.perform(get("/employees")).andDo(print())
			.andExpect(status().isForbidden());
	}

  @Test
	@WithMockUser(roles={"EMPLOYEE"})
	public void testRotaRest() throws Exception {
		mvc
			.perform(get("/rota"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(content().string(containsString("mon")));
	}


  @Test
  public void invalidLoginDenied() throws Exception {
    String loginErrorUrl = "/login?error";
    mvc
            .perform(formLogin().password("invalid"))
            .andExpect(status().isFound())
            .andExpect(redirectedUrl(loginErrorUrl))
            .andExpect(unauthenticated());

    mvc
            .perform(get(loginErrorUrl))
            .andExpect(content().string(containsString("Invalid username and password")));
  }
}
