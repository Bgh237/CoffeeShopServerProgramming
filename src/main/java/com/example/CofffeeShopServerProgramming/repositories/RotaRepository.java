package com.example.CofffeeShopServerProgramming.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.CofffeeShopServerProgramming.model.Rota;
import com.example.CofffeeShopServerProgramming.model.Employee;



public interface RotaRepository extends CrudRepository<Rota, Long> {
	List<Rota> findByMon(String mon);
}
