package com.cg;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.eshop.dao.IElectronicProductDetailsDao;
import com.cg.eshop.dao.IElectronicProductSpecsDao;
import com.cg.eshop.dto.ElectronicProductSpecsDto;
import com.cg.eshop.entity.ElectronicProductDetails;
import com.cg.eshop.entity.ElectronicProductSpecs;
import com.cg.eshop.exception.ProductNotFoundException;
import com.cg.eshop.service.IProductSpecificationService;
import com.cg.eshop.service.ProductSpecificationServiceImpl;

@SpringBootTest
class TestAddByProductId {
	@Mock
	private IElectronicProductSpecsDao productSpecsDao;
	@Mock
	private IElectronicProductDetailsDao productDetailsDao;
	@InjectMocks
	private IProductSpecificationService productSpecService = new ProductSpecificationServiceImpl();

	@BeforeEach
	public void beforeEach() {
		Optional<ElectronicProductDetails> optdetails1 = Optional.of(new ElectronicProductDetails());
		Optional<ElectronicProductDetails> optdetails2 = Optional.empty();
		when(productDetailsDao.findById(1002)).thenReturn(optdetails1);
		when(productDetailsDao.findById(1957)).thenReturn(optdetails2);
		ElectronicProductSpecs specs = new ElectronicProductSpecs(100, "abc", "xyz");
		when(productSpecsDao.save(any(ElectronicProductSpecs.class))).thenReturn(specs);

	}

	@Test
	@DisplayName(value = "Add specs for 1002")
	void testaddspecs1() throws ProductNotFoundException {
		ElectronicProductSpecsDto electronicProductSpecsDto = new ElectronicProductSpecsDto(100, 1002, "abc", "xyz");
		assertNotNull(productSpecService.addSpecs(electronicProductSpecsDto));
	}

	@Test
	@DisplayName(value = "Add specs for 1957")
	void testaddspecs2() throws ProductNotFoundException {
		ElectronicProductSpecsDto electronicProductSpecsDto = new ElectronicProductSpecsDto(101, 1957, "pqr", "suk");
		assertThrows(ProductNotFoundException.class, () -> productSpecService.addSpecs(electronicProductSpecsDto));
	}
}
