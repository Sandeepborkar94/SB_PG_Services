package com.batch.app.record_practice;

public record Person(int id, String name, int age) 
{
	/*Records are a special kind of class in Java (introduced in Java 14 as a preview feature and finalized in Java 16) that provide a concise way to declare classes that are transparent holders for shallowly immutable data.
	 * 
	 * Key Characteristics of Records
Concise syntax: Automatically generates constructor, accessors, equals(), hashCode(), and toString()

Immutable: All fields are final

Transparent: The state description declares the components

Not extensible: Records are implicitly final
	 * 
	 * 
	 * 
	 * 
	 * */
	
	
	public static void main(String[] args)
	{
		Person person = new Person(1, "Sandeep Borkar", 30);
		
		System.out.println("ID of the person: "+person.id);
		System.out.println("Name of the person: "+person.name);
		System.out.println("Age of the person : "+person.age);
		
		System.out.println(person);
	}
}
