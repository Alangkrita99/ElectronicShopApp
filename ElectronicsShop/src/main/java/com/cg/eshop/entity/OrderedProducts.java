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
@Table(name = "cg_ordered_products")
public class OrderedProducts {
	@Id
	@Column(name = "prod_order_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer prodOrderId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_order", referencedColumnName = "order_id")
	private ElectronicProductOrder prodOrder;
	
	@Column(name = "quantity")
	private Integer qty;

	public Integer getProdOrderId() {
		return prodOrderId;
	}

	public void setProdOrderId(Integer prodOrderId) {
		this.prodOrderId = prodOrderId;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	@Override
	public String toString() {
		return "OrderedProducts [prodOrderId=" + prodOrderId + ", prodOrder=" + prodOrder + ", qty=" + qty + "]";
	}

}
