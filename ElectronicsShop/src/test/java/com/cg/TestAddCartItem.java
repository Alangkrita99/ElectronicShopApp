package com.cg;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.cg.eshop.dao.IBasketDao;
import com.cg.eshop.dao.ICustomerDao;
import com.cg.eshop.dao.IElectronicProductDetailsDao;
import com.cg.eshop.dto.BasketDto;
import com.cg.eshop.entity.Basket;
import com.cg.eshop.entity.Customer;
import com.cg.eshop.entity.ElectronicProductDetails;
import com.cg.eshop.exception.CustomerNotFoundException;
import com.cg.eshop.exception.ProductNotFoundException;
import com.cg.eshop.service.BasketServiceImpl;
import com.cg.eshop.service.IBasketService;

@SpringBootTest
class TestAddCartItem {

	@Mock
	private IBasketDao basketdao;

	@Mock
	private ICustomerDao custdao;

	@Mock
	private IElectronicProductDetailsDao productdao;

	@InjectMocks
	private IBasketService basketservice = new BasketServiceImpl();

	@BeforeEach
	void beforeEach() {
		Optional<Customer> cust1 = Optional.of(new Customer());
		when(custdao.findById(1001)).thenReturn(cust1);
		Optional<Customer> cust2 = Optional.empty();
		when(custdao.findById(1002)).thenReturn(cust2);
		Optional<ElectronicProductDetails> prod1 = Optional.of(new ElectronicProductDetails());
		when(productdao.findById(1001)).thenReturn(prod1);
		Optional<ElectronicProductDetails> prod2 = Optional.empty();
		when(productdao.findById(1002)).thenReturn(prod2);
		Basket persistedbkt = new Basket(21);
		when(basketdao.save(any(Basket.class))).thenReturn(persistedbkt);
	}

	@Test
	@DisplayName(value = "Add BasketItem for Customer 1001 & product 1001")
	 void testAddItem1() throws ProductNotFoundException, CustomerNotFoundException {
		BasketDto bdto = new BasketDto(21, 1001, 1001);
		assertNotNull(basketservice.addItem(bdto));
	}

	@Test
	@DisplayName(value = "Add BasketItem for Customer 1002 & product 1001")
	void testAddItem2() throws ProductNotFoundException, CustomerNotFoundException {
		BasketDto bdto = new BasketDto(1002, 1001);
		assertThrows(CustomerNotFoundException.class, () -> basketservice.addItem(bdto));
	}

	@Test
	@DisplayName(value = "Add BasketItem for Customer 1001 & product 1001")
    void testAddItem3() throws ProductNotFoundException, CustomerNotFoundException {
		BasketDto bdto = new BasketDto(21, 1001, 1001);
		assertEquals(21,basketservice.addItem(bdto));
	}

	@Test
	@DisplayName(value = "Add BasketItem for Customer 1001 & product 1002")
	void testAddItem4() throws ProductNotFoundException, CustomerNotFoundException {
		BasketDto bdto = new BasketDto(1001, 1002);
		assertThrows(ProductNotFoundException.class, () -> basketservice.addItem(bdto));
	}

	@Test
	@DisplayName(value = "Add BasketItem for Customer 1002 & product 1002")
	void testAddItem5() throws ProductNotFoundException, CustomerNotFoundException {
		BasketDto bdto = new BasketDto(1002, 1002);
		assertThrows(CustomerNotFoundException.class, () -> basketservice.addItem(bdto));
	}

}
