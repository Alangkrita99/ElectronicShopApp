package com.cg.eshop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.eshop.entity.ElectronicProductSpecs;


@Repository
public interface IElectronicProductSpecsDao extends JpaRepository<ElectronicProductSpecs, Integer> {
	@Query("from ElectronicProductSpecs specs inner join fetch specs.elecProdDetails product where product.productID=:prod_id")
	public List<ElectronicProductSpecs> getSpecificationsByProductId(@Param("prod_id") Integer productId);
	
	/*@Query("from ElectronicProductSpecs specs where specs.specID=:spec_id")
	public ElectronicProductSpecs getSpecificationsBySpecId(@Param("spec_id") Integer specId);*/

}
