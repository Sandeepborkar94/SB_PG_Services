package com.batch.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "employees")
public class EmployeeEntity
{
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String employee_id;
	
	private String first_name;
	private String last_name;
	private String email;
	private String phone_number;
//	private Date hire_date;
	private String job_id;
	private String salary;
//	private String commission_pct;
	private String manager_id;
	private String department_id;
}
