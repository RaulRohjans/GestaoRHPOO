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
