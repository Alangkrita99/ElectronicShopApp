/**
 * @author SOUHARDYA RAY
 * @Version : 1.0
 * @Description : This Service Class contains the service regarding Product Specification Management
 */
package com.cg.eshop.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.eshop.dao.IElectronicProductDetailsDao;
import com.cg.eshop.dao.IElectronicProductSpecsDao;
import com.cg.eshop.dto.ElectronicProductSpecsDto;
import com.cg.eshop.entity.ElectronicProductDetails;
import com.cg.eshop.entity.ElectronicProductSpecs;
import com.cg.eshop.exception.NoSpecsException;
import com.cg.eshop.exception.ProductNotFoundException;
import com.cg.eshop.util.ProductConstants;

@Service
public class ProductSpecificationServiceImpl implements IProductSpecificationService {
	@Autowired
	private IElectronicProductDetailsDao productDao;
	@Autowired
	private IElectronicProductSpecsDao productSpecsDao;

	Logger logger = LoggerFactory.getLogger(ProductSpecificationServiceImpl.class);

	/**
	 * @param electronicProductSpecsDto ElectronicProductSpecsDto
	 * @return ElectronicProductSpecs
	 * @throws ProductNotFoundException, if Product not found
	 * @description This method adds Product Specifications for a given Product Id
	 * @createdAt 15-May-2021
	 */
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

	/**
	 * @param productId Integer
	 * @return List<ElectronicProductSpecs>
	 * @throws ProductNotFoundException, if Product not found
	 * @throws NoSpecsException,         if Product Specification not found
	 * @description This method returns a list of Product Specifications for a given
	 *              Product Id
	 * @createdAt 15-May-2021
	 */
	@Override
	public List<ElectronicProductSpecs> getProductSpecsByProductId(Integer productId)
			throws ProductNotFoundException, NoSpecsException {
		Optional<ElectronicProductDetails> optProduct = productDao.findById(productId);
		if (!optProduct.isPresent())
			throw new ProductNotFoundException(ProductConstants.PRODUCT_NOT_FOUND);
		List<ElectronicProductSpecs> lst = productSpecsDao.getSpecificationsByProductId(productId);

		logger.info("" + lst.isEmpty());
		if (lst.isEmpty())
			throw new NoSpecsException(ProductConstants.PRODUCT_SPEC_EMPTY);
		lst.sort((p1, p2) -> p1.getSpecName().compareTo(p2.getSpecName()));
		return lst;
	}

	/**
	 * @param specId Integer
	 * @return ElectronicProductSpecs
	 * @throws NoSpecsException,if Product Specification not found
	 * @description This method returns the Product Specifications for a given
	 *              Specification Id
	 * @createdAt 15-May-2021
	 */
	@Override
	public ElectronicProductSpecs getProductSpecsBySpecId(Integer specId) throws NoSpecsException {

		Optional<ElectronicProductSpecs> optSpec = productSpecsDao.findById(specId);
		if (!optSpec.isPresent())
			throw new NoSpecsException(ProductConstants.PRODUCT_SPEC_EMPTY);
		return optSpec.get();

	}

	/**
	 * @param electronicProductSpecsDto ElectronicProductSpecsDto
	 * @return true boolean
	 * @throws ProductNotFoundException, if Product not found
	 * @throws NoSpecsException,if       Product Specification not found
	 * @description This method edits the Product Specifications for a given
	 *              Specification Id
	 * @createdAt 15-May-2021
	 */
	@Override
	@Transactional(readOnly = false)
	public boolean editProductSpecsBySpecId(ElectronicProductSpecsDto electronicProductSpecsDto)
			throws ProductNotFoundException, NoSpecsException {
		Optional<ElectronicProductSpecs> optSpec = productSpecsDao.findById(electronicProductSpecsDto.getSpecId());
		if (!optSpec.isPresent())
			throw new NoSpecsException(ProductConstants.PRODUCT_SPEC_EMPTY);
		ElectronicProductSpecs electronicspecs = optSpec.get();
		electronicspecs.setSpecName(electronicProductSpecsDto.getSpecName());
		electronicspecs.setSpecValue(electronicProductSpecsDto.getSpecValue());
		productSpecsDao.save(electronicspecs);
		return true;
	}

}
