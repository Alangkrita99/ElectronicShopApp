package com.cg;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

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

import com.cg.eshop.dao.IBasketDao;
import com.cg.eshop.dao.ICustomerDao;
import com.cg.eshop.dao.IElectronicProductDetailsDao;
import com.cg.eshop.dao.IOrderProductsDao;
import com.cg.eshop.dao.IOrderedProductDetailsDao;
import com.cg.eshop.entity.Basket;
import com.cg.eshop.entity.Customer;
import com.cg.eshop.entity.ElectronicProductDetails;
import com.cg.eshop.entity.OrderProducts;
import com.cg.eshop.exception.BasketException;
import com.cg.eshop.exception.CustomerNotFoundException;
import com.cg.eshop.service.IOrderedProductService;
import com.cg.eshop.service.OrderedProductImpl;

@SpringBootTest
public class TestCreateOrder {
	@Mock
	private ICustomerDao customerDao; 
	@Mock
	private IOrderProductsDao orderProductsDao;
	@Mock
	private IOrderedProductDetailsDao orderedProductDetailsDao;
	@Mock 
	private IBasketDao cartDao;
	@Mock 
	private IElectronicProductDetailsDao electronicProductDetailsDao;
	@InjectMocks
	private IOrderedProductService orderProductService = new OrderedProductImpl();
	
	@BeforeEach
	public void beforeEach() {
		List<Basket> basketlst = new ArrayList<>();
		List<Basket> basketlst1 = new ArrayList<>();
		Optional<Customer> customer1= Optional.of(new Customer(1001,"ram","ram@gmail.com","1234567891","ram_address","123456","pune","Maharashtra","India"));
		Optional<Customer> customer3= Optional.of(new Customer(1006,"ram","ram@gmail.com","1234567891","ram_address","123456","pune","Maharashtra","India"));
		Optional<Customer> customer2 =Optional.empty();
		OrderProducts orderProduct = new OrderProducts(100,LocalDate.of(2019, 03, 01),customer1.get(),2000.0,"ordered");
		basketlst.add(new Basket(100, customer1.get(), new ElectronicProductDetails(1001,"MOB_101","mi_mobile",1000.0,"mobile_image",LocalDate.now(),40)));
		basketlst.add(new Basket(101, customer1.get(), new ElectronicProductDetails(1002,"TV_101","tv",1000.0,"tv_image",LocalDate.of(2019, 07, 05),40)));
		when(customerDao.findById(customer1.get().getCustomerId())).thenReturn(customer1);
		when(customerDao.findById(1005)).thenReturn(customer2);
		when(customerDao.findById(customer3.get().getCustomerId())).thenReturn(customer3);
		when(cartDao.getItemsFromBasket(customer1.get().getCustomerId())).thenReturn(basketlst);
		when(cartDao.getItemsFromBasket(1006)).thenReturn(basketlst1);
		when(orderProductsDao.save(any(OrderProducts.class))).thenReturn(orderProduct);
		
		
	}
	
	@Test
	@DisplayName(value = "test of Create Order positive")
	public void testCreateOrder1() throws CustomerNotFoundException, BasketException {
		System.out.println(orderProductService.createOrder(1001));
		assertTrue(orderProductService.createOrder(1001)==100);
		
	}
	
	// customer Not found exception
	@Test
	@DisplayName(value = "test of Create Order for customer not found")
	public void testCreateOrder2() {
		assertThrows(CustomerNotFoundException.class, ()->orderProductService.createOrder(1002));
	}
	
	// Basket exception
		@Test
		@DisplayName(value = "test of Create Order for Basket Exception")
		public void testCreateOrder3() {
			assertThrows(BasketException.class, ()->orderProductService.createOrder(1006));
		}

}
