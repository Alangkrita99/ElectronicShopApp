package com.cg;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.eshop.dao.ICategoryDao;
import com.cg.eshop.dao.IElectronicProductDetailsDao;
import com.cg.eshop.entity.ElectronicProductDetails;
import com.cg.eshop.exception.ProductNotFoundException;
import com.cg.eshop.service.CategoryServiceImpl;
import com.cg.eshop.service.ICategoryService;

@SpringBootTest
 class ViewProductsByCategoryNameTest {

	@Mock
	private ICategoryDao category;
	@Mock
	private IElectronicProductDetailsDao dao;
	@InjectMocks
	ICategoryService service = new CategoryServiceImpl();

	@BeforeEach
	 void beforeEach() {

		List<ElectronicProductDetails> opt1 = new ArrayList<>();
		List<ElectronicProductDetails> opt2 = new ArrayList<>();
		opt1.add(new ElectronicProductDetails(1001,"MOB2","MOBILE",900.0,"IMG1",LocalDate.now(),120));
		when(category.getElectronicProductsByCategoryName("abc")).thenReturn(opt1);
		when(category.getElectronicProductsByCategoryName("pqr")).thenReturn(opt2);
	}

	@Test
	@DisplayName(value = "test for categoryname abc")
	 void testviewproductbycategory1() throws ProductNotFoundException {
		assertTrue(service.getProductDetailsByCategoryName("abc").size()>0);
	}

	@Test
	@DisplayName(value = "test for categoryname pqr")
	 void testviewproductbycategory2() throws ProductNotFoundException {
		assertThrows(ProductNotFoundException.class, () -> service.getProductDetailsByCategoryName("pqr"));
	}

}
