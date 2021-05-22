package com.cg.eshop.dto;

import java.time.LocalDate;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;

public class PaymentReqDto {
	//@NotBlank(message = "order ID cant be blank")
	private Integer orderId;
	@Range(min = 100, max = 999, message = "Please provide the 3 digit cvv number.")
	@Digits(integer = 3, fraction = 0, message = "the cvv must be a number")
	private Integer cvv;
	@Future(message = "exprdate should not be in the past or present")
	private LocalDate exprdate;
	@NotBlank(message = "card holder name must be present")
	private String cardholder;
	//@Range(min = 10, max = 10, message = "Please provide a valid card number.")
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
