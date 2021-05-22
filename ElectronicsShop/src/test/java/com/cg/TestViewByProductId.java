package com.cg;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.eshop.dao.IElectronicProductDetailsDao;
import com.cg.eshop.dao.IElectronicProductSpecsDao;
import com.cg.eshop.entity.ElectronicProductDetails;
import com.cg.eshop.entity.ElectronicProductSpecs;
import com.cg.eshop.exception.NoSpecsException;
import com.cg.eshop.exception.ProductNotFoundException;
import com.cg.eshop.service.IProductSpecificationService;
import com.cg.eshop.service.ProductSpecificationServiceImpl;

@SpringBootTest
class TestViewByProductId {
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
		when(productDetailsDao.findById(1001)).thenReturn(optdetails2);
		when(productDetailsDao.findById(1999)).thenReturn(optdetails1);
		when(productDetailsDao.findById(1957)).thenReturn(optdetails1);

		List<ElectronicProductSpecs> lst = new ArrayList<>();
		lst.add(new ElectronicProductSpecs(100, "xyz", "abc"));
		lst.add(new ElectronicProductSpecs(200, "dpq", "asmsj"));
		List<ElectronicProductSpecs> lst2 = new ArrayList<>();
		when(productSpecsDao.getSpecificationsByProductId(1002)).thenReturn(lst);
		when(productSpecsDao.getSpecificationsByProductId(1001)).thenReturn(lst2);
		when(productSpecsDao.getSpecificationsByProductId(1999)).thenReturn(lst);
		when(productSpecsDao.getSpecificationsByProductId(1957)).thenReturn(lst2);
	}

	@Test
	@DisplayName(value = "testviewproductspecbyproductid for 1002")
	void testviewbyproductid1() throws ProductNotFoundException, NoSpecsException {
		assertTrue(productSpecService.getProductSpecsById(1002).size() > 0);
	}

	@Test
	@DisplayName(value = "testviewproductspecbyproductid for 1001")
	void testviewbyproductid2() throws ProductNotFoundException, NoSpecsException {
		assertThrows(ProductNotFoundException.class, () -> productSpecService.getProductSpecsById(1001));
	}

	@Test
	@DisplayName(value = "testviewproductspecbyproductid for 1999")
	void testviewbyproductid3() throws ProductNotFoundException, NoSpecsException {
		assertTrue(productSpecService.getProductSpecsById(1999).size() > 0);
	}

	@Test
	@DisplayName(value = "testviewproductspecbyproductid for 1957")
	void testviewbyproductid4() throws ProductNotFoundException, NoSpecsException {
		assertThrows(NoSpecsException.class, () -> productSpecService.getProductSpecsById(1957));
	}
}
