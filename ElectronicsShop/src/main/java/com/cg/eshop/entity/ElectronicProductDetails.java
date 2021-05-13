package com.cg.eshop.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cg_electronic_product_details")
public class ElectronicProductDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_id")
	private Integer productID;
	@Column(name = "product_code")
	private String code;
	@Column(name = "product_name")
	private String name;
	@Column(name = "product_price")
	private Double price;
	@Column(name = "image")
	private String image;
	@Column (name = "create_date")
	private LocalDate createdate;
	@ManyToOne
	@JoinColumn(name = "category", referencedColumnName = "category_id")
	private Category category;
	
	@OneToMany(mappedBy = "elecProdDetails")
	private List<ElectronicProductSpecs> elecProdSpecs;
}
