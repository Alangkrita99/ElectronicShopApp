package com.cg.eshop.dto;

public class OrderedProductDetailsDto {
	private Integer prodOrderId;
	
	public OrderedProductDetailsDto() {
		super();
	}

	public OrderedProductDetailsDto(Integer prodOrderId) {
		super();
		this.prodOrderId = prodOrderId;
	}

	public Integer getProdOrderId() {
		return prodOrderId;
	}

	public void setProdOrderId(Integer prodOrderId) {
		this.prodOrderId = prodOrderId;
	}
	

}
