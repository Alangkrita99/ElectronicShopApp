package com.cg.eshop.dto;

import java.time.LocalDate;

public class ElectronicProductDetaisDto {
	private Integer productID;
	private String code;
	private String name;
	private Double price;
	private String image;
	private LocalDate createdate;
	private Integer stock;
	public ElectronicProductDetaisDto(Integer productID, String code, String name, Double price, String image,
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
	public ElectronicProductDetaisDto(String code, String name, Double price, String image, LocalDate createdate,
			Integer stock) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
		this.image = image;
		this.createdate = createdate;
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
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
}
