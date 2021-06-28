package com.cg.eshop.service;

import java.util.List;

import com.cg.eshop.dto.CategoryDto;
import com.cg.eshop.entity.Category;
import com.cg.eshop.entity.ElectronicProductDetails;
import com.cg.eshop.exception.CategoryNotFoundException;
import com.cg.eshop.exception.ProductNotFoundException;

public interface ICategoryService {
	public Integer addCategory(CategoryDto categorydto);

	public List<Category> getAllCategory() throws CategoryNotFoundException;

	public List<ElectronicProductDetails> getProductDetailsByCategoryName(String categoryName)
			throws ProductNotFoundException;
}
