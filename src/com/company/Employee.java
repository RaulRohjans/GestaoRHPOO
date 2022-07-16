package com.company;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Employee {
    //Fields
    private int id;
    private String name;
    private Date entranceDate;
    private HashMap<String, Integer> workedDays;
    private double hourlyPay;

    public enum EmployeeType {NORMAL, MANAGER, DRIVER, SALESMAN}

    ;
    private EmployeeType type;

    private final static double DAILY_FOOD_SUBSIDY = 4.79;

    //Constructor
    public Employee(int id, String name, Date entranceDate, double hourlyPay) {
        this.id = id;
        this.name = name;
        this.entranceDate = entranceDate;
        this.workedDays = new HashMap<String, Integer>();
        this.type = EmployeeType.NORMAL;
        if (hourlyPay <= 0)
            throw new IllegalArgumentException("Invalid value for hourlyPay.");
        this.hourlyPay = hourlyPay;
    }

    public Employee() {
        this.id = -1;
        this.name = "";
        this.entranceDate = null;
        this.workedDays = new HashMap<String, Integer>();
        this.type = EmployeeType.NORMAL;
        this.hourlyPay = 0;
    }

    //Methods
    public void addWorkedDays(String d, int days) {
        workedDays.put(d, days);
    }



    public void addWorkedDays(int days) {
        Calendar calendar = Calendar.getInstance();
        workedDays.put(String.valueOf(calendar.get(Calendar.YEAR) + "-" + String.valueOf(calendar.get(Calendar.MONTH)) + 1), days);
    }


    /*public double getBasePay() {
        return workedDays * (hourlyPay * 8);
    }

    public double getExtraPay() {
        return workedDays * DAILY_FOOD_SUBSIDY;
    }

    public double getFullPay() {
        return getBasePay() + getExtraPay();
    }*/
    public double calcPaycheck() {
        double total = 0;

        if (workedDays.size() < 1)
            return 0;

        int days = workedDays.get(Calendar.getInstance().get(Calendar.YEAR) + "-" +
                Methods.getFormattedMonth());

        return ((days * 8) * hourlyPay) + (days * DAILY_FOOD_SUBSIDY);
    }

    public double calcTrimesterPaycheck(int year) {
        double yearTotal = 0;
        int days = 0;

        if (workedDays.size() < 1 || year < 1)
            return 0;

        /* Fetch total worked days */
        int monthCount = 0;
        for (String key : workedDays.keySet()) {
            if (Objects.equals(key.split("-")[0], String.valueOf(year))) {
                days += workedDays.get(key);
                monthCount++;
            }
        }

        yearTotal = ((days * 8) * hourlyPay) + (days * DAILY_FOOD_SUBSIDY);

        //Do an average if worked more than 3 months that year
        if (monthCount >= 3)
            return (yearTotal / monthCount) * 3;
        else
            return yearTotal;
    }

    public double calcSemesterPaycheck(int year) {
        double yearTotal = 0;
        int days = 0;

        if (workedDays.size() < 1 || year < 1)
            return 0;

        /* Fetch total worked days */
        int monthCount = 0;
        for (String key : workedDays.keySet()) {
            if (Objects.equals(key.split("-")[0], String.valueOf(year))) {
                days += workedDays.get(key);
                monthCount++;
            }
        }

        yearTotal = ((days * 8) * hourlyPay) + (days * DAILY_FOOD_SUBSIDY);

        //Do an average if worked more than 6 months that year
        if (monthCount >= 6)
            return (yearTotal / monthCount) * 6;
        else
            return yearTotal;
    }

    public double calcYearPaycheck(int year) {
        int days = 0;

        if (workedDays.size() < 1 || year < 1)
            return 0;

        /* Fetch total worked days */
        for (String key : workedDays.keySet()) {
            if (Objects.equals(key.split("-")[0], String.valueOf(year))) {
                days += workedDays.get(key);
            }
        }
        return ((days * 8) * hourlyPay) + (days * DAILY_FOOD_SUBSIDY);
    }


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

    public void setEntranceDate(String entranceDate) throws ParseException {
        this.entranceDate = new SimpleDateFormat("yyyy-MM-dd").parse(entranceDate);
    }

    public HashMap<String, Integer> getWorkedDays() {
        return workedDays;
    }

    public void setWorkedDays(HashMap<String, Integer> workedDays) {
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