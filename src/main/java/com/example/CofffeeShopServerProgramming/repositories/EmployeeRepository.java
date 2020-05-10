package com.example.CofffeeShopServerProgramming.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.CofffeeShopServerProgramming.model.Employee;



@RepositoryRestResource
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	List<Employee> findBySurname(String surname);
	Employee findByUsername(String username);
	
	List<Employee> findByEmail(@Param("email") String email);
	
}
