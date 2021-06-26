package com.cg.eshop.service;


import java.util.List;

import com.cg.eshop.dto.PaymentReqDto;
import com.cg.eshop.entity.BankTransaction;
import com.cg.eshop.exception.BankAccountNotFoundException;
import com.cg.eshop.exception.BankDetailsDidntMatchException;
import com.cg.eshop.exception.CustomerNotFoundException;
import com.cg.eshop.exception.NotSufficientBalanceException;
import com.cg.eshop.exception.OrderProductsNotFoundException;
import com.cg.eshop.exception.TransactionNotFoundException;

public interface IPaymentService {
	public BankTransaction getPayment(Integer trnxID) throws TransactionNotFoundException;
	public List<BankTransaction> getPaymentbyCustID(Integer custId) throws TransactionNotFoundException, CustomerNotFoundException;
	public Integer makePayment (PaymentReqDto payreqdto) throws OrderProductsNotFoundException, BankAccountNotFoundException, BankDetailsDidntMatchException, NotSufficientBalanceException;
}