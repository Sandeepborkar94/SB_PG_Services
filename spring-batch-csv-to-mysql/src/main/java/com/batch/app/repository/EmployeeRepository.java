package com.batch.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.batch.app.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> 
{

}
