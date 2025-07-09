package com.batch.app.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.batch.app.model.Employee;

@Configuration
public class EmployeeReader 
{
	@Bean
	public FlatFileItemReader<Employee> reader()
	{
		return new FlatFileItemReaderBuilder<Employee>()
				.name("employeeItemReader")
				.resource(new ClassPathResource("employees.csv"))
				.linesToSkip(1) // ✅ Skip the header
	            .delimited()
	            .names("employee_id", 
	            		"first_name", 
	            		"last_name",
	            		"email",
	            		"phone_number",
//	            		"hire_date", 
	            		"job_id", 
	            		"salary", 
//	            		"commission_pct", 
	            		"manager_id", 
	            		"department_id")
	            .targetType(Employee.class)
	            .build();
		
	}

	
}
