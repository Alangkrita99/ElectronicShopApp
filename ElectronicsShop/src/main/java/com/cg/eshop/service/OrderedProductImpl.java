/**
 * @author ATANU CHOWDHURY
 * @Version : 1.0
 * @Description : This Service Class contains the service regarding Order Management
 */




package com.cg.eshop.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.eshop.dao.IBankAccountDao;
import com.cg.eshop.dao.IBasketDao;
import com.cg.eshop.dao.ICustomerDao;
import com.cg.eshop.dao.IElectronicProductDetailsDao;
import com.cg.eshop.dao.IOrderProductsDao;
import com.cg.eshop.dao.IOrderedProductDetailsDao;
import com.cg.eshop.entity.BankAccount;
import com.cg.eshop.entity.Basket;
import com.cg.eshop.entity.Customer;
import com.cg.eshop.entity.ElectronicProductDetails;
import com.cg.eshop.entity.OrderProducts;
import com.cg.eshop.entity.OrderedProductDetails;
import com.cg.eshop.exception.BankAccountNotFoundException;
import com.cg.eshop.exception.BasketException;
import com.cg.eshop.exception.CustomerNotFoundException;
import com.cg.eshop.exception.OrderProductsNotFoundException;
import com.cg.eshop.exception.ProductNotFoundException;
import com.cg.eshop.utils.OrderConstants;
import com.cg.eshop.utils.PaymentConstants;
import com.cg.eshop.utils.ProductConstants;
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
	private IBasketDao cartDao;
	@Autowired
	private IBankAccountDao bankAccDao;
	// Create Order
	
	/**
	 * @param custId CustomerId
	 * @return  Integer OrderId
	 * @throws BasketException ,if Basket is empty for a customer Id
	 * @throws CustomerNotFoundException, if Customer Id not found
	 * @description This method create a Order for a customer Id
	 * @createdAt 15-May-2021 
	 */
	
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
		return savedOrder.getOrderId();
	}

	public double findTotalCost(List<Basket> lstBaskets) {
		double totalCost=0.0;
		for(Basket basket : lstBaskets) {
			totalCost=totalCost+basket.getProductDetails().getPrice();
		}
		return totalCost;
	}
	
	/**
	 * @param custId CustomerId
	 * @return  List<OrderProducts>
	 * @throws OrderProductsNotFoundException ,if the Customer have no Order
	 * @throws CustomerNotFoundException, if Customer Id not found
	 * @description This method is to View orders for a customer Id
	 * @createdAt 15-May-2021 
	 */
	
	
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
	
	/**
	 * @param orderId OrderId
	 * @return  List<OrderProductDetails>
	 * @throws OrderProductsNotFoundException ,if the Specific orderProducts is not Present
	 * @throws CustomerNotFoundException, if Customer Id not found
	 * @description This method is to View order Details for a order Id
	 * @createdAt 15-May-2021 
	 */
	
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
	
	/**
	 * @param orderId OrderId
	 * @return  OrderProducts
	 * @throws OrderProductsNotFoundException ,if the Specific orderProducts is not Present
	 * @throws ProductNotFoundException, if specific Product not present
	 * @description This method is to cancel a Order for a order Id
	 * @createdAt 15-May-2021 
	 */
	
	@Override
	public OrderProducts cancelOrder(Integer orderId) throws OrderProductsNotFoundException, ProductNotFoundException, BankAccountNotFoundException {
		
		Optional<OrderProducts> optDeletedOrder = orderProductsDao.findById(orderId);
		
		if(optDeletedOrder.isEmpty())
			throw new OrderProductsNotFoundException(OrderConstants.ORDER_NOT_FOUND);
		OrderProducts deletedProduct =optDeletedOrder.get();
		Integer custId = deletedProduct.getCustomer().getCustomerId();
		BankAccount bankAcc=bankAccDao.findByCustomer(custId);
		List<OrderedProductDetails> productDetails = orderedProductDetailsDao.findByProdOrders(deletedProduct);
		
		for(OrderedProductDetails prodDetail : productDetails) {
			// increment the product stock by 1
			Optional<ElectronicProductDetails> optElectronicProduct = electronicProductDetailsDao.findById(prodDetail.getElectronicProduct().getProductID());
			if(optElectronicProduct.isEmpty())
				throw new ProductNotFoundException(ProductConstants.PRODUCT_NOT_FOUND);
			ElectronicProductDetails electronicProduct = optElectronicProduct.get();
			refund(bankAcc.getBankId(),electronicProduct.getPrice());
			electronicProduct.setStock(electronicProduct.getStock()+OrderConstants.INCREMENT_BY_ONE);
			electronicProductDetailsDao.save(electronicProduct);
			orderedProductDetailsDao.delete(prodDetail);
	
		}
		orderProductsDao.delete(deletedProduct);
		return deletedProduct;
	}
	
	public void refund(Integer bankAccId,Double amount) throws BankAccountNotFoundException {
		Optional<BankAccount> bankAcc= bankAccDao.findById(bankAccId);
		if(bankAcc.isEmpty())
			throw new BankAccountNotFoundException(PaymentConstants.BANK_ACCOUNT_NOT_FOUND);
		BankAccount bankAccount= bankAcc.get();
		bankAccount.setAmount(bankAccount.getAmount()+amount);
		bankAccDao.save(bankAccount);

	}




}
