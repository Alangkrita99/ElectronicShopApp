package com.cg.eshop.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.eshop.dao.IElectronicProductDetailsDao;
import com.cg.eshop.dao.IElectronicProductSpecsDao;
import com.cg.eshop.dto.ElectronicProductSpecsDto;
import com.cg.eshop.entity.ElectronicProductDetails;
import com.cg.eshop.entity.ElectronicProductSpecs;
import com.cg.eshop.exception.NoSpecsException;
import com.cg.eshop.exception.ProductNotFoundException;
import com.cg.eshop.utils.ProductConstants;

@Service
public class ProductSpecificationServiceImpl implements IProductSpecificationService {
	@Autowired
	private IElectronicProductDetailsDao productDao;
	@Autowired
	private IElectronicProductSpecsDao productSpecsDao;

	Logger logger = LoggerFactory.getLogger(ProductSpecificationServiceImpl.class);

	@Override
	public ElectronicProductSpecs addSpecs(ElectronicProductSpecsDto electronicProductSpecsDto)
			throws ProductNotFoundException {

		Optional<ElectronicProductDetails> optProduct = productDao.findById(electronicProductSpecsDto.getProductId());

		logger.info("" + optProduct.isPresent());
		if (!optProduct.isPresent())
			throw new ProductNotFoundException(ProductConstants.PRODUCT_NOT_FOUND);
		ElectronicProductSpecs electronicspecs = new ElectronicProductSpecs();
		electronicspecs.setSpecName(electronicProductSpecsDto.getSpecName());
		electronicspecs.setSpecValue(electronicProductSpecsDto.getSpecValue());
		electronicspecs.setElecProdDetails(optProduct.get());
		return productSpecsDao.save(electronicspecs);

	}

	@Override
	public List<ElectronicProductSpecs> getProductSpecsById(Integer productId)
			throws ProductNotFoundException, NoSpecsException {
		Optional<ElectronicProductDetails> optProduct = productDao.findById(productId);
		if (!optProduct.isPresent())
			throw new ProductNotFoundException(ProductConstants.PRODUCT_NOT_FOUND);
		List<ElectronicProductSpecs> lst = productSpecsDao.getSpecifications(productId);
		
		logger.info(""+lst.isEmpty());
		if (lst.isEmpty())
			throw new NoSpecsException(ProductConstants.PRODUCT_SPEC_EMPTY);
		lst.sort((p1, p2) -> p1.getSpecName().compareTo(p2.getSpecName()));
		return lst;
	}

}
