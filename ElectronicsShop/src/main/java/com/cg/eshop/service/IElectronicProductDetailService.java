package com.cg.eshop.service;

import java.util.List;

import com.cg.eshop.dto.AddProductDto;
import com.cg.eshop.entity.ElectronicProductDetails;
import com.cg.eshop.exception.CategoryNotFoundException;
import com.cg.eshop.exception.ProductNotFoundException;

public interface IElectronicProductDetailService {

	public ElectronicProductDetails addEletronicProduct(AddProductDto addProductDto) throws CategoryNotFoundException;

	public ElectronicProductDetails getProductDetailsByProductId(Integer productId) throws ProductNotFoundException;

	public List<ElectronicProductDetails> getProductDetailsByCategoryName(String categoryName) throws ProductNotFoundException;

	
}
