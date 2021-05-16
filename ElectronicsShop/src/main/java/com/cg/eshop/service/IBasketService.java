package com.cg.eshop.service;

import java.util.List;

import com.cg.eshop.dto.BasketDto;
import com.cg.eshop.dto.Productdto;
import com.cg.eshop.entity.Basket;
import com.cg.eshop.exception.CustomerNotFoundException;
import com.cg.eshop.exception.ProductNotFoundException;

public interface IBasketService {
	
	public List<Basket> viewItems(Integer custId) throws CustomerNotFoundException;
	public List<Basket> viewItemsByProduct(Integer proId) throws ProductNotFoundException;
	public Integer addItem(BasketDto basketdto) throws ProductNotFoundException, CustomerNotFoundException;
	public boolean removeItem(int custId,int prodId) throws ProductNotFoundException, CustomerNotFoundException;

}
