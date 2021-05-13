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
@Table(name = "cg_electronic_product_details")
public class ElectronicProductDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_id")
	private Integer productID;
	@Column(name = "product_code")
	private String code;
	@Column(name = "product_name")
	private String name;
	@Column(name = "product_price")
	private Double price;
	@Column(name = "image")
	private String image;
	@Column (name = "create_date")
	private LocalDate createdate;
	@ManyToOne
	@JoinColumn(name = "category", referencedColumnName = "category_id")
	private Category category;
	@ManyToOne
	@JoinColumn(name = "basket", referencedColumnName = "basket_id")
	private Basket basket;
	public Integer getProductID() {
		return productID;
	}
	public void setProductID(Integer productID) {
		this.productID = productID;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public LocalDate getCreatedate() {
		return createdate;
	}
	public void setCreatedate(LocalDate createdate) {
		this.createdate = createdate;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Basket getBasket() {
		return basket;
	}
	public void setBasket(Basket basket) {
		this.basket = basket;
	}
	
	
}