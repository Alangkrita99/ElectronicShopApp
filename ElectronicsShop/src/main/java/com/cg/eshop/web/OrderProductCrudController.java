/**
 * @author Atanu Chowdhury
 * @Version : 1.0
 * @Description : This Controller Class manages the RestController for Order Management 
 */
package com.cg.eshop.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cg.eshop.dto.SuccessMessage;
import com.cg.eshop.entity.OrderProducts;
import com.cg.eshop.entity.OrderedProductDetails;
import com.cg.eshop.exception.BankAccountNotFoundException;
import com.cg.eshop.exception.BasketException;
import com.cg.eshop.exception.CustomerNotFoundException;
import com.cg.eshop.exception.OrderProductsNotFoundException;
import com.cg.eshop.exception.ProductNotFoundException;
import com.cg.eshop.service.IOrderedProductService;
import com.cg.eshop.utils.OrderConstants;

@CrossOrigin(origins = "*")
@RestController
public class OrderProductCrudController {
	@Autowired
	private IOrderedProductService orderProductService;
	
	/**
	 * @param custId CustomerId
	 * @return SuccessMessage 
	 * @throws BasketException ,if Basket is empty for a customer Id
	 * @throws CustomerNotFoundException, if Customer Id not found
	 * @description This method for adding Order Product for a Customer Id
	 * @createdAt 17-May-2021 
	 */
	@GetMapping("addorderproduct/{custId}")
	public SuccessMessage addOrderProduct(@PathVariable("custId") Integer custId) throws CustomerNotFoundException, BasketException {
		int prodOrderId = orderProductService.createOrder(custId);
		return new SuccessMessage(OrderConstants.ORDER_CREATED+prodOrderId);
		
	}
	/**
	 * @param custId CustomerId
	 * @return List<OrderProduct> 
	 * @throws OrderProductsNotFoundException ,if orderProducts is empty for a customer Id
	 * @throws CustomerNotFoundException, if Customer Id not found
	 * @description This method for view orders by Customer Id
	 * @createdAt 17-May-2021 
	 */
	@GetMapping("viewordersbycustid/{custid}")
	public List<OrderProducts> viewOrderByUserId(@PathVariable("custid") Integer custId) throws OrderProductsNotFoundException, CustomerNotFoundException{
		return orderProductService.viewOrderByUserId(custId);
		
	}
	/**
	 * @param orderId Id
	 * @return List<OrderProduct> 
	 * @throws OrderProductsNotFoundException ,if orderProducts is empty for a customer Id
	 * @description This method for view order Details by Customer Id
	 * @createdAt 17-May-2021 
	 */
	@GetMapping("vieworderdetails/{orderId}")
	public List<OrderedProductDetails> viewOrderDetails(@PathVariable("orderId") Integer orderId) throws OrderProductsNotFoundException{
		return orderProductService.displayOrderDetails(orderId);
		
	}
	/**
	 * @param orderId Id
	 * @return List<OrderProduct> 
	 * @throws OrderProductsNotFoundException ,if orderProducts is empty for a customer Id
	 * @throws ProductNotFoundException ,if Product is not found 
	 * @throws BankAccountNotFoundException ,if Product is not found
	 * @description This method for view order Details by Customer Id
	 * @createdAt 17-May-2021 
	 */
	
	@GetMapping("cancelorder/{orderId}")
	public OrderProducts cancelOrder(@PathVariable("orderId") Integer orderId) throws OrderProductsNotFoundException, ProductNotFoundException, BankAccountNotFoundException{
		return orderProductService.cancelOrder(orderId);
		
	}
	

}
