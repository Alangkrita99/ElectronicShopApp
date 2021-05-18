package com.cg.eshop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.eshop.entity.BankAccount;

@Repository
public interface IBankAccountDao extends JpaRepository<BankAccount, Integer>{
	//@Query("from cg_bank_account")
	//public List<BankAccount> viewAllBankAccount();
	
	public BankAccount findByCardNumber(Integer cardNumber);
}
