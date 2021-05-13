package com.cg.eshop.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="cg_order_products")
public class OrderProducts {
	@Id
	@Column(name = "order_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
	@Column(name="order_date")
	private LocalDate orderDate;
	@ManyToOne()
	@JoinColumn(name = "cust_id",referencedColumnName = "cust_id")
	private Customer customer;
	@Column
	private Double totalCost;
	@Column
	private String orderStatus;
	


}
