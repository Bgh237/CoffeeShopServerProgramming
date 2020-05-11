package com.example.CoffeeShopServerProgramming;

import com.zaxxer.hikari.*;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.net.URISyntaxException;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

  @Value("${spring.datasource.url}")
  private String dbUrl;

  @Bean
  public BasicDataSource dataSource() throws URISyntaxException {
     
	  //The following code is used to connect to the Postgresql database on heroku
	  
	  String dbUrl = System.getenv("JDBC_DATABASE_URL");
      String username = System.getenv("JDBC_DATABASE_USERNAME");
      String password = System.getenv("JDBC_DATABASE_PASSWORD");

      BasicDataSource basicDataSource = new BasicDataSource();
      basicDataSource.setUrl(dbUrl);
      basicDataSource.setUsername(username);
      basicDataSource.setPassword(password);

      return basicDataSource;
  }
 
}