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
@Table(name = "cg_bank_txns")
public class BankTransaction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "txns_id")
	private Integer bankTxns;
	@ManyToOne
	@JoinColumn(name = "bank", referencedColumnName = "bank_id")
	private BankAccount bankAcc;
	@Column(name = "txns_date")
	private LocalDate txnDate;
	@Column(name = "txn_amount")
	private Double txnAmount;
	@ManyToOne
	@JoinColumn(name = "order_id", referencedColumnName = "order_id")
	private OrderProducts orderproducts;

	public BankTransaction() {
		super();
	}

	public BankTransaction(Integer bankTxns, BankAccount bankAcc, LocalDate txnDate, Double txnAmount) {
		super();
		this.bankTxns = bankTxns;
		this.bankAcc = bankAcc;
		this.txnDate = txnDate;
		this.txnAmount = txnAmount;
	}

	public BankTransaction(Integer bankTxns, LocalDate txnDate, Double txnAmount) {
		super();
		this.bankTxns = bankTxns;
		this.txnDate = txnDate;
		this.txnAmount = txnAmount;
	}

	public OrderProducts getOrderproducts() {
		return orderproducts;
	}

	public void setOrderproducts(OrderProducts orderproducts) {
		this.orderproducts = orderproducts;
	}

	public Integer getBankTxns() {
		return bankTxns;
	}

	public void setBankTxns(Integer bankTxns) {
		this.bankTxns = bankTxns;
	}

	public BankAccount getBankAcc() {
		return bankAcc;
	}

	public void setBankAcc(BankAccount bankAcc) {
		this.bankAcc = bankAcc;
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

	@Override
	public String toString() {
		return bankTxns + " " + txnDate + " " + txnAmount;
	}

}
