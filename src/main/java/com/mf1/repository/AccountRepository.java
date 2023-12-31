package com.mf1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mf1.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{
	Account findByUsername(String username);
	
	Account findByEmail(String email);
	
	List<Account> findByUsernameContainingOrEmailContaining(String usernameKeyword, String emailKeyword);

}
