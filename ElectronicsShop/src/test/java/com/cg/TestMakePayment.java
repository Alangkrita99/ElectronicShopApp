package com.cg;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

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
import com.cg.eshop.exception.NotSufficientBalanceException;
import com.cg.eshop.exception.OrderProductsNotFoundException;
import com.cg.eshop.service.IPaymentService;
import com.cg.eshop.service.PaymentServiceImpl;

@SpringBootTest
 class TestMakePayment {
	@Mock
	private IOrderProductsDao orderProductsDao;
	@Mock
	private ICustomerDao customerDao;
	@Mock
	private IBankTranxDao btnxdao;
	@Mock
	private IBankAccountDao bankaccdao;
	
	@InjectMocks
	private IPaymentService service = new PaymentServiceImpl();
	
	@BeforeEach
	public void beforeEach() {
		Optional<OrderProducts> oprderpro1 = Optional.of(new OrderProducts(1,LocalDate.now(),"ordered",5000.0));
		Optional<OrderProducts> oprderpro2 = Optional.empty();
		Optional<OrderProducts> oprderpro3 = Optional.of(new OrderProducts(3,LocalDate.now(),"ordered",35000.0));
		when(orderProductsDao.findById(1)).thenReturn(oprderpro1);
		when(orderProductsDao.findById(2)).thenReturn(oprderpro2);
		when(orderProductsDao.findById(3)).thenReturn(oprderpro3);
		BankAccount ba = new BankAccount(1,123,new Customer(),"riju",LocalDate.of(2022, 10, 10),123,30000.0); 
		when(bankaccdao.findByCardNumber(123)).thenReturn(ba);
		when(bankaccdao.findByCardNumber(100)).thenReturn(null);
		BankTransaction persistbt =  new BankTransaction(1, LocalDate.now(), 3000.0);
		when(btnxdao.save(any(BankTransaction.class))).thenReturn(persistbt);
		
	}
	@Test
	@DisplayName(value = "test for making payment success")
	 void testMakePayment1 () throws OrderProductsNotFoundException, BankAccountNotFoundException, BankDetailsDidntMatchException, NotSufficientBalanceException {
		PaymentReqDto paydto = new PaymentReqDto(1, 123, LocalDate.of(2022, 10, 10), "riju", 123);
		assertNotNull(service.makePayment(paydto));
	}
	@Test
	@DisplayName(value = "test for making payment order not found")
	 void testMakePayment2 () throws OrderProductsNotFoundException, BankAccountNotFoundException, BankDetailsDidntMatchException, NotSufficientBalanceException {
		PaymentReqDto paydto = new PaymentReqDto(2, 123, LocalDate.of(2022, 10, 10), "riju", 123);
		assertThrows(OrderProductsNotFoundException.class, ()->service.makePayment(paydto));
	}
	@Test
	@DisplayName(value = "test for making payment Bank account not found")
	 void testMakePayment3 () throws OrderProductsNotFoundException, BankAccountNotFoundException, BankDetailsDidntMatchException, NotSufficientBalanceException {
		PaymentReqDto paydto = new PaymentReqDto(1, 123, LocalDate.of(2022, 10, 10), "riju", 100);
		assertThrows(BankAccountNotFoundException.class ,()->service.makePayment(paydto));
	}
	@Test
	@DisplayName(value = "test for making payment Bank details did not match")
	 void testMakePayment4 () throws OrderProductsNotFoundException, BankAccountNotFoundException, BankDetailsDidntMatchException, NotSufficientBalanceException {
		PaymentReqDto paydto = new PaymentReqDto(1, 120, LocalDate.of(2022, 10, 10), "riju", 123);
		assertThrows(BankDetailsDidntMatchException.class ,()->service.makePayment(paydto));
	}
	@Test
	@DisplayName(value = "test for making payment not sufficient balance")
	 void testMakePayment5 () throws OrderProductsNotFoundException, BankAccountNotFoundException, BankDetailsDidntMatchException, NotSufficientBalanceException {
		PaymentReqDto paydto = new PaymentReqDto(3, 123, LocalDate.of(2022, 10, 10), "riju", 123);
		assertThrows(NotSufficientBalanceException.class ,()->service.makePayment(paydto));
	}
}
