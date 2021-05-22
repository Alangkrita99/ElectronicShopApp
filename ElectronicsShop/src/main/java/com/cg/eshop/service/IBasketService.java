package com.cg.eshop.service;

import java.util.List;

import com.cg.eshop.dto.BasketDto;
import com.cg.eshop.entity.Basket;
import com.cg.eshop.exception.BasketException;
import com.cg.eshop.exception.CustomerNotFoundException;
import com.cg.eshop.exception.ProductNotFoundException;

public interface IBasketService {

	public List<Basket> viewItems(Integer custId) throws BasketException, CustomerNotFoundException;

	public Integer addItem(BasketDto basketdto) throws ProductNotFoundException, CustomerNotFoundException;

	public boolean removeAllItem(int custId) throws CustomerNotFoundException, BasketException;

	public boolean removeByCartId(int cartId) throws BasketException;

	List<Basket> viewAllItems() throws BasketException;
}