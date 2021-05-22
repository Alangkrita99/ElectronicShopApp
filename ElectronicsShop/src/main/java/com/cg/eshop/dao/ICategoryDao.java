package com.cg.eshop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.eshop.entity.Category;
import com.cg.eshop.entity.ElectronicProductDetails;

@Repository
public interface ICategoryDao extends JpaRepository<Category, Integer> {
	@Query("from ElectronicProductDetails pd inner join fetch pd.category c where c.categoryName=:categoryName")
	public List<ElectronicProductDetails> getElectronicProductsByCategoryName(
			@Param("categoryName") String categoryName);
}
