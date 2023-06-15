package com.mf1.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mf1.entities.Category;
import com.mf1.entities.Product;
import com.mf1.entities.Report;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	Page<Product> findAllByNameLike(String name, Pageable pageable);

	Page<Product> findByPriceBetween(double min, double max, Pageable pageable);

	@Query("SELECT o FROM Product o WHERE o.name LIKE ?1")
	Page<Product> findByKeywords(String keywords, Pageable pageable);

	// Report
	@Query("SELECT new Report(o.category, sum(o.price), count(o)) " + " FROM Product o " + " GROUP BY o.category"
			+ " ORDER BY sum(o.price) DESC")
	List<Report> getInventoryByCategory();

	Page<Product> findByNameContainingAndStatusAndPriceBetween(String name, int status, double minPrice,
			double maxPrice, Pageable pageable);

	// Có thể hoàn toàn sử dụng một hàm dưới này để tìm cả hai trường hợp là có hoặc
	// k có tham số Category, chỉ cần phía controller truyền vào category.orElse(null) là được
	Page<Product> findByCategoryAndNameContainingAndStatusAndPriceBetween(Category category, String name, int status,
			double minPrice, double maxPrice, Pageable pageable);

}
