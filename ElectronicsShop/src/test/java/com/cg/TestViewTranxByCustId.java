package com.cg;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
import com.cg.eshop.entity.BankTransaction;
import com.cg.eshop.entity.Customer;
import com.cg.eshop.exception.CustomerNotFoundException;
import com.cg.eshop.exception.OrderProductsNotFoundException;
import com.cg.eshop.exception.TransactionNotFoundException;
import com.cg.eshop.service.IPaymentService;
import com.cg.eshop.service.PaymentServiceImpl;

@SpringBootTest
 class TestViewTranxByCustId {
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
	private void beforeEach() {

		Optional<Customer> customer1 = Optional.of(new Customer(1001, "riju", "riju@gmail.com", "1234567891",
				"riju_address", "123456", "pune", "Maharashtra", "India"));
		Optional<Customer> customer2 = Optional.empty();
		when(customerDao.findById(1001)).thenReturn(customer1);
		when(customerDao.findById(1002)).thenReturn(customer1);
		when(customerDao.findById(1003)).thenReturn(customer2);
		List<BankTransaction> btlst = new ArrayList<>();
		List<BankTransaction> btlst1 = new ArrayList<>();

		btlst.add(new BankTransaction(1, LocalDate.of(2021, 12, 2), 300.0));
		when(btnxdao.viewAllBankTransaction(1001)).thenReturn(btlst);
		when(btnxdao.viewAllBankTransaction(1002)).thenReturn(btlst1);

	}

	@Test
	@DisplayName(value = "test view by customer id for 1001")
	 void testViewTranxbyCustID1()
			throws TransactionNotFoundException, CustomerNotFoundException, OrderProductsNotFoundException {
		assertTrue(service.getPaymentbyCustID(1001).size() > 0);
	}

	@Test
	@DisplayName(value = "test view by customer id for 1002")
	 void testViewTranxbyCustID2() throws TransactionNotFoundException, CustomerNotFoundException {
		assertThrows(CustomerNotFoundException.class, () -> service.getPaymentbyCustID(1003));
	}

	@Test
	@DisplayName(value = "test view by customer id for 1002")
	 void testViewTranxbyCustID3() throws TransactionNotFoundException, CustomerNotFoundException {
		assertThrows(TransactionNotFoundException.class, () -> service.getPaymentbyCustID(1002));
	}
}
