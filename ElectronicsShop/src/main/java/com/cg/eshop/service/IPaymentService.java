package com.cg.eshop.service;

import com.cg.eshop.entity.BankTransaction;
import com.cg.eshop.error.TransactionNotFoundException;

public interface IPaymentService {
	public BankTransaction viewPayment(Integer trnxID) throws TransactionNotFoundException;
}
