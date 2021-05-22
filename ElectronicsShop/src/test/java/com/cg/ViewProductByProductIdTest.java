package com.cg;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.eshop.dao.IElectronicProductDetailsDao;
import com.cg.eshop.entity.ElectronicProductDetails;
import com.cg.eshop.exception.ProductNotFoundException;
import com.cg.eshop.service.ElectronicProductDetailsServiceImpl;
import com.cg.eshop.service.IElectronicProductDetailService;

@SpringBootTest
 class ViewProductByProductIdTest {
	@Mock
	private IElectronicProductDetailsDao dao;
	@InjectMocks
	IElectronicProductDetailService service = new ElectronicProductDetailsServiceImpl();

	@BeforeEach
	public void beforeEach() {
		Optional<ElectronicProductDetails> optdet1 = Optional.of(new ElectronicProductDetails());
		Optional<ElectronicProductDetails> optdet2 = Optional.empty();
		when(dao.findById(1003)).thenReturn(optdet1);
		when(dao.findById(203)).thenReturn(optdet2);
		when(dao.findById(229)).thenReturn(optdet2);
	}

	@Test
	@DisplayName(value = "testviewproductbyproductid for 1003")
	void testviewbyprodid1() throws ProductNotFoundException {
		assertNotNull(service.getProductDetailsByProductId(1003));
	}

	@Test
	@DisplayName(value = "testviewproductbyproductid for 203")
	void testviewbyprodid2() throws ProductNotFoundException {
		assertThrows(ProductNotFoundException.class, () -> service.getProductDetailsByProductId(203));
	}

	@Test
	@DisplayName(value = "testviewproductbyproductid for 229")
	void testviewbyprodid3() throws ProductNotFoundException {
		assertThrows(ProductNotFoundException.class, () -> service.getProductDetailsByProductId(229));
	}
}

