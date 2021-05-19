package com.cg.eshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.eshop.entity.ElectronicProductDetails;

@Repository
public interface IElectronicProductDetailsDao extends JpaRepository<ElectronicProductDetails, Integer>{
//	@Query("from ElectronicProductDetails productDetails inner join fetch productDetails.category cat where cat.categoryName=:cname")
//	public ElectronicProductDetails getProductDetailsByCategoryName(@Param("cat_name") String categoryName);

}
