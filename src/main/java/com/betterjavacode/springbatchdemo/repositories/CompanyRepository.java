package com.betterjavacode.springbatchdemo.repositories;

import com.betterjavacode.springbatchdemo.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CompanyRepository extends JpaRepository<Company, Integer>
{
}
