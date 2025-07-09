package com.batch.app.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.batch.app.entity.EmployeeEntity;
import com.batch.app.model.Employee;

@Component
public class EmployeeProcessor implements ItemProcessor<Employee, EmployeeEntity>
{

	@Override
	public EmployeeEntity process(Employee emp) throws Exception
	{
		var entity = new EmployeeEntity();
		
		entity.setEmployee_id(emp.employee_id());
		entity.setFirst_name(emp.first_name().toUpperCase());
		entity.setLast_name(emp.last_name().toUpperCase());
		entity.setEmail(emp.email());
		entity.setPhone_number(emp.phone_number());
//		entity.setHire_date(emp.hire_date());
		entity.setJob_id(emp.job_id());
		entity.setSalary(emp.salary());
//		entity.setCommission_pct(emp.commission_pct());
		entity.setManager_id(emp.manager_id());
		entity.setDepartment_id(emp.department_id());
		
		return entity;
	}
	
	
	

}
