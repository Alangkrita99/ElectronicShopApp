package com.cg.eshop.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.eshop.dto.OrderProductRequestDto;
import com.cg.eshop.dto.SuccessMessage;
import com.cg.eshop.entity.OrderProducts;
import com.cg.eshop.entity.OrderedProductDetails;
import com.cg.eshop.exception.BasketException;
import com.cg.eshop.exception.CustomerNotFoundException;
import com.cg.eshop.exception.OrderProductsNotFoundException;
import com.cg.eshop.service.IOrderedProductService;
import com.cg.eshop.utils.OrderConstants;

@RestController
public class OrderProductCrudController {
	@Autowired
	private IOrderedProductService orderProductService;
	@GetMapping("addorderproduct/{custId}")
	public SuccessMessage addOrderProduct(@PathVariable("custId") Integer custId) throws CustomerNotFoundException, BasketException {
		int prodOrderId = orderProductService.createOrder(custId);
		return new SuccessMessage(OrderConstants.ORDER_CREATED+prodOrderId);
		
	}
	@GetMapping("viewordersbycustid/{custid}")
	public List<OrderProducts> viewOrderByUserId(@PathVariable("custid") Integer custId) throws OrderProductsNotFoundException, CustomerNotFoundException{
		return orderProductService.viewOrderByUserId(custId);
		
	}
	@GetMapping("vieworderdetails/{orderId}")
	public List<OrderedProductDetails> viewOrderDetails(@PathVariable("orderId") Integer orderId) throws OrderProductsNotFoundException{
		return orderProductService.displayOrderDetails(orderId);
		
	}
	

}
