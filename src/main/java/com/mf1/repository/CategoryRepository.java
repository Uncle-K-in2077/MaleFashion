package com.mf1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mf1.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // Các phương thức CRUD sẽ được tự động triển khai bởi Spring Data JPA
}
