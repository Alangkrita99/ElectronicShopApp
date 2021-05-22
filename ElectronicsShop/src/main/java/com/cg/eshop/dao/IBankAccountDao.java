
package com.cg.eshop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.eshop.entity.BankAccount;
import com.cg.eshop.entity.Customer;

@Repository
public interface IBankAccountDao extends JpaRepository<BankAccount, Integer>{
	//@Query("from cg_bank_account")
	//public List<BankAccount> viewAllBankAccount();
	
	public BankAccount findByCardNumber(Integer cardNumber);
	
	@Query("from BankAccount ba inner join fetch ba.customer c where c.customerId=:custId")
	public BankAccount findByCustomer(@Param("custId") Integer custId);
	
//	public BankAccount findByCustomer(Customer customer);
}
