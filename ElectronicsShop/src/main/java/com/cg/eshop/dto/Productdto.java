package com.cg.eshop.dto;

import java.time.LocalDate;

public class Productdto {
	
	private Integer id; 
	private String prodName;
	private String code;
	private Double price;
	private String image;
	private LocalDate createdate;
	private Integer stock;
	private String categoryName;
	
	public Productdto(String prodName, String code, Double price, String image, LocalDate createdate, Integer stock, String categoryName) {
		super();
		this.prodName = prodName;
		this.code = code;
		this.price = price;
		this.image = image;
		this.createdate = createdate;
		this.stock = stock;
		this.categoryName = categoryName;
	}

	public Productdto(Integer id, String prodName, String code, Double price, String image, LocalDate createdate,
			Integer stock, String categoryName) {
		super();
		this.id = id;
		this.prodName = prodName;
		this.code = code;
		this.price = price;
		this.image = image;
		this.createdate = createdate;
		this.stock = stock;
		this.categoryName = categoryName;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	

}
