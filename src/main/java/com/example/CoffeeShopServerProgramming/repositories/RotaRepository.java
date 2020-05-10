package com.example.CoffeeShopServerProgramming.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.CoffeeShopServerProgramming.model.Employee;
import com.example.CoffeeShopServerProgramming.model.Rota;



public interface RotaRepository extends CrudRepository<Rota, Long> {
	List<Rota> findByMon(String mon);
}
