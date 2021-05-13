package com.cg.eshop.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="cg_elec_prod_order")
public class ElectronicProductOrder {
	
	@Id
	@Column(name="order_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
	@Column(name="order_date")
	private LocalDate orderDate;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="cust_id",referencedColumnName = "cust_id")
	private Customer customer;
	@Column(name="order_num")
	private Integer ordernum;
	
	
	@OneToMany(mappedBy = "prodOrder")
	private List<OrderedProducts> productList;
	
	
	/*@ManyToOne()
//	@Column(name="shipping_address")
	private Address ShippingAddress;
	@ManyToOne
//	@Column(name="billing_address")
	private Address billingAddress;*/
	
	
	@Column(name="totalAmount")
	private double totalAmount;
	@Column(name="orderStatus")
	private String orderStatus;
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
	public Integer getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(Integer ordernum) {
		this.ordernum = ordernum;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

}