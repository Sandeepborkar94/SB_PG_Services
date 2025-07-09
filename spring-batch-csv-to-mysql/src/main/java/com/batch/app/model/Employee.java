package com.batch.app.model;

public record Employee(
	    String employee_id,
	    String first_name,
	    String last_name,
	    String email,
	    String phone_number,
//	    Date hire_date,
	    String job_id,
	    String salary,
//	    String commission_pct,
	    String manager_id,
	    String department_id
	)
{
	    // You can add additional methods if needed
	    // For example, a method to get full name:
//	    public String getFullName() {
//	        return FIRST_NAME + " " + LAST_NAME;
//	    }
	}