package com.betterjavacode.springbatchdemo.dtos;

import com.betterjavacode.springbatchdemo.models.Company;
import com.betterjavacode.springbatchdemo.models.Employee;
import com.betterjavacode.springbatchdemo.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;


import java.io.Serializable;

public class EmployeeDto implements Serializable
{
    private static final long serialVersionUID = 710566148641281929L;

    @Autowired
    public CompanyRepository companyRepository;

    private int employeeId;
    private int companyId;
    private String firstName;
    private String lastName;
    private String email;
    private String jobTitle;

    public EmployeeDto()
    {

    }

    public EmployeeDto(int employeeId, String firstName, String lastName, String email,
                        String jobTitle, int companyId)
    {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.jobTitle = jobTitle;
        this.companyId = companyId;
    }

    public Employee employeeDtoToEmployee()
    {
        Employee employee = new Employee();
        employee.setEmployeeId(this.employeeId);
        employee.setFirstName(this.firstName);
        employee.setLastName(this.lastName);
        employee.setEmail(this.email);
        Company company = companyRepository.findById(this.companyId).get();
        employee.setCompany(company);
        employee.setJobTitle(this.jobTitle);
        return employee;
    }

    public int getEmployeeId ()
    {
        return employeeId;
    }

    public void setEmployeeId (int employeeId)
    {
        this.employeeId = employeeId;
    }

    public int getCompanyId ()
    {
        return companyId;
    }

    public void setCompanyId (int companyId)
    {
        this.companyId = companyId;
    }

    public String getFirstName ()
    {
        return firstName;
    }

    public void setFirstName (String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName ()
    {
        return lastName;
    }

    public void setLastName (String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getJobTitle ()
    {
        return jobTitle;
    }

    public void setJobTitle (String jobTitle)
    {
        this.jobTitle = jobTitle;
    }
}
