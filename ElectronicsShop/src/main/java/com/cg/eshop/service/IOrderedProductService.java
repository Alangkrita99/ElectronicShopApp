package com.cg.eshop.service;

import java.util.List;

import com.cg.eshop.entity.OrderProducts;
import com.cg.eshop.entity.OrderedProductDetails;
import com.cg.eshop.exception.BankAccountNotFoundException;
import com.cg.eshop.exception.BasketException;
import com.cg.eshop.exception.CustomerNotFoundException;
import com.cg.eshop.exception.OrderProductsNotFoundException;
import com.cg.eshop.exception.ProductNotFoundException;

public interface IOrderedProductService {
	
	public Integer createOrder(Integer customerId) throws CustomerNotFoundException,BasketException;
	
	public List<OrderProducts> viewOrderByUserId(Integer custId) throws OrderProductsNotFoundException,CustomerNotFoundException;
	
	public OrderProducts cancelOrder(Integer orderId) throws OrderProductsNotFoundException, ProductNotFoundException, BankAccountNotFoundException;
	
	public List<OrderedProductDetails> displayOrderDetails(Integer orderId)throws OrderProductsNotFoundException;


}
