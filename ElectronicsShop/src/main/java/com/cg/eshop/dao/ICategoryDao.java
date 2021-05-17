package com.cg.eshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.eshop.entity.Category;

@Repository
public interface ICategoryDao extends JpaRepository<Category, Integer>{
	
	

}
