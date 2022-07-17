package com.company;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Employee {
    //Fields
    private int id;
    private String name;
    private Date entranceDate;
    private HashMap<String, Integer> workedDays;
    private double hourlyPay;

    public enum EmployeeType {NORMAL, MANAGER, DRIVER, SALESMAN, NULL}

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

    public double calcPaycheck() {
        if (workedDays.size() < 1)
            return 0;

        //Calculate Yearly Extra Percentage
        Duration diff = Duration.between(Calendar.getInstance().toInstant(), entranceDate.toInstant());
        int years = (int) (diff.toDays() / 365);
        double percentage = years * 0.5;

        int days = workedDays.get(Calendar.getInstance().get(Calendar.YEAR) + "-" +
                Methods.getFormattedMonth());

        return ((days * 8) * hourlyPay) + (days * DAILY_FOOD_SUBSIDY) + ((days * 8) * hourlyPay) * percentage;
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

        //Calculate Yearly Extra Percentage
        Duration diff = Duration.between(Calendar.getInstance().toInstant(), entranceDate.toInstant());
        int years = (int) (diff.toDays() / 365);
        double percentage = years * 0.5;

        yearTotal = ((days * 8) * hourlyPay) + (days * DAILY_FOOD_SUBSIDY) + ((days * 8) * hourlyPay) * percentage;

        //Do an average if worked more than 3 months that year
        if (monthCount >= 3)
            return (yearTotal / monthCount) * 3 + (yearTotal / monthCount) * 2/4;
        else
            return yearTotal + (yearTotal / monthCount) * 2;
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

        //Calculate Yearly Extra Percentage
        Duration diff = Duration.between(Calendar.getInstance().toInstant(), entranceDate.toInstant());
        int years = (int) (diff.toDays() / 365);
        double percentage = years * 0.5;

        yearTotal = ((days * 8) * hourlyPay) + (days * DAILY_FOOD_SUBSIDY) + ((days * 8) * hourlyPay) * percentage;

        //Do an average if worked more than 6 months that year
        if (monthCount >= 6)
            return (yearTotal / monthCount) * 6 + (yearTotal / monthCount);
        else
            return yearTotal + (yearTotal / monthCount) * 2;
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

        //Calculate Yearly Extra Percentage
        Duration diff = Duration.between(Calendar.getInstance().toInstant(), entranceDate.toInstant());
        int years = (int) (diff.toDays() / 365);
        double percentage = years * 0.5;

        return ((days * 8) * hourlyPay) + (days * DAILY_FOOD_SUBSIDY) + (((days * 8) * hourlyPay) / 12) * 2 + ((days * 8) * hourlyPay) * percentage;
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