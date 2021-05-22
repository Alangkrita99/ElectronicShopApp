package com.cg.eshop.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cg_order_products")
public class OrderProducts {
	@Id
	@Column(name = "order_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
	@Column(name = "order_date")
	private LocalDate orderDate;
	@ManyToOne()
	@JoinColumn(name = "cust_id", referencedColumnName = "cust_id")
	private Customer customer;
	@Column(name = "total_cost")
	private Double totalCost;
	@Column(name = "order_status")
	private String orderStatus;

	public OrderProducts() {
		super();
	}
	

	public OrderProducts(LocalDate orderDate, Customer customer, Double totalCost, String orderStatus) {
		super();
		this.orderDate = orderDate;
		this.customer = customer;
		this.totalCost = totalCost;
		this.orderStatus = orderStatus;
	}


	public OrderProducts(Integer orderId, LocalDate orderDate, Customer customer, Double totalCost,
			String orderStatus) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.customer = customer;
		this.totalCost = totalCost;
		this.orderStatus = orderStatus;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return orderId + " " + orderDate +" " + totalCost + " " + orderStatus;
	}

}
