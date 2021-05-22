package com.cg;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.eshop.dao.IElectronicProductSpecsDao;
import com.cg.eshop.dto.ElectronicProductSpecsDto;
import com.cg.eshop.entity.ElectronicProductSpecs;
import com.cg.eshop.exception.NoSpecsException;
import com.cg.eshop.exception.ProductNotFoundException;
import com.cg.eshop.service.IProductSpecificationService;
import com.cg.eshop.service.ProductSpecificationServiceImpl;

@SpringBootTest
 class TestEditBySpecId {
	@Mock
	private IElectronicProductSpecsDao productSpecsDao;
	@InjectMocks
	private IProductSpecificationService productSpecService = new ProductSpecificationServiceImpl();

	@BeforeEach
	public void beforeEach() {
		Optional<ElectronicProductSpecs> optdetails1 = Optional.of(new ElectronicProductSpecs());
		Optional<ElectronicProductSpecs> optdetails2 = Optional.empty();
		when(productSpecsDao.findById(100)).thenReturn(optdetails1);
		when(productSpecsDao.findById(101)).thenReturn(optdetails2);
		ElectronicProductSpecs specs = new ElectronicProductSpecs(100, "abc", "xyz");
		when(productSpecsDao.save(any(ElectronicProductSpecs.class))).thenReturn(specs);

	}

	@Test
	@DisplayName(value = "Edit specs for 1002")
	 void testeditspecs1() throws ProductNotFoundException, NoSpecsException {
		ElectronicProductSpecsDto electronicProductSpecsDto = new ElectronicProductSpecsDto(100, "abc", "xyz");
		assertNotNull(productSpecService.editProductSpecsBySpecId(electronicProductSpecsDto));
	}

	@Test
	@DisplayName(value = "Edit specs for 1957")
	 void testeditspecs2() throws NoSpecsException {
		ElectronicProductSpecsDto electronicProductSpecsDto = new ElectronicProductSpecsDto(101, "pqr", "suk");
		assertThrows(NoSpecsException.class,
				() -> productSpecService.editProductSpecsBySpecId(electronicProductSpecsDto));
	}
}
