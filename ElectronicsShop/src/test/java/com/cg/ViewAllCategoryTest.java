package com.cg;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.eshop.dao.ICategoryDao;
import com.cg.eshop.entity.Category;
import com.cg.eshop.exception.CategoryNotFoundException;
import com.cg.eshop.service.CategoryServiceImpl;
import com.cg.eshop.service.ICategoryService;

@SpringBootTest
 class ViewAllCategoryTest {

	@Mock
	private ICategoryDao catdao;

	@InjectMocks
	ICategoryService catservice = new CategoryServiceImpl();

	@BeforeEach
	 void beforeEach() {
		List<Category> lst = new ArrayList<>();
		lst.add(new Category());
		lst.add(new Category());
		when(catdao.findAll()).thenReturn(lst);
	}
	
	@Test
	@DisplayName(value = "test for view all category")
	 void test1() throws CategoryNotFoundException {
		assertTrue(catservice.getAllCategory().size()>0);
	}
}

