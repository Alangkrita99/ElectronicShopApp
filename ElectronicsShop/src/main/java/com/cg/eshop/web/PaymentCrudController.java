package com.cg.eshop.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.eshop.dto.PaymentReqDto;
import com.cg.eshop.dto.SuccessMessage;
import com.cg.eshop.entity.BankTransaction;
import com.cg.eshop.exception.BankAccountNotFoundException;
import com.cg.eshop.exception.BankDetailsDidntMatchException;
import com.cg.eshop.exception.CustomerNotFoundException;
import com.cg.eshop.exception.NotSufficientBalanceException;
import com.cg.eshop.exception.OrderProductsNotFoundException;
import com.cg.eshop.exception.TransactionNotFoundException;
import com.cg.eshop.service.IPaymentService;

@RestController
public class PaymentCrudController {
	@Autowired
	public IPaymentService paymentservice;
	
	@RequestMapping(name = "paymenttransaction",method = RequestMethod.POST)
	public SuccessMessage makePaymentTranx(@Valid @RequestBody PaymentReqDto paymentreq) throws OrderProductsNotFoundException, CustomerNotFoundException, BankAccountNotFoundException, BankDetailsDidntMatchException, NotSufficientBalanceException{
		
		Integer tranxID = paymentservice.makePayment(paymentreq);
		SuccessMessage mesg = new SuccessMessage("Your generated transaction ID is :"+tranxID);
		return mesg;
		
	}
	@GetMapping("viewpaymentbycustid/{custid}")
	public List<BankTransaction> viewPaymentbyCustID(@PathVariable("custid") Integer custId) throws OrderProductsNotFoundException, CustomerNotFoundException, TransactionNotFoundException{
		return paymentservice.viewPaymentbyCustID(custId);
	}
	@GetMapping("viewpayment/{trnxid}")
	public BankTransaction viewPayment(@PathVariable("trnxID") Integer trnxID) throws TransactionNotFoundException{
		return paymentservice.viewPayment(trnxID);
	}
}
