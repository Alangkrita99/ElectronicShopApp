package com.cg;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.eshop.dao.IBankTranxDao;
import com.cg.eshop.entity.BankTransaction;
import com.cg.eshop.exception.TransactionNotFoundException;
import com.cg.eshop.service.IPaymentService;
import com.cg.eshop.service.PaymentServiceImpl;

@SpringBootTest
public class TestViewTranxByTranxID {
	@Mock
	private IBankTranxDao banktranxdao;
	
	@InjectMocks
	private IPaymentService service = new PaymentServiceImpl();
	
	@BeforeEach
	private void beforeEach() {
		Optional<BankTransaction> op1 = Optional.of(new BankTransaction());
		Optional<BankTransaction> op2 = Optional.empty();
		when(banktranxdao.findById(1)).thenReturn(op1);
		when(banktranxdao.findById(2)).thenReturn(op2);
	}
	
	@Test
	@DisplayName(value = "test view transaction by transaction ID 1")
	public void testbytranxID() throws TransactionNotFoundException{
		assertNotNull(service.viewPayment(1));
	}

	@Test
	@DisplayName(value = "test view transaction by transaction ID 2")
	public void testbytranxID2() throws TransactionNotFoundException{
		assertThrows(TransactionNotFoundException.class,()-> service.viewPayment(2));
	}
}
