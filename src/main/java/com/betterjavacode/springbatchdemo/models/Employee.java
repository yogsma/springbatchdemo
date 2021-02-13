package com.betterjavacode.springbatchdemo.models;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "employee")
@Entity(name = "Employeee")
public class Employee implements Serializable
{

    private static final long serialVersionUID = -4083557549573717009L;

    public Employee ()
    {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "employeeId", nullable = false)
    private int employeeId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companyid", nullable = false)
    private Company company;

    @Column(name = "firstname", length = 255)
    private String firstName;

    @Column(name = "lastname", length = 255)
    private String lastName;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "jobtitle", length = 100)
    private String jobTitle;

    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
    }

    public int getEmployeeId ()
    {
        return employeeId;
    }

    public void setEmployeeId (int employeeId)
    {
        this.employeeId = employeeId;
    }

    public Company getCompany ()
    {
        return company;
    }

    public void setCompany (Company company)
    {
        this.company = company;
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

    @Override
    public String toString ()
    {
        return "Employee{}";
    }


}
