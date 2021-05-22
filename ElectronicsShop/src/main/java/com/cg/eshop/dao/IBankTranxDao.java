package com.cg.eshop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.eshop.entity.BankTransaction;

@Repository
public interface IBankTranxDao extends JpaRepository<BankTransaction, Integer> {
	@Query("from BankTransaction bt inner join fetch bt.bankAcc ba where ba.customer.customerId = :custID")
	public List<BankTransaction> viewAllBankTransaction(@Param("custID") Integer cID);

}
