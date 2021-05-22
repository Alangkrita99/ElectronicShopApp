package com.cg;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

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
import com.cg.eshop.entity.Basket;
import com.cg.eshop.entity.Customer;
import com.cg.eshop.exception.BasketException;
import com.cg.eshop.exception.CustomerNotFoundException;
import com.cg.eshop.service.BasketServiceImpl;
import com.cg.eshop.service.IBasketService;

@SpringBootTest
class TestRemoveCartByCustId {
	@Mock
	private IBasketDao basketdao;

	@Mock
	private ICustomerDao custdao;

	@InjectMocks
	private IBasketService basketService = new BasketServiceImpl();

	@BeforeEach
	void beforeEach() throws BasketException {

		Optional<Customer> cust1 = Optional.of(new Customer());
		when(custdao.findById(1001)).thenReturn(cust1);
		when(custdao.findById(1003)).thenReturn(cust1);
		Optional<Customer> cust2 = Optional.empty();
		when(custdao.findById(1002)).thenReturn(cust2);
		List<Basket> optbasket1 = new ArrayList<>();
		optbasket1.add(new Basket());
		optbasket1.add(new Basket());
		List<Basket> optbasket2 = new ArrayList<>();
		when(basketdao.getItemsFromBasket(1001)).thenReturn(optbasket1);
		when(basketdao.getItemsFromBasket(1003)).thenReturn(optbasket2);

	}

	@Test
	@DisplayName(value = "Test RemoveByCustId for 1001")
	void testRemoveCartItembyCust() throws BasketException, CustomerNotFoundException {
		assertTrue(basketService.removeAllItem(1001));
	}

	@Test
	@DisplayName(value = "Test RemoveByCustId for 1002")
	void testRemoveCartItem2() throws BasketException, CustomerNotFoundException {
		assertThrows(CustomerNotFoundException.class, () -> basketService.removeAllItem(1002));
	}

	@Test
	@DisplayName(value = "Test RemoveByCustId for 1003")
	void testRemoveCartItem3() throws BasketException, CustomerNotFoundException {
		assertThrows(BasketException.class, () -> basketService.removeAllItem(1003));
	}

}
