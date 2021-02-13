package com.betterjavacode.springbatchdemo.configurations.processor;


import com.betterjavacode.springbatchdemo.dtos.EmployeeDto;
import com.betterjavacode.springbatchdemo.models.Company;
import com.betterjavacode.springbatchdemo.repositories.CompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;


public class EmployeeItemProcessor implements ItemProcessor<EmployeeDto, EmployeeDto>
{
    private static final Logger logger = LoggerFactory.getLogger(EmployeeItemProcessor.class);

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public EmployeeDto process (EmployeeDto employeeDto) throws Exception
    {
        if(validateCompany(employeeDto))
        {
            return employeeDto;
        }

        logger.debug("Not a valid company, can't save the employee");
        return null;
    }

    private boolean validateCompany (EmployeeDto employeeDto)
    {
        Optional<Company> company = companyRepository.findById(employeeDto.getCompanyId());
        if(company.isPresent())
        {
            return true;
        }
        return false;
    }
}
