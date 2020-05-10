package com.example.CoffeeShopServerProgramming.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Rota {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String mon, tue, wed, thur, fri, sat, sun;
	
	@ManyToOne 
	@JoinColumn(name = "empid")
	private Employee employee;
	
	public Rota () {}
	
	public Rota(Employee employee, String mon, String tue, String wed, String thur, String fri, String sat,
			String sun) {
		super();
		this.employee = employee;
		this.mon = mon;
		this.tue = tue;
		this.wed = wed;
		this.thur = thur;
		this.fri = fri;
		this.sat = sat;
		this.sun = sun;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMon() {
		return mon;
	}

	public void setMon(String mon) {
		this.mon = mon;
	}

	public String getTue() {
		return tue;
	}

	public void setTue(String tue) {
		this.tue = tue;
	}

	public String getWed() {
		return wed;
	}

	public void setWed(String wed) {
		this.wed = wed;
	}

	public String getThur() {
		return thur;
	}

	public void setThur(String thur) {
		this.thur = thur;
	}

	public String getFri() {
		return fri;
	}

	public void setFri(String fri) {
		this.fri = fri;
	}

	public String getSat() {
		return sat;
	}

	public void setSat(String sat) {
		this.sat = sat;
	}

	public String getSun() {
		return sun;
	}

	public void setSun(String sun) {
		this.sun = sun;
	}
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Rota [employee=" + employee.getFirstName() + " " + employee.getSurname() + ", mon=" + mon + ", tue=" + tue + ", wed=" + wed + ", thur=" + thur
				+ ", fri=" + fri + ", sat=" + sat + ", sun=" + sun + "]";
	}
	
	
	
}
