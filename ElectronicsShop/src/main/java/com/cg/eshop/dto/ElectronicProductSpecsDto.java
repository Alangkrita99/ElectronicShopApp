package com.cg.eshop.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.cg.eshop.util.ProductConstants;

public class ElectronicProductSpecsDto {
	private Integer specId;// added by me
	@NotNull(message = ProductConstants.PRODUCT_ID_REQUIRE)
	private Integer productId;
	@NotBlank(message = ProductConstants.SPEC_NAME_REQUIRE)
	private String specName;
	@NotBlank(message = ProductConstants.SPEC_VALUE_REQUIRE)
	private String specValue;

	public ElectronicProductSpecsDto() {
		super();
	}

	// added by me
	public ElectronicProductSpecsDto(Integer specId, Integer productId, String specName, String specValue) {
		super();
		this.specId = specId;
		this.productId = productId;
		this.specName = specName;
		this.specValue = specValue;
	}

	public ElectronicProductSpecsDto(String specName, String specValue, Integer productId) {
		super();
		this.productId = productId;
		this.specName = specName;
		this.specValue = specValue;
	}

	public ElectronicProductSpecsDto(Integer specId, String specName, String specValue) {
		super();
		this.specId = specId;
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

	public Integer getSpecId() {
		return specId;
	}

	public void setSpecId(Integer specId) {
		this.specId = specId;
	}

}
