package com.example.CoffeeShopServerProgramming;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.actuate.context.ShutdownEndpoint;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.client.RestTemplate;

import com.example.CoffeeShopServerProgramming.service.Impl.UserDetailServiceImpl;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailServiceImpl userDetailsService;
	
	//The following code is used to configure the http
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	 http
    	 //set up app to be deployed in heroku with https
    	 .requiresChannel()
         .requestMatchers(r -> r.getHeader("X-Forwarded-Proto") != null)
         .requiresSecure()
         //authorise use of some pages without login
         .and()
         .authorizeRequests()
         .antMatchers("/css/**", "/login", "/signup", "/save").permitAll()
         //Only allow access to the actuator shutdown method to users with the MANAGER authority and others open to everyone
         .requestMatchers(EndpointRequest.to(ShutdownEndpoint.class))
         .hasAuthority("MANAGER")
         .requestMatchers(EndpointRequest.toAnyEndpoint())
         .permitAll()
         .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
         .permitAll()
         .antMatchers("/slowApi")
         .permitAll()
         .antMatchers("/**")
         .authenticated()
         .anyRequest().authenticated()
         .and()
      .formLogin()
      .loginPage("/login")
           .defaultSuccessUrl("/")
           .permitAll()
           .and()
       .logout()
           .permitAll()
    	 .and()
         .httpBasic();
    	
    }
    
    //The following is used to encrypt the passwords with Bcrypt
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
    
    //Add some users
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        List<UserDetails> users = new ArrayList();
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("manager")
                .password("manager")
                .roles("MANAGER", "EMPLOYEE")
                .build();
    	

    	users.add(user);
    	
    	user = User.withDefaultPasswordEncoder()
                   .username("employee")
                   .password("employee")
                   .roles("EMPLOYEE")
                   .build();
    	
    	users.add(user);
    	
        return new InMemoryUserDetailsManager(users);
    }

}
