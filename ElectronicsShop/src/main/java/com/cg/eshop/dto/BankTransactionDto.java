package com.cg.eshop.dto;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

public class BankTransactionDto {
	private Integer bankTxns;
	@PastOrPresent(message = "date of transaction must be past or present")
	private LocalDate txnDate;
	@NotBlank(message = "department name cant be blank")
	@Min(value = 1, message = "the minimum amount is 1")
	private Double txnAmount;

	public BankTransactionDto(Integer bankTxns, LocalDate txnDate, Double txnAmount) {
		super();
		this.bankTxns = bankTxns;
		this.txnDate = txnDate;
		this.txnAmount = txnAmount;
	}

	public BankTransactionDto(@PastOrPresent(message = "date of transaction must be past or present") LocalDate txnDate,
			@NotBlank(message = "department name cant be blank") @Min(value = 1, message = "the minimum amount is 1") Double txnAmount) {
		super();
		this.txnDate = txnDate;
		this.txnAmount = txnAmount;
	}

	public Integer getBankTxns() {
		return bankTxns;
	}

	public void setBankTxns(Integer bankTxns) {
		this.bankTxns = bankTxns;
	}

	public LocalDate getTxnDate() {
		return txnDate;
	}

	public void setTxnDate(LocalDate txnDate) {
		this.txnDate = txnDate;
	}

	public Double getTxnAmount() {
		return txnAmount;
	}

	public void setTxnAmount(Double txnAmount) {
		this.txnAmount = txnAmount;
	}

}