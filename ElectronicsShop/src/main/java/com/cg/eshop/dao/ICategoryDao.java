package com.cg.eshop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.eshop.entity.Category;

@Repository
public interface ICategoryDao extends JpaRepository<Category, Integer>{
	@Query("from category")
	public List<Category> viewAllCategory();
	

}
