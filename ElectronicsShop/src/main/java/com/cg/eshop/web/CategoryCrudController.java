/**
 * @author ALANGKRITA CHAKRABORTY
 * @Version : 1.0
 * @Description : This Controller Class manages the RestController for Category Management
 */
package com.cg.eshop.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.eshop.dto.CategoryDto;
import com.cg.eshop.dto.SuccessMessage;
import com.cg.eshop.entity.Category;
import com.cg.eshop.entity.ElectronicProductDetails;
import com.cg.eshop.exception.CategoryNameNotFoundException;
import com.cg.eshop.exception.CategoryNotFoundException;
import com.cg.eshop.exception.ProductNotFoundException;
import com.cg.eshop.service.ICategoryService;
import com.cg.eshop.utils.CategoryConstants;

@CrossOrigin(origins = "*")
@RestController
public class CategoryCrudController {
	@Autowired
	private ICategoryService categoryService;
	
	/**
	 * @return Success Message
	 * @description This method returns Success Message when a new category is added
	 * @createdAt 18-May-2021 
	 */
	@PostMapping("addcategory")
	public SuccessMessage addCategory(@Valid @RequestBody CategoryDto categoryDto) {
		Integer categoryId= categoryService.addCategory(categoryDto);
		return new SuccessMessage(CategoryConstants.SUCESS_MESSAGE+ categoryId);
		
	}
	/**
	 * @return category list 
	 * @throws CategoryNotFoundException, if Category Id not found
	 * @description This method returns the category list
	 * @createdAt 18-May-2021 
	 */
	@GetMapping("viewallcategory")
	public List<Category> viewAllCategory() throws CategoryNotFoundException {
		List<Category> categoryLst= categoryService.getAllCategory();
		if(categoryLst.isEmpty())
			throw new CategoryNotFoundException(CategoryConstants.CATEGORY_NOT_FOUND);
		return categoryLst;
	}
	/**
	 * @return product details list 
	 * @throws ProductNotFoundException when product details list is empty
	 * @throws CategoryNotFoundException, if Category Id not found
	 * @description This method returns the product details list
	 * @createdAt 18-May-2021 
	 */

	@GetMapping("viewallproductsbycategory/{categoryName}")
	public List<ElectronicProductDetails> viewAllProductsByCategory(@PathVariable("categoryName")String categoryName) throws ProductNotFoundException, CategoryNameNotFoundException {
		List<ElectronicProductDetails> productDetailsLst= categoryService.getProductDetailsByCategoryName(categoryName);
		if(productDetailsLst.isEmpty())
			throw new ProductNotFoundException(CategoryConstants.PRODUCT_NOT_FOUND_FOR_CATEGORY);
		return productDetailsLst;
	}

}
