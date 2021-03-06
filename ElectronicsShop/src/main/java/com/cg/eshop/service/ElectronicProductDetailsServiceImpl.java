package com.cg.eshop.service;

import java.time.LocalDate;
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

	@Override
	public ElectronicProductDetails getProductDetailsByProductId(Integer productId) throws ProductNotFoundException {
		Optional<ElectronicProductDetails> optProdDetails = productDetailsDao.findById(productId);
		if (!optProdDetails.isPresent())
			throw new ProductNotFoundException(ProductConstants.PRODUCT_NOT_FOUND);
		return optProdDetails.get();
	}
}
