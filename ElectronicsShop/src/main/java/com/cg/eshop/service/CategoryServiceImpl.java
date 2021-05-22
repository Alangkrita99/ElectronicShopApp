/**
 * @author ALANGKRITA CHAKRABORTY
 * @Version : 1.0
 * @Description : This Service Class contains the service regarding ElectronicProductDetails Management
 */
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

	/**
	 * @return new category
	 * @description This method returns the new added category in the category list
	 * @createdAt 17-May-2021
	 */
	@Override
	public Integer addCategory(CategoryDto categorydto) {
		Category category = new Category();
		category.setCategoryName(categorydto.getCategoryName());
		Category newCategory = categoryDao.save(category);
		return newCategory.getCategoryId();

	}

	/**
	 * @param categoryList Category
	 * @return List<Category>
	 * @description This method returns the list of category
	 * @createdAt 17-May-2021
	 */

	@Override
	public List<Category> viewAllCategory() {
		List<Category> categoryList = categoryDao.findAll();

		return categoryList;
	}

	/**
	 * @param ProductdetailsList ElectronicProductDetails
	 * @return productDetailsList
	 * @throws ProductNotFoundException if no product is found by categoryName
	 * @description This method returns product details list
	 * @createdAt 17-May-2021
	 */
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
