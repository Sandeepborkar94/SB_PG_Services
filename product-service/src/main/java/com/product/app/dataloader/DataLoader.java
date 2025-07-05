package com.product.app.dataloader;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.product.app.entity.Product;
import com.product.app.repository.ProductRepository;

@Component
public class DataLoader implements CommandLineRunner
{

	private final ProductRepository productRepo;

	public DataLoader(ProductRepository productRepo) 
	{
		this.productRepo = productRepo;
	}

	@Override
	public void run(String... args)
	{
		// Check if already populated
		if (productRepo.count() == 0)
		{
			productRepo.save(Product.builder().name("Laptop").price(75000.0).quantity(10).build());
			productRepo.save(Product.builder().name("Phone").price(25000.0).quantity(25).build());
			productRepo.save(Product.builder().name("Tablet").price(15000.0).quantity(15).build());
			productRepo.save(Product.builder().name("Headphones").price(3000.0).quantity(50).build());
			productRepo.save(Product.builder().name("Smartwatch").price(5000.0).quantity(30).build());

			System.out.println("Dummy product data inserted ✅");
		} else {
			System.out.println("Product data already exists, skipping insert ⏭️");
		}
	}
}
