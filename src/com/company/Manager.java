package com.company;

import java.util.Date;

public class Manager extends Employee{
    //Fields
    public static final double AWARD = 15;

    //Constructors
    public Manager(int id, String name, Date entranceDate, double hourlyPay) {
        super(id, name, entranceDate, hourlyPay);
        setType(EmployeeType.MANAGER);
    }

    //Getters and Setters
}
