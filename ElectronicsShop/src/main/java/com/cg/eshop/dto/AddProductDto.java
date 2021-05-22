package com.cg.eshop.dto;

public class AddProductDto {
	private String code;
	private String name;
	private Double price;
	private String image;
	private Integer stock;
	private Integer categoryId;
	
	
	public AddProductDto() {
		super();
	}
	public AddProductDto(String code, String name, Double price, String image, Integer stock, Integer categoryId) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
		this.image = image;
		this.stock = stock;
		this.categoryId = categoryId;
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
	
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
}
