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

import com.cg.eshop.dao.ICustomerDao;
import com.cg.eshop.dao.IOrderProductsDao;
import com.cg.eshop.entity.Customer;
import com.cg.eshop.entity.OrderProducts;
import com.cg.eshop.exception.CustomerNotFoundException;
import com.cg.eshop.exception.OrderProductsNotFoundException;
import com.cg.eshop.service.IOrderedProductService;
import com.cg.eshop.service.OrderedProductImpl;

@SpringBootTest
public class TestViewOrderByUserId {
	@Mock
	private ICustomerDao customerDao; 
	@Mock
	private IOrderProductsDao orderProductsDao;
	@InjectMocks
	private IOrderedProductService orderProductService = new OrderedProductImpl();
	
	@BeforeEach
	public void beforeEach() throws OrderProductsNotFoundException, CustomerNotFoundException {
		List<OrderProducts> lst = new  ArrayList<>();
		List<OrderProducts> lst1 = new  ArrayList<>();
		Optional<Customer> customer1= Optional.of(new Customer(1001,"ram","ram@gmail.com","1234567891","ram_address","123456","pune","Maharashtra","India"));
		Optional<Customer> customer2=Optional.empty();
		Optional<Customer> customer3= Optional.of(new Customer(1004,"sam","sam@gmail.com","1234567892","sam_address","123457","kolkata","west Bengal","India"));
		lst.add(new OrderProducts(1,LocalDate.now(),customer1.get(),2000.0,"ordered"));
		lst.add(new OrderProducts(2,LocalDate.now(),customer1.get(),2500.0,"ordered"));
		when(customerDao.findById(customer1.get().getCustomerId())).thenReturn(customer1);
		when(customerDao.findById(1003)).thenReturn(customer2);
		when(customerDao.findById(1004)).thenReturn(customer3);
		when(orderProductsDao.viewOrderByUserId(customer1.get().getCustomerId())).thenReturn(lst);
		when(orderProductsDao.viewOrderByUserId(customer3.get().getCustomerId())).thenReturn(lst1);
	}
	
	@Test
	@DisplayName(value ="test for view order for customerId 1001")
	public void testViewOrder1() throws OrderProductsNotFoundException, CustomerNotFoundException{
		assertTrue(orderProductService.viewOrderByUserId(1001).size()>0);
	}
	
	@Test
	@DisplayName(value = "test for view order for customerId 1003")
	public void testViewOrder2() throws OrderProductsNotFoundException, CustomerNotFoundException{
		assertThrows(CustomerNotFoundException.class, ()->orderProductService.viewOrderByUserId(1003));
	}
	
	@Test
	@DisplayName(value = "test for view order for customerId 1004")
	public void testViewOrder3() throws OrderProductsNotFoundException, CustomerNotFoundException{
		assertThrows(OrderProductsNotFoundException.class, ()->orderProductService.viewOrderByUserId(1004));
	}
	

}
