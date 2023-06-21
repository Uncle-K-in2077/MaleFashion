package com.mf1.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mf1.entities.Account;
import com.mf1.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
	Page<Order> findByIdLike(int id, Pageable pageable);

	List<Order> findByAccount(Account account, Sort sort);
}
