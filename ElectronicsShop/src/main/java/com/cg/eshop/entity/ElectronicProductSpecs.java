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
@Table(name="cg_elec_prod_specs")
public class ElectronicProductSpecs {
	
	@Id
	@Column(name="spec_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer specId;
	
	@Column(name="spec_name")
	private String specName;
	
	@Column(name="spec_value")
	private String specValue;
	
	@ManyToOne
	@JoinColumn(name="product_id" , referencedColumnName = "product_id")
	private ElectronicProductDetails elecProdDetails;

}
