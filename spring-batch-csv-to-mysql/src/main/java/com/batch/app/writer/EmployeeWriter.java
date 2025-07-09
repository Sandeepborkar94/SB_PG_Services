package com.batch.app.writer;

import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.batch.app.entity.EmployeeEntity;
import com.batch.app.repository.EmployeeRepository;

@Component
public class EmployeeWriter 
{
	@Bean
	public RepositoryItemWriter<EmployeeEntity> writer(EmployeeRepository repository)
	{
		var writer = new RepositoryItemWriter<EmployeeEntity>();
		
		writer.setRepository(repository);
		writer.setMethodName("save");
		
		return writer;
		 
	}

}
