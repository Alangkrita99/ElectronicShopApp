package com.cg.eshop.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@Column(name = "create_date")
	private LocalDate createdate;
	@ManyToOne
	@JoinColumn(name = "category_id", referencedColumnName = "category_id")
	private Category category;
	@JsonIgnore
	@OneToMany(mappedBy = "elecProdDetails")
	private Set<ElectronicProductSpecs> elecProdSpecs;
	@Column(name = "prod_stock")
	private Integer stock;

	public ElectronicProductDetails() {
		super();
	}

	public ElectronicProductDetails(String code, String name, Double price, String image, LocalDate createdate,
			Category category, Set<ElectronicProductSpecs> elecProdSpecs, Integer stock) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
		this.image = image;
		this.createdate = createdate;
		this.category = category;
		this.elecProdSpecs = elecProdSpecs;
		this.stock = stock;
	}

	

	public ElectronicProductDetails(Integer productID, String code, String name, Double price, String image,
			LocalDate createdate, Integer stock) {
		super();
		this.productID = productID;
		this.code = code;
		this.name = name;
		this.price = price;
		this.image = image;
		this.createdate = createdate;
		this.stock = stock;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

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

	public Set<ElectronicProductSpecs> getElecProdSpecs() {
		return elecProdSpecs;
	}

	public void setElecProdSpecs(Set<ElectronicProductSpecs> elecProdSpecs) {
		this.elecProdSpecs = elecProdSpecs;
	}

	@Override
	public String toString() {
		return productID + " " + code + " " + name + " " + price + " " + image + " " + createdate ;
	}
}
