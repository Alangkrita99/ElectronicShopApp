package com.cg.eshop.service;

import java.util.List;

import com.cg.eshop.dto.OrderProductRequestDto;
import com.cg.eshop.entity.ElectronicProductDetails;
import com.cg.eshop.entity.OrderProducts;
import com.cg.eshop.entity.OrderedProductDetails;
import com.cg.eshop.exception.BasketException;
import com.cg.eshop.exception.CustomerNotFoundException;
import com.cg.eshop.exception.OrderProductsNotFoundException;

public interface IOrderedProductService {
	
	public Integer createOrder(Integer customerId) throws CustomerNotFoundException,BasketException;
	
	public List<OrderProducts> viewOrderByUserId(Integer custId) throws OrderProductsNotFoundException,CustomerNotFoundException;
	
	
	public List<OrderedProductDetails> displayOrderDetails(Integer orderId)throws OrderProductsNotFoundException;


}
