package com.cg.eshop.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
import com.cg.eshop.exception.ValidateException;
import com.cg.eshop.service.IPaymentService;
import com.cg.eshop.utils.PaymentConstants;
/**
 * @author WRIJU BANERJEE
 * @Version : 1.0
 * @Description : This Controller Class manages the RestController for Payment Management 
 */
@RestController
public class PaymentCrudController {
	@Autowired
	public IPaymentService paymentservice;
	
	/**
	 * @param instance of payment dto
	 * @return List<BankTransaction>
	 * @throws TransactionNotFoundException ,if transaction is empty for a
	 *                                      transaction Id
	 * @throws CustomerNotFoundException    ,if transaction is empty for a Customer
	 *                                      Id
	 * @description This method returns a success message 
	 * @createdAt 16-May-2021
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("makepayment")
	public SuccessMessage makePaymentTranx(@Valid @RequestBody PaymentReqDto paymentreq, BindingResult br) throws OrderProductsNotFoundException, BankAccountNotFoundException, BankDetailsDidntMatchException, NotSufficientBalanceException, ValidateException{
		if (br.hasErrors())
			throw new ValidateException(br.getFieldErrors());
		Integer tranxID = paymentservice.makePayment(paymentreq);
		return new SuccessMessage(PaymentConstants.SUCCESS_MESSAGE+tranxID);
		
	}
	/**
	 * @param custID Customer Id
	 * @return List<BankTransaction>
	 * @throws TransactionNotFoundException ,if transaction is empty for a
	 *                                      transaction Id
	 * @throws CustomerNotFoundException    ,if transaction is empty for a Customer
	 *                                      Id
	 * @description This method returns a list of transaction for a customer Id
	 * @createdAt 16-May-2021
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("viewpaymentbycustid/{custid}")
	public List<BankTransaction> viewPaymentbyCustID(@PathVariable("custid") Integer custId) throws CustomerNotFoundException, TransactionNotFoundException{
		return paymentservice.getPaymentbyCustID(custId);
	}

	/**
	 * @param trnxID Bank Transaction Id
	 * @return List<BankTransaction>
	 * @throws TransactionNotFoundException ,if transaction is empty for a
	 *                                      transaction Id
	 * @description This method returns a list of transaction for a transaction Id
	 * @createdAt 15-May-2021
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("viewpayment/{trnxid}")
	public BankTransaction viewPayment(@PathVariable("trnxid")Integer trnxID) throws TransactionNotFoundException{
		return paymentservice.getPayment(trnxID);
	}
}
