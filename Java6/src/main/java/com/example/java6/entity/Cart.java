package com.example.java6.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "carts")
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	private BigDecimal price;
	@Column(nullable = false)
	private Integer quantity;
	@ManyToOne
	@JoinColumn(name = "User_id",referencedColumnName = "user_id" )
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "product_id",referencedColumnName = "id" )
	private Product product;

	public Cart(Integer id, BigDecimal price, Integer quantity, User user, Product product) {
		this.id = id;
		this.price = price;
		this.quantity = quantity;
		this.user = user;
		this.product = product;
	}

	public Cart() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
