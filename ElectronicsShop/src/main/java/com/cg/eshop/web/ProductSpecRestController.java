/**
 * @author SOUHARDYA RAY
 * @Version : 1.0
 * @Description : This Controller Class manages the RestController for Product Specifications 
 */
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
import org.springframework.web.bind.annotation.PutMapping;
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

	/**
	 * @param electronicProductSpecsDto ElectronicProductSpecsDto
	 * @return SuccessMessage
	 * @throws ProductNotFoundException ,if Product not found
	 * @throws ValidateException,       if ElectronicProductSpecsDto has any field
	 *                                  errors
	 * @description This method returns SuccessMessage when Product Specifications
	 *              is added for a given Product Id
	 * @createdAt 17-May-2021
	 */
	@PostMapping("addspecs")
	public SuccessMessage addProductSpecs(@Valid @RequestBody ElectronicProductSpecsDto electronicProductSpecsDto,
			BindingResult br) throws ProductNotFoundException, ValidateException {

		logger.info(electronicProductSpecsDto.getSpecName());
		if (br.hasErrors())
			throw new ValidateException(br.getFieldErrors());
		ElectronicProductSpecs obj = specService.addSpecs(electronicProductSpecsDto);
		return new SuccessMessage(ProductConstants.PRODUCT_SPEC_ADDED + obj.getSpecId());
	}

	/**
	 * @param productID Integer
	 * @return List<ElectronicProductSpecs>
	 * @throws ProductNotFoundException ,if Product not found
	 * @throws NoSpecsException,        if Product Specification not found
	 * @description This method returns the List of Product Specifications found for
	 *              a given Product Id
	 * @createdAt 17-May-2021
	 */
	@GetMapping("viewspecsbyproductid/{prod_id}")
	public List<ElectronicProductSpecs> getProductSpecsByProductId(@PathVariable("prod_id") Integer productID)
			throws ProductNotFoundException, NoSpecsException {
		logger.info(productID + "");
		return specService.getProductSpecsById(productID);
	}

	/**
	 * @param specID Integer
	 * @return ElectronicProductSpecs
	 * @throws ProductNotFoundException ,if Product not found
	 * @throws NoSpecsException,        if Product Specification not found
	 * @description This method returns a List of Product Specifications found for a
	 *              given Specification Id
	 * @createdAt 17-May-2021
	 */
	@GetMapping("viewspecsbyspecid/{spec_id}")
	public ElectronicProductSpecs getProductSpecsBySpecId(@PathVariable("spec_id") Integer specId)
			throws ProductNotFoundException, NoSpecsException {
		logger.info(specId + "");
		return specService.getProductSpecsBySpecId(specId);
	}

	/**
	 * @param electronicProductSpecsDto ElectronicProductSpecsDto
	 * @return String
	 * @throws ProductNotFoundException ,if Product not found
	 * @throws NoSpecsException,        if Product Specification not found
	 * @throws ValidateException,       if ElectronicProductSpecsDto has any field
	 *                                  errors
	 * @description This method returns a Success Message if Product Specifications
	 *              is edited for a given Specification Id
	 * @createdAt 17-May-2021
	 */
	@PutMapping("editspecs/{spec_id}")
	public String editProductSpecsBySpecId(@RequestBody ElectronicProductSpecsDto electronicProductSpecsDto,
			BindingResult br) throws ProductNotFoundException, NoSpecsException, ValidateException {
		if (br.hasErrors())
			throw new ValidateException(br.getFieldErrors());
		specService.editProductSpecsBySpecId(electronicProductSpecsDto);
		return ProductConstants.SPEC_EDIT_SUCCESSFUL;
	}

}
