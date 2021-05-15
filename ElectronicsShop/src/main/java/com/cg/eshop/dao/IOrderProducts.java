package com.cg.eshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.eshop.entity.OrderProducts;

@Repository
public interface IOrderProducts extends JpaRepository<OrderProducts, Integer>{

}
