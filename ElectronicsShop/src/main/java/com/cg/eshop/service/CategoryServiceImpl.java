package com.cg.eshop.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.eshop.dao.ICategoryDao;
import com.cg.eshop.dao.IElectronicProductDetailsDao;
import com.cg.eshop.dto.AddProductDto;
import com.cg.eshop.dto.CategoryDto;
import com.cg.eshop.dto.ElectronicProductDetaisDto;
import com.cg.eshop.entity.Category;
import com.cg.eshop.entity.ElectronicProductDetails;
import com.cg.eshop.exception.CategoryNameNotFoundException;
import com.cg.eshop.exception.CategoryNotFoundException;
import com.cg.eshop.exception.ProductNotFoundException;
import com.cg.eshop.utils.CategoryConstants;

@Service
@Transactional

public class CategoryServiceImpl implements ICategoryService{
	
	@Autowired
	private ICategoryDao categoryDao;
	@Autowired
	private IElectronicProductDetailsDao productDao;
	
	@Override
	public Integer addCategory(CategoryDto categorydto) {
		Category category=new Category();
		category.setCategoryName(categorydto.getCategoryName());
		Category newCategory= categoryDao.save(category);
		return newCategory.getCategoryId();
			
	
	}

	@Override
	public List<Category> viewAllCategory() {
		List<Category> categoryList=categoryDao.findAll();
		
		return categoryList;
	}

	@Override
	public List<ElectronicProductDetails> getProductDetailsByCategoryName(String categoryName)
			throws ProductNotFoundException{
		List<ElectronicProductDetails>productDetailsList=categoryDao.getElectronicProductsByCategoryName(categoryName);
		if(productDetailsList.isEmpty())
			throw new ProductNotFoundException();
		return productDetailsList;
		
	}

	
	

}
