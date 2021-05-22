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
import com.cg.eshop.dao.IOrderedProductDetailsDao;
import com.cg.eshop.entity.Category;
import com.cg.eshop.entity.Customer;
import com.cg.eshop.entity.ElectronicProductDetails;
import com.cg.eshop.entity.OrderProducts;
import com.cg.eshop.entity.OrderedProductDetails;
import com.cg.eshop.exception.CustomerNotFoundException;
import com.cg.eshop.exception.OrderProductsNotFoundException;
import com.cg.eshop.service.IOrderedProductService;
import com.cg.eshop.service.OrderedProductImpl;

@SpringBootTest
public class TestDisplayOrderDetails {
	
	@Mock
	private ICustomerDao customerDao; 
	@Mock
	private IOrderProductsDao orderProductsDao;
	@Mock
	private IOrderedProductDetailsDao orderedProductDetailsDao;
	@InjectMocks
	private IOrderedProductService orderProductService = new OrderedProductImpl();
	
	@BeforeEach
	public void beforeEach() throws OrderProductsNotFoundException, CustomerNotFoundException {
		List<OrderedProductDetails> lst = new ArrayList<>();
		ElectronicProductDetails electronicProduct = new ElectronicProductDetails(1001,"MOB_101","mi_mobile",1000.0,"mobile_image",LocalDate.now(),40);
		ElectronicProductDetails electronicProduct1 = new ElectronicProductDetails(1002,"TV_101","tv",1000.0,"tv_image",LocalDate.of(2019, 07, 05),40);
		Optional<Customer> customer1= Optional.of(new Customer(1001,"ram","ram@gmail.com","1234567891","ram_address","123456","pune","Maharashtra","India"));
		Optional<OrderProducts> orderProduct = Optional.of( new OrderProducts(11,LocalDate.now(),customer1.get(),2500.0,"ordered"));
		Optional<OrderProducts> orderProduct1 = Optional.empty();
		lst.add(new OrderedProductDetails(1,orderProduct.get(),electronicProduct));
		lst.add(new OrderedProductDetails(2,orderProduct.get(),electronicProduct1));
		when(orderProductsDao.findById(orderProduct.get().getOrderId())).thenReturn(orderProduct);
		when(orderProductsDao.findById(12)).thenReturn(orderProduct1);
		when(orderedProductDetailsDao.getProductDetailsInOrder(orderProduct.get().getOrderId())).thenReturn(lst);
		
	}
	@Test
	@DisplayName(value ="test for display order details for orderId 11")
	public void testdisplayOrderDetails1() throws OrderProductsNotFoundException{
		assertTrue(orderProductService.displayOrderDetails(11).size()>0);
	}
	
	@Test
	@DisplayName(value ="test for display order details for orderId 12")
	public void testdisplayOrderDetails2() throws OrderProductsNotFoundException{
//		assertTrue(orderProductService.displayOrderDetails(12).size()>0);
		assertThrows(OrderProductsNotFoundException.class, ()->orderProductService.displayOrderDetails(12));
		
	}

}
