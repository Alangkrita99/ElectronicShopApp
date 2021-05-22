/**
 * @author ALANGKRITA CHAKRABORTY
 * @Version : 1.0
 * @Description : This Service Class contains the service regarding ElectronicProductDetails Management
 */
package com.cg.eshop.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.eshop.dao.ICategoryDao;
import com.cg.eshop.dao.IElectronicProductDetailsDao;
import com.cg.eshop.dto.AddProductDto;
import com.cg.eshop.entity.Category;
import com.cg.eshop.entity.ElectronicProductDetails;
import com.cg.eshop.exception.CategoryNotFoundException;
import com.cg.eshop.exception.ProductNotFoundException;
import com.cg.eshop.utils.ProductConstants;

@Service
@Transactional
public class ElectronicProductDetailsServiceImpl implements IElectronicProductDetailService {
	@Autowired
	private IElectronicProductDetailsDao productDetailsDao;
	@Autowired
	private ICategoryDao categoryDao;
	
	/**
	 * @return added product
	 * @throws CategoryNotFoundException if category is invalid
	 * @description This method returns the added product to the list
	 * @createdAt 17-May-2021 
	 */
	
	@Override
	public ElectronicProductDetails addEletronicProduct(AddProductDto addProductDto) throws CategoryNotFoundException {
		ElectronicProductDetails newProduct = new ElectronicProductDetails();
		newProduct.setCode(addProductDto.getCode());
		newProduct.setCreatedate(LocalDate.now());
		newProduct.setImage(addProductDto.getImage());
		newProduct.setName(addProductDto.getName());
		newProduct.setPrice(addProductDto.getPrice());
		newProduct.setStock(addProductDto.getStock());
		Optional<Category> category = categoryDao.findById(addProductDto.getCategoryId());
		if (!category.isPresent())
			throw new CategoryNotFoundException();
		Category newCategory = category.get();
		newProduct.setCategory(newCategory);
		ElectronicProductDetails addedProduct = productDetailsDao.save(newProduct);
		return addedProduct;
	}
	/**
	 * @return ProductDetails
	 * @throws ProductNotFoundException if product id is invalid
	 * @description This method returns the Product Details by ProductId
	 * @createdAt 17-May-2021 
	 */

	@Override
	public ElectronicProductDetails getProductDetailsByProductId(Integer productId) throws ProductNotFoundException {
		Optional<ElectronicProductDetails> optProdDetails = productDetailsDao.findById(productId);
		if (!optProdDetails.isPresent())
			throw new ProductNotFoundException(ProductConstants.PRODUCT_NOT_FOUND);
		return optProdDetails.get();
	}

	@Override
	public List<ElectronicProductDetails> getProductDetailsByCategoryName(String categoryName)
			throws ProductNotFoundException {
		return null;
	}

}
