package com.product.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.product.app.dto.ProductDTO;
import com.product.app.entity.Product;
import com.product.app.exception.ResourceNotFoundException;
import com.product.app.repository.ProductRepository;
import com.product.app.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService
{
	private final ProductRepository productRepo;
	
	@Override
	public ProductDTO createProduct(ProductDTO dto)
	{
		Product product = mapToEntity(dto);
		
		return mapToDto(productRepo.save(product));
	}

	@Override
//	 GET product by ID - Cacheable
	@Cacheable(value = "products", key = "#id")
	public ProductDTO getProductById(Long id) 
	{
		
		Product product = productRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product Not found with id "+id));
		
		return mapToDto(product);
	}

	@Override
	public List<ProductDTO> getAllProducts() 
	{
		
		return productRepo.findAll()
				.stream()
				.map(this::mapToDto)
				.collect(Collectors.toList());
	}

	@Override
	// UPDATE product - Cache update
	@CachePut(value = "products", key = "#id")
	public ProductDTO updateProduct(Long id, ProductDTO dto)
	{
		Product product = productRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product Not found with id "+id));
		
		product.setName(dto.getName());
		product.setPrice(dto.getPrice());
		product.setQuantity(dto.getQuantity());;
		
		return mapToDto(productRepo.save(product));
	}

	@Override
	// DELETE product - Evict from cache
	@CacheEvict(value = "products", key = "#id")
	public void deleteProduct(Long id)
	{
		 Product product = productRepo.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
	        productRepo.delete(product);
	}
	
	private ProductDTO mapToDto(Product product)
	{
		return ProductDTO.builder()
				.id(product.getId())
				.name(product.getName())
				.price(product.getPrice())
				.quantity(product.getQuantity())
				.build();	
	}
	
	private Product mapToEntity(ProductDTO productDTO) {
		return Product.builder()
				.name(productDTO.getName())
				.price(productDTO.getPrice())
				.quantity(productDTO.getQuantity())
				.build();
	}

}
