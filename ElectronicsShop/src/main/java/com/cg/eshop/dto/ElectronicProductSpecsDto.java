package com.cg.eshop.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.cg.eshop.utils.ProductConstants;

public class ElectronicProductSpecsDto {
	@NotNull(message = ProductConstants.PRODUCT_ID_REQUIRE)
	private Integer productId;
	@NotBlank(message = ProductConstants.SPEC_NAME_REQUIRE)
	private String specName;
	@NotBlank(message = ProductConstants.SPEC_VALUE_REQUIRE)
	private String specValue;

	public ElectronicProductSpecsDto() {
		super();
	}

	public ElectronicProductSpecsDto(Integer productId, String specName, String specValue) {
		super();
		this.productId = productId;
		this.specName = specName;
		this.specValue = specValue;
	}

	public ElectronicProductSpecsDto(String specName, String specValue) {
		super();
		this.specName = specName;
		this.specValue = specValue;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getSpecName() {
		return specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}

	public String getSpecValue() {
		return specValue;
	}

	public void setSpecValue(String specValue) {
		this.specValue = specValue;
	}

}
