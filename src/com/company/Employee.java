package com.company;

import java.util.ArrayList;
import java.util.Date;

public class Employee {
    //Fields
    private int id;
    private String name;
    private Date entranceDate;
    private ArrayList<Integer> workedDays;
    private double hourlyPay;
    public enum EmployeeType {NORMAL, MANAGER, DRIVER, SALESMAN};
    private EmployeeType type;

    private final static double DAILY_FOOD_SUBSIDY = 4.79;

    //Constructor
    public Employee(int id, String name, Date entranceDate, double hourlyPay) {
        this.id = id;
        this.name = name;
        this.entranceDate = entranceDate;
        this.workedDays = new ArrayList<Integer>();
        this.type = EmployeeType.NORMAL;
        if(hourlyPay <= 0)
            throw new IllegalArgumentException("Invalid value for hourlyPay.");
        this.hourlyPay = hourlyPay;
    }

    //Methods
    /*public double getBasePay() {
        return workedDays * (hourlyPay * 8);
    }*/

    /*public double getExtraPay() {
        return workedDays * DAILY_FOOD_SUBSIDY;
    }*/

    /*public double getFullPay() {
        return getBasePay() + getExtraPay();
    }*/

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getEntranceDate() {
        return entranceDate;
    }

    public void setEntranceDate(Date entranceDate) {
        this.entranceDate = entranceDate;
    }

    public ArrayList<Integer> getWorkedDays() {
        return workedDays;
    }

    public void setWorkedDays(ArrayList<Integer> workedDays) {
        this.workedDays = workedDays;
    }

    public EmployeeType getType() {
        return type;
    }

    public void setType(EmployeeType type) {
        this.type = type;
    }

    public double getHourlyPay() {
        return hourlyPay;
    }

    public void setHourlyPay(double hourlyPay) {
        this.hourlyPay = hourlyPay;
    }
}
