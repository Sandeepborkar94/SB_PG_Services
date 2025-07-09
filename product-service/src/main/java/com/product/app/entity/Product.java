package com.product.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product implements AutoCloseable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Name is mandatory")
	private String name;
	
	@NotNull(message = "Price is required")
	@Positive(message = "Price must be positive")
	private Double price;
	
	@Min(value = 0, message = "Quantity must be 0 or more ")
	private Integer quantity;

	@Override
	public void close() throws Exception
	{
			// Implement any resource cleanup logic if necessary
		// For example, if this entity holds any resources that need to be released
		// In this case, we don't have any resources to clean up, so this can be empty	
		
		// This method is here to demonstrate the AutoCloseable interface
		
		
		
		
	}
	
	
	

}
