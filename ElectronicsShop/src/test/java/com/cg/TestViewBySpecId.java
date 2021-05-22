package com.cg;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.eshop.dao.IElectronicProductSpecsDao;
import com.cg.eshop.entity.ElectronicProductSpecs;
import com.cg.eshop.exception.NoSpecsException;
import com.cg.eshop.exception.ProductNotFoundException;
import com.cg.eshop.service.IProductSpecificationService;
import com.cg.eshop.service.ProductSpecificationServiceImpl;

@SpringBootTest
class TestViewBySpecId {
	@Mock
	private IElectronicProductSpecsDao productSpecsDao;
	@InjectMocks
	private IProductSpecificationService productSpecService = new ProductSpecificationServiceImpl();

	@BeforeEach
	public void beforeEach() {
		Optional<ElectronicProductSpecs> optspecs1 = Optional.of(new ElectronicProductSpecs());
		Optional<ElectronicProductSpecs> optspecs2 = Optional.empty();
		when(productSpecsDao.findById(21)).thenReturn(optspecs1);
		when(productSpecsDao.findById(203)).thenReturn(optspecs2);
		when(productSpecsDao.findById(22)).thenReturn(optspecs2);
		when(productSpecsDao.findById(229)).thenReturn(optspecs1);
	}

	@Test
	@DisplayName(value = "testviewproductspecbyspecid for 21")
	void testviewbyspecid1() throws ProductNotFoundException, NoSpecsException {
		assertNotNull(productSpecService.getProductSpecsBySpecId(21));
	}

	@Test
	@DisplayName(value = "testviewproductspecbyspecid for 203")
	void testviewbyspecid2() throws NoSpecsException {
		assertThrows(NoSpecsException.class, () -> productSpecService.getProductSpecsBySpecId(203));
	}

	@Test
	@DisplayName(value = "testviewproductspecbyspecid for 22")
	void testviewbyspecid3() throws NoSpecsException {
		assertThrows(NoSpecsException.class, () -> productSpecService.getProductSpecsBySpecId(203));
	}

	@Test
	@DisplayName(value = "testviewproductspecbyspecid for 229")
	void testviewbyspecid4() throws ProductNotFoundException, NoSpecsException {
		assertNotNull(productSpecService.getProductSpecsBySpecId(229));

	}
}
