package com.cg;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.cg.eshop.dao.IBasketDao;
import com.cg.eshop.entity.Basket;
import com.cg.eshop.exception.BasketException;
import com.cg.eshop.service.BasketServiceImpl;
import com.cg.eshop.service.IBasketService;
import com.cg.eshop.web.BasketRestController;
@SpringBootTest
class TestRemoveCartItem {
	@Mock
	private IBasketDao basketdao;
	@InjectMocks
	private IBasketService basketService = new BasketServiceImpl();
	
	
	@BeforeEach
	void beforeEach() throws BasketException
	{
		Optional<Basket> bkt1 = Optional.of(new Basket());
		when(basketdao.findById(11)).thenReturn(bkt1);
		Optional<Basket> bkt2 = Optional.empty();
		when(basketdao.findById(12)).thenReturn(bkt2);
	}
	
	@Test
	@DisplayName(value = "Test RemoveByCartId for 11")
	void testRemoveCartItem() throws BasketException
	{
		assertTrue(basketService.removeByCartId(11));
	}
	
	@Test
	@DisplayName(value = "Test RemoveByCartId for 12")
	void testRemoveCartItem2() throws BasketException
	{
		assertThrows(BasketException.class,()->basketService.removeByCartId(12));
	}

}
