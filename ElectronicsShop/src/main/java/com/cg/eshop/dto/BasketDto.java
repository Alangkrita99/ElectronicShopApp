
package com.cg.eshop.dto;

import javax.validation.constraints.NotNull;

import com.cg.eshop.utils.BasketConstants;

public class BasketDto {

	@NotNull(message = BasketConstants.CUSTOMER_NOT_NULL)
	private Integer custId;
	@NotNull(message = BasketConstants.PRODUCT_NOT_NULL)
	private Integer prodId;
	
	public BasketDto() {
		super();
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