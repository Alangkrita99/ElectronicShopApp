package com.cg.eshop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.eshop.entity.Basket;

@Repository
public interface IBasketDao extends JpaRepository<Basket, Integer> {
	
	@Query(value = "from Basket b inner join fetch b.customer c where c. customerId =:cid")
	public List<Basket> viewItems(@Param("cid") Integer custId);
	
	@Query(value = "from Basket b inner join fetch b.productDetails p where p.productID =:pid")
	public List<Basket> viewItemsByProduct(@Param("pid") Integer prodId);
}
