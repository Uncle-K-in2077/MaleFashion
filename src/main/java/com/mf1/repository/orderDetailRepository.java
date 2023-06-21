package com.mf1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mf1.entities.OrderDetail;

@Repository
public interface orderDetailRepository extends JpaRepository<OrderDetail, Integer>{

}
