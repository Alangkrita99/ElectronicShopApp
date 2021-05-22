package com.cg.eshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cg_basket_item")
public class Basket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "basket_id")
	private Integer basketId;

	@ManyToOne()
	@JoinColumn(name = "customer_id", referencedColumnName = "cust_id")
	private Customer customer;
	@ManyToOne()
	@JoinColumn(name = "product_id", referencedColumnName = "product_id")
	private ElectronicProductDetails productDetails;
	
	

	public Basket() {
		super();
	}
	

	public Basket(Integer basketId, Customer customer, ElectronicProductDetails productDetails) {
		super();
		this.basketId = basketId;
		this.customer = customer;
		this.productDetails = productDetails;
	}

	public Basket(Customer customer, ElectronicProductDetails productDetails) {
		super();
		this.customer = customer;
		this.productDetails = productDetails;
	}


	public Integer getBasketId() {
		return basketId;
	}

	public void setBasketId(Integer basketId) {
		this.basketId = basketId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ElectronicProductDetails getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(ElectronicProductDetails productDetails) {
		this.productDetails = productDetails;
	}

	@Override
	public String toString() {
		return basketId+"" ;
	}

}
