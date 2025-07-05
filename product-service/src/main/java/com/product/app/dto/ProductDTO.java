package com.product.app.dto;

import java.io.Serializable;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

//    @NotBlank(message = "Name cannot be blank")
    private String name;

//    @NotNull(message = "Price cannot be null")
//    @Positive(message = "Price must be greater than zero")
    private Double price;

//    @Min(value = 0, message = "Quantity must be non-negative")
    private Integer quantity;

}
