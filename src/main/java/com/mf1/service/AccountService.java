package com.mf1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mf1.entities.Account;
import com.mf1.repository.AccountRepository;

@Service
public class AccountService {
	@Autowired
	AccountRepository accountRepository;
	
	public Account saveAccount(Account account) {
		// Triển khai logic để lưu tài khoản vào cơ sở dữ liệu
		return accountRepository.save(account);
	}
	
	public Account getAccountById(int id) {
		// Triển khai logic để lấy tài khoản từ cơ sở dữ liệu dựa trên id
		return accountRepository.findById(id).orElse(null);
	}
	
	public Account getAccountByUsername(String username) {
		// Triển khai logic để lấy tài khoản từ cơ sở dữ liệu dựa trên tên đăng nhập
		return accountRepository.findByUsername(username);
	}
	
	public Account getAccountByEmail(String email) {
		// Triển khai logic để lấy tài khoản từ cơ sở dữ liệu dựa trên email
		return accountRepository.findByEmail(email);
	}
}
