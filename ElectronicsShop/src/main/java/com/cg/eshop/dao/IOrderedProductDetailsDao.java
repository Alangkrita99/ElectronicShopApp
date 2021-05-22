package com.cg.eshop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.eshop.entity.OrderProducts;
import com.cg.eshop.entity.OrderedProductDetails;

@Repository
public interface IOrderedProductDetailsDao extends JpaRepository<OrderedProductDetails, Integer>{
	@Query("from OrderedProductDetails op inner join fetch op.prodOrders po inner join fetch op.electronicProduct where po.orderId=:orderId")
	public List<OrderedProductDetails> getProductDetailsInOrder(@Param("orderId") Integer orderId);
	
	public List<OrderedProductDetails> findByProdOrders(OrderProducts prodOrders);
	

}
