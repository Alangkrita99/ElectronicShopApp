package com.cg.eshop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.eshop.entity.OrderProducts;

@Repository
public interface IOrderProductsDao extends JpaRepository<OrderProducts, Integer>{
	// custom query
	@Query("from OrderProducts o inner join fetch o.customer c where c.customerId=:custid ")
	public List<OrderProducts> viewOrderByUserId(@Param("custid") Integer custId);
}
