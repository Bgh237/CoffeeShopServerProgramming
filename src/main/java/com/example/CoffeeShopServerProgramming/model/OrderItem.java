package com.example.CoffeeShopServerProgramming.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
//The following class is used create an item included in a specific order. Included so that the database
@Entity
public class OrderItem {

    @EmbeddedId
    @JsonIgnore
    private OrderItemPK pk;
 
    @Column(nullable = false)
    private Integer quantity;
 
    public OrderItem() {
		super();
	}
 
    public OrderItem(Order order, Item item, Integer quantity) {
        pk = new OrderItemPK();
        pk.setOrder(order);
        pk.setItem(item);
        this.quantity = quantity;
    }
 
    
	@Transient
    public Item getItem() {
        return this.pk.getItem();
    }
 
    @Transient
    public Double getTotalPrice() {
        return getItem().getPrice() * getQuantity();
    }

	public OrderItemPK getPk() {
		return pk;
	}

	public void setPk(OrderItemPK pk) {
		this.pk = pk;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
    
    
 
    // standard getters and setters
 
    // hashcode() and equals() methods
}
