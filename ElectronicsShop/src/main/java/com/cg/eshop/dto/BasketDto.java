
package com.cg.eshop.dto;

import javax.validation.constraints.NotNull;

import com.cg.eshop.utils.BasketConstants;

public class BasketDto {

	private Integer basketId;
	@NotNull(message = BasketConstants.CUSTOMER_NOT_NULL)
	private Integer custId;
	@NotNull(message = BasketConstants.PRODUCT_NOT_NULL)
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

	public Integer getBasketId() {
		return this.basketId;
	}

	public void setBasketId(Integer basketId) {
		this.basketId = basketId;
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

}