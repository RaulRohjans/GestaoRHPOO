package com.company;

import java.util.Calendar;
import java.util.Date;

public class Manager extends Employee{
    //Fields
    public static final double AWARD = 0.15;

    //Constructors
    public Manager(int id, String name, Date entranceDate, double hourlyPay) {
        super(id, name, entranceDate, hourlyPay);
        setType(EmployeeType.MANAGER);
    }

    public Manager() {
        super();
        setType(EmployeeType.MANAGER);
    }
    
    //Methods
    @Override
    public double calcPaycheck() {
        int days = super.getWorkedDays().get(Calendar.getInstance().get(Calendar.YEAR) + "-" +
                Methods.getFormattedMonth());

        return super.calcPaycheck() + ((days * 8) * super.getHourlyPay()) * (AWARD+1);
    }

    //Getter
    public static double getAWARD() {
        return AWARD;
    }
    
}
