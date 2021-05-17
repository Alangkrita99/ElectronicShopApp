package com.cg.eshop.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.eshop.dao.ICartDao;
import com.cg.eshop.dao.ICustomerDao;
import com.cg.eshop.dao.IElectronicProductDetailsDao;
import com.cg.eshop.dao.IOrderProductsDao;
import com.cg.eshop.dao.IOrderedProductDetailsDao;
import com.cg.eshop.dto.OrderProductRequestDto;
import com.cg.eshop.entity.Basket;
import com.cg.eshop.entity.Customer;
import com.cg.eshop.entity.ElectronicProductDetails;
import com.cg.eshop.entity.OrderProducts;
import com.cg.eshop.entity.OrderedProductDetails;
import com.cg.eshop.exception.BasketException;
import com.cg.eshop.exception.CustomerNotFoundException;
import com.cg.eshop.exception.OrderProductsNotFoundException;
import com.cg.eshop.utils.OrderConstants;
@Service
@Transactional
public class OrderedProductImpl implements IOrderedProductService{
	@Autowired
	private IOrderProductsDao orderProductsDao;
	@Autowired
	private ICustomerDao customerDao;
	@Autowired
	private IOrderedProductDetailsDao orderedProductDetailsDao;
	@Autowired
	private IElectronicProductDetailsDao electronicProductDetailsDao;
	@Autowired
	private ICartDao cartDao;
	// Create Order
	@Override
	public Integer createOrder(Integer customerId) throws CustomerNotFoundException, BasketException {
		//updating the table cg_order_products
		OrderProducts orderProduct = new OrderProducts();

		Optional<Customer> optCust = customerDao.findById(customerId);
		
		if(!optCust.isPresent())
			throw new CustomerNotFoundException(OrderConstants.CUSTOMER_NOT_FOUND);
		Customer customer=optCust.get();
		List<Basket> lstbasket = cartDao.getItemsFromBasket(customerId);
		if(lstbasket.isEmpty()) 
			throw new BasketException(OrderConstants.BASKET_EMPTY);

		orderProduct.setOrderDate(LocalDate.now());
		orderProduct.setCustomer(customer);
		orderProduct.setOrderStatus(OrderConstants.ORDER_CONFIRM);
		orderProduct.setTotalCost(findTotalCost(lstbasket));
		OrderProducts savedOrder = orderProductsDao.save(orderProduct);
		
		for(Basket basket : lstbasket) {
			
			ElectronicProductDetails product = basket.getProductDetails();
			OrderedProductDetails orderprodDetail = new OrderedProductDetails();
			
			orderprodDetail.setElectronicProduct(product);
			orderprodDetail.setProdOrders(orderProduct);
			orderedProductDetailsDao.save(orderprodDetail);
			product.setStock(product.getStock()-OrderConstants.DECREMENT_BY_ONE);
			electronicProductDetailsDao.save(product);
			cartDao.delete(basket);
			
		}
//		orderProductsDao.save(orderProduct)


		return orderProduct.getOrderId();

	}
	public double findTotalCost(List<Basket> lstBaskets) {
		double totalCost=0.0;
		for(Basket basket : lstBaskets) {
			totalCost=totalCost+basket.getProductDetails().getPrice();
		}
		return totalCost;
	}
	
	
	
	
	// View Order By User Id
	@Override
	public List<OrderProducts> viewOrderByUserId(Integer custId) throws OrderProductsNotFoundException, CustomerNotFoundException {
		Optional<Customer> optCust = customerDao.findById(custId);
		
		if(!optCust.isPresent())
			throw new CustomerNotFoundException(OrderConstants.CUSTOMER_NOT_FOUND);
		
		List<OrderProducts> lst = orderProductsDao.viewOrderByUserId(custId);
		
		if(lst.isEmpty())
			throw new OrderProductsNotFoundException(OrderConstants.ORDER_EMPTY);
		lst.sort((e1,e2)->e1.getOrderDate().compareTo(e2.getOrderDate()));
		return lst;
	}
	
	@Override
	public List<OrderedProductDetails> displayOrderDetails(Integer orderId) throws OrderProductsNotFoundException {
		Optional<OrderProducts> optOrder = orderProductsDao.findById(orderId);
		if(!optOrder.isPresent())
			throw new OrderProductsNotFoundException(OrderConstants.ORDER_NOT_FOUND);
		List<OrderedProductDetails> orderProdDetails = orderedProductDetailsDao.getProductDetailsInOrder(orderId);
		if(orderProdDetails.isEmpty())
			throw new OrderProductsNotFoundException(OrderConstants.ORDER_EMPTY);
		orderProdDetails.sort((e1,e2)->e1.getProdOrderId().compareTo(e2.getProdOrderId()));
		return orderProdDetails;
	}




}
