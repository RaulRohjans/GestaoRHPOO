package com.company;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

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

        return super.calcPaycheck() + ((days * 8) * super.getHourlyPay()) * AWARD;
    }

    @Override
    public double calcTrimesterPaycheck(int year) {
        int days = 0;
        double yearTotal = 0;

        if(year < 1){
            return 0;
        }

        /* Fetch total worked days */
        int monthCount = 0;
        for(String key: super.getWorkedDays().keySet()){
            if(Objects.equals(key.split("-")[0], String.valueOf(year))){
                days += super.getWorkedDays().get(key);
                monthCount++;
            }
        }

        yearTotal = ((days * 8) * super.getHourlyPay()) * AWARD;

        //Do an average if worked more than 3 months that year
        if(monthCount >= 3)
            return super.calcTrimesterPaycheck(year) + (yearTotal / monthCount) * 3;
        else
            return super.calcTrimesterPaycheck(year) + yearTotal;
    }

    @Override
    public double calcSemesterPaycheck(int year) {
        int days = 0;
        double yearTotal = 0;

        if(year < 1)
            return 0;

        /* Fetch total worked days */
        int monthCount = 0;
        for(String key: super.getWorkedDays().keySet()){
            if(Objects.equals(key.split("-")[0], String.valueOf(year))){
                days += super.getWorkedDays().get(key);
                monthCount++;
            }
        }

        yearTotal = ((days * 8) * super.getHourlyPay()) * AWARD;

        //Do an average if worked more than 6 months that year
        if(monthCount >= 6)
            return super.calcSemesterPaycheck(year) + (yearTotal / monthCount) * 6;
        else
            return super.calcSemesterPaycheck(year) + yearTotal;
    }

    @Override
    public double calcYearPaycheck(int year) {
        int days = 0;

        if(year < 1)
            return 0;

        /* Fetch total worked days */
        for(String key: super.getWorkedDays().keySet()){
            if(Objects.equals(key.split("-")[0], String.valueOf(year))){
                days += super.getWorkedDays().get(key);
            }
        }

        return super.calcYearPaycheck(year) + ((days * 8) * super.getHourlyPay()) * AWARD;
    }

    //Getter
    public static double getAWARD() {
        return AWARD;
    }
    
}
