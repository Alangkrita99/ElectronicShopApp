package com.cg.eshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.eshop.entity.ElectronicProductDetails;

@Repository
public interface IProductDetails extends JpaRepository<ElectronicProductDetails, Integer> {

}
