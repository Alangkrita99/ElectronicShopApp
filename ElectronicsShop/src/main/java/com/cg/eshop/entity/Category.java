package com.cg.eshop.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cg_category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "category_id")
	private Integer categoryId;
	@Column(name = "category_name")
	private String categoryName;
	@JsonIgnore
	@OneToMany(mappedBy = "category")
	private Set<ElectronicProductDetails> electronicProducts;

	public Set<ElectronicProductDetails> getElectronicProducts() {
		return electronicProducts;
	}

	public void setElectronicProducts(Set<ElectronicProductDetails> electronicProducts) {
		this.electronicProducts = electronicProducts;
	}

	@Override
	public String toString() {
		return categoryId + " " + categoryName;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	

}
