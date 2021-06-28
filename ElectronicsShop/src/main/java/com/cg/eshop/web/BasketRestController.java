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
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin(origins = "*")
@RestController
public class BasketRestController {

	@Autowired
	private IBasketService basketService;

	Logger logger = LoggerFactory.getLogger(BasketRestController.class);

	/**
	 * @param basketRequestDto BasketDto
	 * @param br BindingResult
	 * @return SuccessMessage
	 * @throws ValidateException,         if basketDto has any field errors
	 * @throws CustomerNotFoundException, if Customer Id not found
	 * @throws ProductNotFoundException,  if Product Id not found
	 * @description This method returns SuccessMessage when a Basket item added for a specific Customer Id and Product Id
	 * @createdAt 17-May-2021
	 */
	@PostMapping("additemsinbasket")
	public SuccessMessage addItemsInBasket(@Valid @RequestBody BasketDto basketRequestDto, BindingResult br)
			throws ProductNotFoundException, CustomerNotFoundException, ValidateException {
		if (br.hasErrors())
			throw new ValidateException(br.getFieldErrors());
		int basketid = basketService.addItem(basketRequestDto);
		return new SuccessMessage(BasketConstants.BASKET_ITEM_ADDED + basketid);

	}

	/**
	 * @param custId CustomerId
	 * @return SuccessMessage
	 * @throws BasketException, if Basket is empty for a customer Id
	 * @throws CustomerNotFoundException, if Customer Id not found
	 * @description This method returns SuccessMessage when all Basket items deleted for a Customer Id
	 * @createdAt 17-May-2021
	 */
	@DeleteMapping("deleteallitemsbycustid/{cust_id}")
	public SuccessMessage deleteAllItems(@PathVariable("cust_id") Integer custId)
			throws CustomerNotFoundException, BasketException {
		boolean itemdeleted = basketService.removeAllItem(custId);
		return new SuccessMessage(BasketConstants.BASKET_ITEM_DELETED + itemdeleted);

	}

	/**
	 * @param cartId CartId
	 * @return SuccessMessage
	 * @throws BasketException ,if Basket item is not present
	 * @description This method returns SuccessMessage when a Basket item is deleted
	 * @createdAt 17-May-2021
	 */
	@DeleteMapping("deleteitembycartid/{cart_id}")
	public SuccessMessage deleteByCart(@PathVariable("cart_id") Integer cartId) throws BasketException {
		boolean itemdeleted = basketService.removeByCartId(cartId);
		return new SuccessMessage(BasketConstants.BASKET_ITEM_DELETED + itemdeleted);

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
		return new ResponseEntity<List<Basket>>(bkt, HttpStatus.OK);
	}

	/**
	 * @return List<Basket>
	 * @throws BasketException ,if no basket items present
	 * @description This method returns all basket items
	 * @createdAt 17-May-2021
	 */
	@GetMapping("viewallitems")
	public List<Basket> getallitemsinbasket() throws BasketException {

		return basketService.viewAllItems();
	}

}
