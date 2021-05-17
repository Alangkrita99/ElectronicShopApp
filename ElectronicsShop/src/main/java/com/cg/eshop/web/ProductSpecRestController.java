package com.cg.eshop.web;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.eshop.dto.ElectronicProductSpecsDto;
import com.cg.eshop.dto.SuccessMessage;
import com.cg.eshop.entity.ElectronicProductSpecs;
import com.cg.eshop.exception.NoSpecsException;
import com.cg.eshop.exception.ProductNotFoundException;
import com.cg.eshop.exception.ValidateException;
import com.cg.eshop.service.IProductSpecificationService;
import com.cg.eshop.utils.ProductConstants;

@RestController
public class ProductSpecRestController {
	@Autowired
	private IProductSpecificationService specService;

	Logger logger = LoggerFactory.getLogger(ProductSpecRestController.class);

	@PostMapping("addspecs")
	public SuccessMessage addProductSpecs(@Valid @RequestBody ElectronicProductSpecsDto electronicProductSpecsDto,
			BindingResult br) throws ProductNotFoundException, ValidateException {

		logger.info(electronicProductSpecsDto.getSpecName());
		if (br.hasErrors())
			throw new ValidateException(br.getFieldErrors());
		ElectronicProductSpecs obj = specService.addSpecs(electronicProductSpecsDto);
		return new SuccessMessage(ProductConstants.PRODUCT_SPEC_ADDED + obj.getSpecId());
	}

	@GetMapping("viewspecs/{prod_id}")
	public List<ElectronicProductSpecs> getProductSpecs(@PathVariable("prod_id") Integer productID)
			throws ProductNotFoundException, NoSpecsException {
		logger.info(productID + "");
		return specService.getProductSpecsById(productID);
	}

}
