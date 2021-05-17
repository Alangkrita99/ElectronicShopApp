package com.cg.eshop.web;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.eshop.dto.BasketDto;
import com.cg.eshop.dto.SuccessMessage;
import com.cg.eshop.entity.Basket;
import com.cg.eshop.exception.BasketException;
import com.cg.eshop.exception.CustomerNotFoundException;
import com.cg.eshop.exception.ProductNotFoundException;
import com.cg.eshop.exception.ValidateException;
import com.cg.eshop.service.IBasketService;
import com.cg.eshop.utils.BasketConstants;

@RestController
public class BasketRestController {
	
	@Autowired
	private IBasketService basketService;
	Logger logger = LoggerFactory.getLogger(BasketRestController.class);
	
	@PostMapping("additemsinbasket")
	public SuccessMessage addItemsInBasket(@Valid @RequestBody BasketDto basketRequestDto,BindingResult br) throws ProductNotFoundException, CustomerNotFoundException, ValidateException
	{
		if (br.hasErrors())
			throw new ValidateException(br.getFieldErrors());
	    int basketid = basketService.addItem(basketRequestDto);
		return new SuccessMessage(BasketConstants.BASKET_ITEM_ADDED+basketid);
		
	}
	@DeleteMapping("deleteitemsinbasket/{cust_id}/{prod_id}")
	public SuccessMessage deleteItemsInBasket( @PathVariable("cust_id") Integer custId,@PathVariable("prod_id") Integer prodId) throws ProductNotFoundException, CustomerNotFoundException, BasketException
	{
	    boolean itemdeleted = basketService.removeItem(custId, prodId);
		return new SuccessMessage(BasketConstants.BASKET_ITEM_DELETED+itemdeleted);
		
	}
	@GetMapping("viewitems/{cust_id}")
	public List<Basket> getitemsinbasket(@PathVariable("cust_id") Integer custID)
			throws ProductNotFoundException, CustomerNotFoundException {
		logger.info(custID + "");
		return basketService.viewItems(custID);
	}

	
	

}
