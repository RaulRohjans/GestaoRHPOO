package com.company;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Driver extends Employee{
    //Fields
    private double distanceKms;
    private Map<String, String> pricePerKm;

    //Constructors
    public Driver(int id, String name, Date entranceDate, double hourlyPay) {
        super(id, name, entranceDate, hourlyPay);
        setType(EmployeeType.DRIVER);
        this.distanceKms = 0;
        this.pricePerKm = new HashMap<String, String>();
    }

    public Driver() {
        super();
        setType(EmployeeType.DRIVER);
        this.distanceKms = 0;
        this.pricePerKm = new HashMap<String, String>();
    }

    //Methods
    public void addPricePerKm(int year, double price){
        if(price < 0){
            throw new IllegalArgumentException("Price cannot be negative!");
        }
        if (year < 1){
            throw new IllegalArgumentException("Year cannot be negative!");
        }
        pricePerKm.put(String.valueOf(year), String.valueOf(price));
    }

    //Getters and Setters
    public double getDistanceKms() {
        return distanceKms;
    }

    public void setDistanceKms(double distanceKms) {
        this.distanceKms = distanceKms;
    }

    public Map<String, String> getPricePerKm() {
        return pricePerKm;
    }

    public void setPricePerKm(Map<String, String> pricePerKm) {
        this.pricePerKm = pricePerKm;
    }
}
