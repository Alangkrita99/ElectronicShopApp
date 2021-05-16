package com.cg.eshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.eshop.entity.ElectronicProductSpecs;

@Repository
public interface IProductSpecsDao extends JpaRepository<ElectronicProductSpecs, Integer> {

}
