package com.cg.eshop.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.eshop.dao.IBankAccountDao;
import com.cg.eshop.dao.IBankTranxDao;
import com.cg.eshop.dao.ICustomerDao;
import com.cg.eshop.dao.IOrderProductsDao;
import com.cg.eshop.dto.PaymentReqDto;
import com.cg.eshop.entity.BankAccount;
import com.cg.eshop.entity.BankTransaction;
import com.cg.eshop.entity.Customer;
import com.cg.eshop.entity.OrderProducts;
import com.cg.eshop.exception.BankAccountNotFoundException;
import com.cg.eshop.exception.BankDetailsDidntMatchException;
import com.cg.eshop.exception.CustomerNotFoundException;
import com.cg.eshop.exception.NotSufficientBalanceException;
import com.cg.eshop.exception.OrderProductsNotFoundException;
import com.cg.eshop.exception.TransactionNotFoundException;
import com.cg.eshop.utils.OrderConstants;
import com.cg.eshop.utils.PaymentConstants;

@Service
@Transactional
public class PaymentServiceImpl implements IPaymentService {
	@Autowired
	private IOrderProductsDao orderProductsDao;
	@Autowired
	private ICustomerDao customerDao;
	@Autowired
	private IBankTranxDao btnxdao;
	@Autowired
	private IBankAccountDao bankaccdao;

	@Override
	public BankTransaction viewPayment(Integer trnxID) throws TransactionNotFoundException {
		Optional<BankTransaction> optemp = btnxdao.findById(trnxID);
		if (!optemp.isPresent()) {
			throw new TransactionNotFoundException("No bank transaction found for id" + trnxID);
		}
		//System.out.println("error 1");
		return optemp.get();
	}

	@Override
	public List<BankTransaction> viewPaymentbyCustID(Integer custId)
			throws TransactionNotFoundException, CustomerNotFoundException {
		Optional<Customer> optCust = customerDao.findById(custId);
		System.out.println("no error1");
		if (!optCust.isPresent())
			throw new CustomerNotFoundException(OrderConstants.CUSTOMER_NOT_FOUND);

		List<BankTransaction> lst = btnxdao.viewAllBankTransaction(custId);
		System.out.println("no error 2");
		if (lst.isEmpty())
			throw new TransactionNotFoundException(PaymentConstants.BANK_TRANSACTION_NOT_FOUND);
		// lst.sort((e1,e2)->e1.getTxnDate().compareTo(e2.getTxnDate()));
		System.out.println("no error 3");
		return lst;
	}

	@Override
	public Integer makePayment(PaymentReqDto payreqdto) throws OrderProductsNotFoundException,
			BankAccountNotFoundException, BankDetailsDidntMatchException, NotSufficientBalanceException {

		Optional<OrderProducts> orderpro = orderProductsDao.findById(payreqdto.getOrderId());
		if (!orderpro.isPresent())
			throw new OrderProductsNotFoundException(OrderConstants.ORDER_NOT_FOUND);
		OrderProducts orderproduct = orderpro.get();
		Customer cust = orderproduct.getCustomer();
		BankAccount bankacc = bankaccdao.findByCardNumber(payreqdto.getCardno());
		if (bankacc == null)
			throw new BankAccountNotFoundException(PaymentConstants.BANK_ACCOUNT_NOT_FOUND);
		
		bankacc.setCustomer(cust);

		if (bankacc.getCvv() != payreqdto.getCvv() || !bankacc.getCardHolderName().equals(payreqdto.getCardholder()) 
				|| !bankacc.getExpiryDt().equals(payreqdto.getExprdate()))
			throw new BankDetailsDidntMatchException(PaymentConstants.BANK_ACCOUNT_DETAILS_MISS_MATCH);

		if (bankacc.getAmount() <= orderproduct.getTotalCost())
			throw new NotSufficientBalanceException(PaymentConstants.BANK_BALANCE_INSUFFICIENT);
		BankTransaction newtransaction = new BankTransaction();

		newtransaction.setTxnAmount(orderproduct.getTotalCost());
		newtransaction.setTxnDate(LocalDate.now());
		newtransaction.setOrderproducts(orderproduct);

		bankacc.setAmount(bankacc.getAmount() - orderproduct.getTotalCost());

		BankAccount savebankacc = bankaccdao.save(bankacc);
		newtransaction.setBankAcc(savebankacc);

		BankTransaction savebanktnx = btnxdao.save(newtransaction);

		return savebanktnx.getBankTxns();
	}

}
