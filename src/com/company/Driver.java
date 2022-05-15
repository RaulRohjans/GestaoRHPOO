package com.company;

import java.util.Date;

public class Driver extends Employee{
    //Fields
    private double distanceKms;
    private double pricePerKm;

    //Constructors
    public Driver(int id, String name, Date entranceDate, double hourlyPay, double pricePerKm) {
        super(id, name, entranceDate, hourlyPay);
        setType(EmployeeType.DRIVER);
        this.distanceKms = 0;
        this.pricePerKm = pricePerKm;
    }

    //Getters and Setters
    public double getDistanceKms() {
        return distanceKms;
    }

    public void setDistanceKms(double distanceKms) {
        this.distanceKms = distanceKms;
    }

    public double getPricePerKm() {
        return pricePerKm;
    }

    public void setPricePerKm(double pricePerKm) {
        this.pricePerKm = pricePerKm;
    }
}
