package com.cg.eshop.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="cg_basket")
public class Basket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="basket_id")
	private String basketId;
	
	@Column(name="productCount")
	private Integer productCount;
	
	@Column(name="total")
	private Double Total;
	
	@Column(name="grand_total")
	private Double grandTotal;
	
	
	
	@OneToOne(mappedBy = "basket")
	private Customer customer;
	
	@OneToMany
	private List<ElectronicProductDetails> productList;

	public String getBasketId() {
		return basketId;
	}

	public void setBasketId(String basketId) {
		this.basketId = basketId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/*public Map<ElectronicProduct, Integer> getProductList() {
		return productList;
	}

	public void setProductList(Map<ElectronicProduct, Integer> productList) {
		this.productList = productList;
	}*/

}
