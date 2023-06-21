package com.mf1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mf1.dto.CartElement;

@Service
public interface CartService {
	void add(int id);

	void remove(int id);

	void update(int id, int quantity);

	void clear();

	int getCount();

	double getAmount();

	List<CartElement> getList();
}
