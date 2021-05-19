package com.cg.eshop.service;

import java.util.List;

import javax.validation.Valid;

import com.cg.eshop.dto.AddProductDto;
import com.cg.eshop.dto.CategoryDto;
import com.cg.eshop.dto.ElectronicProductDetaisDto;
import com.cg.eshop.entity.Category;
import com.cg.eshop.entity.ElectronicProductDetails;
import com.cg.eshop.exception.CategoryNameNotFoundException;
import com.cg.eshop.exception.CategoryNotFoundException;
import com.cg.eshop.exception.ProductNotFoundException;

public interface ICategoryService {
	public Integer addCategory(CategoryDto categorydto);

	public List<Category> viewAllCategory() throws CategoryNotFoundException;

	public List<ElectronicProductDetails> getProductDetailsByCategoryName(String categoryName)
			throws ProductNotFoundException;
}
