package com.cg.eshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.eshop.entity.OrderedProductDetails;

@Repository
public interface IOrderedProductDetails extends JpaRepository<OrderedProductDetails, Integer>{

}
