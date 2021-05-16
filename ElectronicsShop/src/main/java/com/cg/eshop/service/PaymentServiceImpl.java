package com.cg.eshop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.eshop.dao.IBankTranxDao;
import com.cg.eshop.entity.BankTransaction;
import com.cg.eshop.error.TransactionNotFoundException;

@Service("myser")
@Transactional
public class PaymentServiceImpl implements IPaymentService {
	@Autowired
	private IBankTranxDao btnxdao;

	@Override
	public BankTransaction viewPayment(Integer trnxID) throws TransactionNotFoundException {
		Optional<BankTransaction> optemp = btnxdao.findById(trnxID);
		if (!optemp.isPresent()) {
			throw new TransactionNotFoundException("No bank transaction found for id" + trnxID);
		}
		return optemp.get();
	}

}
