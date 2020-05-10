package com.example.CoffeeShopServerProgramming.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long empId;
	private String firstName, surname, email, telno, address, role;
	private double hourlyRate;
	
	@Column(name="username", nullable=false, unique=true)
	private String username;
	
	@Column(name="password", nullable=false)
	private String passwordHash;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	@JsonIgnore
	private List<Rota> rotas;
	

	public Employee() {
	}


	public Employee(String firstName, String surname, String email, String telno, String address, String role,
			double hourlyRate, String username, String passwordHash) {
		super();
		this.firstName = firstName;
		this.surname = surname;
		this.email = email;
		this.telno = telno;
		this.address = address;
		this.role = role;
		this.hourlyRate = hourlyRate;
		this.username = username;
		this.passwordHash = passwordHash;
	}


	public Long getId() {
		return empId;
	}


	public void setId(Long empId) {
		this.empId = empId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTelno() {
		return telno;
	}


	public void setTelno(String telno) {
		this.telno = telno;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public double getHourlyRate() {
		return hourlyRate;
	}


	public void setHourlyRate(double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	
	public List<Rota> getRotas() {
		return rotas;
	}


	public void setRotas(List<Rota> rotas) {
		this.rotas = rotas;
	}


	@Override
	public String toString() {
		return "Employee [id=" + empId + ", firstName=" + firstName + ", surname=" + surname + ", email=" + email
				+ ", telno=" + telno + ", address=" + address + ", role=" + role + ", hourlyRate=" + hourlyRate + "]";
	}



}
