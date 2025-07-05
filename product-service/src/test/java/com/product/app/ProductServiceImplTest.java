package com.product.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;

import com.product.app.dto.ProductDTO;
import com.product.app.entity.Product;
import com.product.app.exception.ResourceNotFoundException;
import com.product.app.repository.ProductRepository;
import com.product.app.service.impl.ProductServiceImpl;

@SpringBootTest
@EnableCaching
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepo;

    @InjectMocks
    private ProductServiceImpl productService;

    @Spy
    private CacheManager cacheManager = new ConcurrentMapCacheManager("products");

    private Product product;
    private ProductDTO dto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        product = Product.builder().id(1L).name("Test").price(100.0).quantity(5).build();
        dto = ProductDTO.builder().id(1L).name("Test").price(100.0).quantity(5).build();
    }

    @Test
    void testGetProductById_CacheWorks() {
        // 1st call: mock repository
        when(productRepo.findById(1L)).thenReturn(Optional.of(product));

        // First call: should hit DB
        ProductDTO result1 = productService.getProductById(1L);
        assertEquals("Test", result1.getName());
        verify(productRepo, times(1)).findById(1L); // called once

        // Second call: should use cache (no repo call)
        ProductDTO result2 = productService.getProductById(1L);
        assertEquals("Test", result2.getName());
        verify(productRepo, times(1)).findById(1L); // still only once

        // Confirm that result came from cache
        assertSame(result1, result2);
    }

    @Test
    void testUpdateProduct_ShouldUpdateAndCachePut() {
        when(productRepo.findById(1L)).thenReturn(Optional.of(product));
        when(productRepo.save(any(Product.class))).thenReturn(product);

        ProductDTO updated = ProductDTO.builder().id(1L).name("Updated").price(200.0).quantity(10).build();
        ProductDTO result = productService.updateProduct(1L, updated);

        assertEquals("Updated", result.getName());
        verify(productRepo, times(1)).save(any(Product.class));

        // Now, call getProductById again — should come from cache
        ProductDTO fromCache = productService.getProductById(1L);
        assertEquals("Updated", fromCache.getName());
    }

    @Test
    void testDeleteProduct_ShouldEvictCache() {
        when(productRepo.findById(1L)).thenReturn(Optional.of(product));

        // Put in cache first
        productService.getProductById(1L);

        // Delete
        productService.deleteProduct(1L);
        verify(productRepo, times(1)).delete(product);

        // Call get again — should call DB because cache was evicted
        when(productRepo.findById(1L)).thenReturn(Optional.of(product)); // re-mock for next call
        productService.getProductById(1L);
        verify(productRepo, times(2)).findById(1L); // 1st + after eviction
    }

    @Test
    void testGetProductById_NotFound_ShouldThrowException() {
        when(productRepo.findById(2L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productService.getProductById(2L));
    }
}
