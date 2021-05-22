package com.cg.eshop.dto;

import java.time.LocalDate;
import java.util.List;

public class OrderProductRequestDto {
	private Integer orderId;
	private LocalDate orderDate;
	private Double totalCost;
	private String orderStatus;
	private Integer custId;
	private List<Integer> electronicsProductDetailsIds;
	
	
	
	public OrderProductRequestDto() {
		super();
	}
	public OrderProductRequestDto(Integer orderId, LocalDate orderDate, Double totalCost, String orderStatus,
			Integer custId, List<Integer> electronicsProductDetails) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.totalCost = totalCost;
		this.orderStatus = orderStatus;
		this.custId = custId;
		this.electronicsProductDetailsIds = electronicsProductDetails;
	}
	
	
	public OrderProductRequestDto(LocalDate orderDate, Double totalCost, String orderStatus, Integer custId,
			List<Integer> electronicsProductDetails) {
		super();
		this.orderDate = orderDate;
		this.totalCost = totalCost;
		this.orderStatus = orderStatus;
		this.custId = custId;
		this.electronicsProductDetailsIds = electronicsProductDetails;
	}


	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	public Double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Integer getCustId() {
		return custId;
	}
	public void setCustId(Integer custId) {
		this.custId = custId;
	}
	public List<Integer> getElectronicsProductDetailsIds() {
		return electronicsProductDetailsIds;
	}
	public void setElectronicsProductDetailsIds(List<Integer> electronicsProductDetails) {
		this.electronicsProductDetailsIds = electronicsProductDetails;
	}
	
	
	
	
	

}
