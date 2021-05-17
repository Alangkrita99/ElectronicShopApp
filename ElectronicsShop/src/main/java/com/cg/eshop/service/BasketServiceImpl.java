package com.cg.eshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.eshop.dao.IBasketDao;
import com.cg.eshop.dao.ICustomer;
import com.cg.eshop.dao.IProductDetails;
import com.cg.eshop.dto.BasketDto;
import com.cg.eshop.entity.Basket;
import com.cg.eshop.entity.Customer;
import com.cg.eshop.entity.ElectronicProductDetails;
import com.cg.eshop.exception.CustomerNotFoundException;
import com.cg.eshop.exception.ProductNotFoundException;

@Service("BasketService")
@Transactional(readOnly = true)
public class BasketServiceImpl implements IBasketService{

	@Autowired
	IBasketDao basketdao;
	
	@Autowired
	ICustomer custdao;
	
	@Autowired
	IProductDetails productdao;
	
	@Override
	public List<Basket> viewItems(Integer custId) throws CustomerNotFoundException {
		List<Basket> bkt= basketdao.viewItems(custId);
		if(bkt.isEmpty())
			throw new CustomerNotFoundException("Customer Not Found");
		return bkt;
		
	}

	@Override
	@Transactional(readOnly = false)
	public boolean removeItem(int custid,int prodId) throws ProductNotFoundException,CustomerNotFoundException {
		
		List<Basket> bkt = viewItems(custid);
		if(bkt== null)
			throw new CustomerNotFoundException("Customer Not Found");
		List<Basket> pbkt=viewItemsByProduct(prodId);
		if(pbkt== null)
			throw new ProductNotFoundException("Basket is Empty");
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
		Customer cust = custdao.getOne(basketdto.getCustId());
		if(cust==null)
			throw new CustomerNotFoundException("Customer Not found");
		ElectronicProductDetails product = productdao.getOne(basketdto.getProdId());
		if(product == null)
			throw new ProductNotFoundException("Product Not Found");
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
			throw new ProductNotFoundException("Product Not Found");
		return bkt;
	}

}