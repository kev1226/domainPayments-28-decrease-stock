package com.tecnoshop.stock.repository;

import com.tecnoshop.stock.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
