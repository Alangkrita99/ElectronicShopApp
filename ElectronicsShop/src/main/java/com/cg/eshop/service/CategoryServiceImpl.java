package com.cg.eshop.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.eshop.dao.ICategoryDao;
import com.cg.eshop.dto.CategoryDto;
import com.cg.eshop.entity.Category;
import com.cg.eshop.entity.ElectronicProductDetails;
import com.cg.eshop.exception.ProductNotFoundException;

@Service
@Transactional

public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private ICategoryDao categoryDao;

	@Override
	public Integer addCategory(CategoryDto categorydto) {
		Category category = new Category();
		category.setCategoryName(categorydto.getCategoryName());
		Category newCategory = categoryDao.save(category);
		return newCategory.getCategoryId();

	}

	@Override
	public List<Category> viewAllCategory() {

		return categoryDao.findAll();
	}

	@Override
	public List<ElectronicProductDetails> getProductDetailsByCategoryName(String categoryName)
			throws ProductNotFoundException {
		List<ElectronicProductDetails> productDetailsList = categoryDao
				.getElectronicProductsByCategoryName(categoryName);
		if (productDetailsList.isEmpty())
			throw new ProductNotFoundException();
		return productDetailsList;

	}

}
