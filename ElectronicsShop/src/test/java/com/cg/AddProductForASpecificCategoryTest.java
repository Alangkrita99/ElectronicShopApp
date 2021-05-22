package com.cg;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.eshop.dao.ICategoryDao;
import com.cg.eshop.dao.IElectronicProductDetailsDao;
import com.cg.eshop.dto.AddProductDto;
import com.cg.eshop.entity.Category;
import com.cg.eshop.entity.ElectronicProductDetails;
import com.cg.eshop.exception.CategoryNotFoundException;
import com.cg.eshop.service.ElectronicProductDetailsServiceImpl;
import com.cg.eshop.service.IElectronicProductDetailService;

@SpringBootTest


class AddProductForASpecificCategoryTest {
	
	
	@Mock
	private ICategoryDao categoryDao;
	@Mock
	private IElectronicProductDetailsDao prodDao;
	
	@InjectMocks
	private IElectronicProductDetailService service = new ElectronicProductDetailsServiceImpl();
	
	@BeforeEach
	 void beforeEach() {
		Optional<Category> opt1=Optional.of(new Category());
		Optional<Category> opt2=Optional.empty();
		when(categoryDao.findById(102)).thenReturn(opt1);
		when(categoryDao.findById(108)).thenReturn(opt2);
		ElectronicProductDetails prod=new ElectronicProductDetails(1001,"MOB2","MOBILE",900.0,"IMG1",LocalDate.now(),120);
		when(prodDao.save(any(ElectronicProductDetails.class))).thenReturn(prod);
		
	}
	@Test
	@DisplayName(value="test add product for 102")
	 void testadd1() throws CategoryNotFoundException {
		assertNotNull(service.addEletronicProduct(new AddProductDto("MOB2","MOBILE",900.0,"IMG1",120,102)));
	}
	@Test
	@DisplayName(value="test add product for 108")
	 void testadd2() throws CategoryNotFoundException {
		assertThrows(CategoryNotFoundException.class,()->service.addEletronicProduct(new AddProductDto("MOB3","MOBILE",800.0,"IMG3",140,108)));
	}

}
