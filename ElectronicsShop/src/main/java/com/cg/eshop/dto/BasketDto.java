package com.cg.eshop.dto;

public class BasketDto {
	
	private Integer basketId;
	private Integer custId;
	private Integer prodId;
	
	public BasketDto() {
		super();
	}
	
	public BasketDto(Integer basketId, Integer custId, Integer prodId) {
		super();
		this.basketId = basketId;
		this.custId = custId;
		this.prodId = prodId;
	}

	public BasketDto(Integer custId, Integer prodId) {
		super();
		this.custId = custId;
		this.prodId = prodId;
	}
	public Integer getCustId() {
		return custId;
	}
	public void setCustId(Integer custId) {
		this.custId = custId;
	}
	public Integer getProdId() {
		return prodId;
	}
	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}
	public Integer getBasketId() {
		return basketId;
	}
	
	

}