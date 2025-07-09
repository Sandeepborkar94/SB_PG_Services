package com.product.app.exception;

public class ResourceNotFoundException extends RuntimeException
{
    public ResourceNotFoundException(String message) 
    {
        super(message);
    }
}