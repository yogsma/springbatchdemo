package com.betterjavacode.springbatchdemo.repositories;

import com.betterjavacode.springbatchdemo.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>
{
}
