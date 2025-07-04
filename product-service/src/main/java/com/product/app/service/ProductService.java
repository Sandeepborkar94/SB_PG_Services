package com.product.app.service;

import java.util.List;

import com.product.app.dto.ProductDTO;

public interface ProductService
{
	ProductDTO createProduct(ProductDTO dto);

	ProductDTO getProductById(Long id);

	List<ProductDTO> getAllProducts();

	ProductDTO updateProduct(Long id, ProductDTO dto);

	void deleteProduct(Long id);

}
