package com.example.CoffeeShopServerProgramming.model;

import java.io.Serializable;
import java.util.Optional;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

//This class creates a composite key for the database as the order id and item id together make the Primary Key
@Embeddable
public class OrderItemPK implements Serializable {
	
	@JsonBackReference
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
 
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item2) {
		this.item = item2;
	}
}
