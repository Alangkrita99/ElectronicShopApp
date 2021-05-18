package com.cg.eshop.dto;

import java.time.LocalDate;

public class PaymentReqDto {
	
	private Integer orderId;
	private Integer cvv;
	private LocalDate exprdate;
	private String cardholder;
	private Integer cardno;
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getCvv() {
		return cvv;
	}
	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}
	public LocalDate getExprdate() {
		return exprdate;
	}
	public void setExprdate(LocalDate exprdate) {
		this.exprdate = exprdate;
	}
	public String getCardholder() {
		return cardholder;
	}
	public void setCardholder(String cardholder) {
		this.cardholder = cardholder;
	}
	public Integer getCardno() {
		return cardno;
	}
	public void setCardno(Integer cardno) {
		this.cardno = cardno;
	}
	public PaymentReqDto(Integer cvv, LocalDate exprdate, String cardholder, Integer cardno) {
		super();
		this.cvv = cvv;
		this.exprdate = exprdate;
		this.cardholder = cardholder;
		this.cardno = cardno;
	}
	public PaymentReqDto(Integer orderId, Integer cvv, LocalDate exprdate, String cardholder, Integer cardno) {
		super();
		this.orderId = orderId;
		this.cvv = cvv;
		this.exprdate = exprdate;
		this.cardholder = cardholder;
		this.cardno = cardno;
	}
	public PaymentReqDto() {
		super();
		
	}
	
	

}
