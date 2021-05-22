package com.cg.eshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cg_ordered_product_details")
public class OrderedProductDetails {
	@Id
	@Column(name = "prod_order_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer prodOrderId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id", referencedColumnName = "order_id")
	private OrderProducts prodOrders;
	
	@ManyToOne()
	@JoinColumn(name="product_id",referencedColumnName = "product_id")
	private ElectronicProductDetails electronicProduct;
	
	


	public OrderedProductDetails() {
		super();
	}

	public OrderedProductDetails(OrderProducts prodOrders, ElectronicProductDetails electronicProduct) {
		super();
		this.prodOrders = prodOrders;
		this.electronicProduct = electronicProduct;
	}

	public OrderedProductDetails(Integer prodOrderId, OrderProducts prodOrders,
			ElectronicProductDetails electronicProduct) {
		super();
		this.prodOrderId = prodOrderId;
		this.prodOrders = prodOrders;
		this.electronicProduct = electronicProduct;
	}


	public Integer getProdOrderId() {
		return prodOrderId;
	}

	public void setProdOrderId(Integer prodOrderId) {
		this.prodOrderId = prodOrderId;
	}

	public OrderProducts getProdOrders() {
		return prodOrders;
	}

	public void setProdOrders(OrderProducts prodOrders) {
		this.prodOrders = prodOrders;
	}

	public ElectronicProductDetails getElectronicProduct() {
		return electronicProduct;
	}

	public void setElectronicProduct(ElectronicProductDetails electronicProduct) {
		this.electronicProduct = electronicProduct;
	}

	@Override
	public String toString() {
		return prodOrderId +"";
	}

}
