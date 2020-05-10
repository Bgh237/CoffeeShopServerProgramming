package com.example.CoffeeShopServerProgramming.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Item {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long ItemId;
	
	private String name, description;
	private double price;
	
	@ManyToOne
	@JoinColumn(name = "catId")
	private ItemCategory category;

	public Item() {
		super();
	}

	public Item(String name, String description, double price, ItemCategory category) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
	}

	public Long getItemId() {
		return ItemId;
	}

	public void setItemId(Long itemId) {
		ItemId = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ItemCategory getCategory() {
		return category;
	}

	public void setCategory(ItemCategory category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Item [ItemId=" + ItemId + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", category=" + category + "]";
	}
	
	

}
