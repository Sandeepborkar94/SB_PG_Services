package com.product.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.app.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> 
{
	
}