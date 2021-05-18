

package com.cg.eshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cg_elec_prod_specs")
public class ElectronicProductSpecs {

	@Id
	@Column(name = "spec_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer specId;

	@Column(name = "spec_name")
	private String specName;

	@Column(name = "spec_value")
	private String specValue;

	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "product_id")
	private ElectronicProductDetails elecProdDetails;

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

	public ElectronicProductDetails getElecProdDetails() {
		return elecProdDetails;
	}

	public void setElecProdDetails(ElectronicProductDetails elecProdDetails) {
		this.elecProdDetails = elecProdDetails;
	}

	@Override
	public String toString() {
		return specId + " " + specName + " " + specValue ;
	}

}
