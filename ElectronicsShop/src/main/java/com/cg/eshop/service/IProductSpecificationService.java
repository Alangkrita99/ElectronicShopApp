package com.cg.eshop.service;

import java.util.List;

import com.cg.eshop.dto.ElectronicProductSpecsDto;
import com.cg.eshop.entity.ElectronicProductSpecs;
import com.cg.eshop.exception.NoSpecsException;
import com.cg.eshop.exception.ProductNotFoundException;

public interface IProductSpecificationService {
public ElectronicProductSpecs addSpecs(ElectronicProductSpecsDto electronicProductSpecsDto) throws ProductNotFoundException;
public List<ElectronicProductSpecs>getProductSpecsById(Integer productId)throws ProductNotFoundException,NoSpecsException;
}
