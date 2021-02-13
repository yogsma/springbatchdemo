package com.betterjavacode.springbatchdemo.configurations.processor;

import com.betterjavacode.springbatchdemo.dtos.EmployeeDto;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

@Component
public class EmployeeDtoFieldSetMapper implements FieldSetMapper<EmployeeDto>
{

    @Override
    public EmployeeDto mapFieldSet (FieldSet fieldSet) throws BindException
    {
        int employeeId = fieldSet.readInt("employeeId");
        String firstName = fieldSet.readRawString("firstName");
        String lastName = fieldSet.readRawString("lastName");
        String jobTitle = fieldSet.readRawString("jobTitle");
        String email = fieldSet.readRawString("email");
        int companyId = fieldSet.readInt("companyId");

        return new EmployeeDto(employeeId, firstName, lastName, jobTitle, email, companyId);
    }
}
