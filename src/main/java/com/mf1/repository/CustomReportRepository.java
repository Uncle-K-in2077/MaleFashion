package com.mf1.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mf1.dto.CustomReport;

public interface CustomReportRepository extends JpaRepository<CustomReport, Serializable> {
	 @Query("SELECT new com.mf1.dto.CustomReport(p.id, p.name, p.image, p.price, SUM(od.quantity), (SUM(od.quantity) * p.price)) "
	            + "FROM com.mf1.entities.Product p "
	            + "JOIN p.orderDetails od "
	            + "JOIN od.order o "
	            + "WHERE o.status = 1 "
	            + "GROUP BY p.id, p.name, p.image, p.price "
	            + "ORDER BY SUM(od.quantity) DESC")
	    List<CustomReport> getCustomReports();
}
