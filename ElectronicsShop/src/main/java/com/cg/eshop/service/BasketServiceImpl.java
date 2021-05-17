package com.cg.eshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.eshop.dao.ICartDao;
import com.cg.eshop.dao.ICustomerDao;
import com.cg.eshop.dao.IElectronicProductDetailsDao;
import com.cg.eshop.dto.BasketDto;
import com.cg.eshop.entity.Basket;
import com.cg.eshop.entity.Customer;
import com.cg.eshop.entity.ElectronicProductDetails;
import com.cg.eshop.exception.BasketException;
import com.cg.eshop.exception.CustomerNotFoundException;
import com.cg.eshop.exception.ProductNotFoundException;
import com.cg.eshop.utils.BasketConstants;

@Service("BasketService")
@Transactional(readOnly = true)
public class BasketServiceImpl implements IBasketService{

	@Autowired
	ICartDao basketdao;
	
	@Autowired
	ICustomerDao custdao;
	
	@Autowired
	IElectronicProductDetailsDao productdao;
	
	@Override
	public List<Basket> viewItems(Integer custId) throws CustomerNotFoundException {
		List<Basket> bkt= basketdao.getItemsFromBasket(custId);
		if(bkt.isEmpty())
			throw new CustomerNotFoundException(BasketConstants.CUSTOMER_NOT_FOUND);
		return bkt;
		
	}

	@Override
	@Transactional(readOnly = false)
	public boolean removeItem(int custid,int prodId) throws ProductNotFoundException,CustomerNotFoundException, BasketException {
		List<Basket> pbkt=viewItemsByProduct(prodId);
		List<Basket> bkt = viewItems(custid);
		if(pbkt== null && bkt==null)
			throw new BasketException(BasketConstants.BASKET_EMPTY);
		
		else if(bkt== null)
			throw new CustomerNotFoundException(BasketConstants.CUSTOMER_NOT_FOUND);
		else if(pbkt==null)
			throw new ProductNotFoundException(BasketConstants.PRODUCT_NOT_FOUND);

		for(Basket b:pbkt)
		{
			if(b.getCustomer().getCustomerId()==custid)
			{
				basketdao.delete(b);
			    return true;
			}
			
		}
		
		return false;
	}

	@Override
	@Transactional(readOnly = false)
	public Integer addItem(BasketDto basketdto) throws ProductNotFoundException, CustomerNotFoundException {
		Optional<Customer> customer = custdao.findById(basketdto.getCustId());
		if(!customer.isPresent())
			throw new CustomerNotFoundException(BasketConstants.CUSTOMER_NOT_FOUND);
		Optional<ElectronicProductDetails> prod = productdao.findById(basketdto.getProdId());
		if(!prod.isPresent())
			throw new ProductNotFoundException(BasketConstants.PRODUCT_NOT_FOUND);
		Customer cust = customer.get();
		ElectronicProductDetails product = prod.get();
		Basket basket=new Basket();
		basket.setCustomer(cust);
		basket.setProductDetails(product);
		Basket persistedBasket=basketdao.save(basket);
		return persistedBasket.getBasketId();
	}

	@Override
	public List<Basket> viewItemsByProduct(Integer proId) throws ProductNotFoundException {
		List<Basket> bkt= basketdao.viewItemsByProduct(proId);
		if(bkt==null)
			throw new ProductNotFoundException(BasketConstants.PRODUCT_NOT_FOUND);
		return bkt;
	}

}