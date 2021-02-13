package com.betterjavacode.springbatchdemo.models;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "company")
@Entity(name = "Company")
public class Company implements Serializable
{


    private static final long serialVersionUID = 4785016814014017939L;

    public Company ()
    {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = true)
    private String type;

    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }
}
