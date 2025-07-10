package com.batch.app.record_practice;

public record Rectangle(double length, double width)
{
	
	/*When you need a simple data carrier class

When immutability is desired

When the data won't change after construction

When you want automatic implementations of common methods*/
	
	public Rectangle
	{
		if (length <= 0 || width <= 0)
		{
			throw new ArgentNotPositiveException("Dimensions must be positive");
		}
	}
	
	public double area() 
	{
		return 	length*width;
	}

	public static void main(String[] args)
	{
		Rectangle rectangle =  new Rectangle(0, 0);
		
		System.out.println("Area : "+rectangle.area());
	}
}
