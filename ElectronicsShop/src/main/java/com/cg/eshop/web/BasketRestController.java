/**
 * @author SUKANYA BISWAS
 * @Version : 1.0
 * @Description : This Controller Class manages the RestController for Basket Management 
 */
package com.cg.eshop.web;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public SuccessMessage addItemsInBasket(@Valid @RequestBody BasketDto basketRequestDto, BindingResult br) throws ProductNotFoundException, CustomerNotFoundException, ValidateException
	{
		if (br.hasErrors())
			throw new ValidateException(br.getFieldErrors());
	    int basketid = basketService.addItem(basketRequestDto);
		return new SuccessMessage(BasketConstants.BASKET_ITEM_ADDED+basketid);
		
	}
	
	@DeleteMapping("deleteallitemsbycustid/{cust_id}")
	public SuccessMessage deleteAllItems( @PathVariable("cust_id") Integer custId) throws CustomerNotFoundException, BasketException
	{
	    boolean itemdeleted = basketService.removeAllItem(custId);
		return new SuccessMessage(BasketConstants.BASKET_ITEM_DELETED+itemdeleted);
		
	}
	@DeleteMapping("deleteitembycartid/{cart_id}")
	public SuccessMessage deleteByCart( @PathVariable("cart_id") Integer cartId) throws BasketException
	{
	    boolean itemdeleted = basketService.removeByCartId(cartId);
		return new SuccessMessage(BasketConstants.BASKET_ITEM_DELETED+itemdeleted);
		
	}
	
	/**
	 * @param custId CustomerId
	 * @return ResponseEntity 
	 * @throws BasketException ,if Basket is empty for a customer Id
	 * @throws CustomerNotFoundException, if Customer Id not found
	 * @description This method returns ResponseStatus OK when Basket items found for Customer Id
	 * @createdAt 17-May-2021 
	 */
	@GetMapping("viewitems/{cust_id}")
	public ResponseEntity<List<Basket>> getitemsinbasket(@PathVariable("cust_id") Integer custID)
			throws CustomerNotFoundException, BasketException {
		logger.info(custID + "");
		List<Basket> bkt = basketService.viewItems(custID);
		return ResponseEntity.ok(bkt);
	}
	
	/**
	 * @return ResponseEntity 
	 * @throws BasketException ,if no basket items present
	 * @description This method returns all basket items
	 * @createdAt 17-May-2021 
	 */
	@GetMapping("viewallitems")
	public ResponseEntity<List<Basket>> getallitemsinbasket() throws BasketException {
		
		List<Basket> bkt = basketService.viewAllItems();
		return new ResponseEntity<List<Basket>>(bkt,HttpStatus.OK);
	}

	
	

}