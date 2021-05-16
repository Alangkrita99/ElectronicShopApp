package com.cg.eshop.dto;

public class ElectronicProductSpecsDto {
	private Integer specId;
	private String specName;
	private String specValue;

	public ElectronicProductSpecsDto(Integer specId, String specName, String specValue) {
		super();
		this.specId = specId;
		this.specName = specName;
		this.specValue = specValue;
	}

	public ElectronicProductSpecsDto(String specName, String specValue) {
		super();
		this.specName = specName;
		this.specValue = specValue;
	}

	public Integer getSpecId() {
		return specId;
	}

	public void setSpecId(Integer specId) {
		this.specId = specId;
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
