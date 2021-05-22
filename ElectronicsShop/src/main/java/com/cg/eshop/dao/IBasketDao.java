package com.cg.eshop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.eshop.entity.Basket;

@Repository
public interface IBasketDao extends JpaRepository<Basket, Integer>{
	@Query("from Basket b inner join b.customer c inner join b.productDetails pd where c.customerId = :custId")
	public List<Basket> getItemsFromBasket(@Param("custId") Integer custId);


}
