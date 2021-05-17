package com.cg.eshop.service;

import java.util.List;

import com.cg.eshop.entity.BankTransaction;
import com.cg.eshop.entity.Customer;
import com.cg.eshop.exception.TransactionNotFoundException;

public interface IPaymentService {
	public BankTransaction viewPayment(Integer trnxID) throws TransactionNotFoundException;
	public List<BankTransaction> viewPayment(Customer custId) throws TransactionNotFoundException;
}
