package com.cg.eshop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cg.eshop.entity.ElectronicProductDetails;
import com.cg.eshop.exception.ProductNotFoundException;
import com.cg.eshop.service.IElectronicProductDetailService;

@RestController
public class ElectronicProductDetailsCrudController {
	@Autowired
	private IElectronicProductDetailService electronicProductDetailService;

	@GetMapping("viewproductdetailbyproductid/{prod_id}")
	public ElectronicProductDetails getProductDetailsByProductId(@PathVariable("prod_id") Integer productId)
			throws ProductNotFoundException {
		return electronicProductDetailService.getProductDetailsByProductId(productId);
	}
}
