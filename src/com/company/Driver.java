package com.company;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Driver extends Employee{
    //Fields
    private HashMap<String, Double> distanceKms;
    private HashMap<String, Double> pricePerKm;

    //Constructors
    public Driver(int id, String name, Date entranceDate, double hourlyPay) {
        super(id, name, entranceDate, hourlyPay);
        setType(EmployeeType.DRIVER);
        this.distanceKms = new HashMap<String, Double>();
        this.pricePerKm = new HashMap<String, Double>();
    }

    public Driver() {
        super();
        setType(EmployeeType.DRIVER);
        this.distanceKms = new HashMap<String, Double>();
        this.pricePerKm = new HashMap<String, Double>();
    }

    //Methods
    public void addPricePerKm(int year, double price){
        if(price < 0){
            throw new IllegalArgumentException("Price cannot be negative!");
        }
        if (year < 1){
            throw new IllegalArgumentException("Year is invalid!");
        }
        pricePerKm.put(String.valueOf(year), price);
    }

    public void setKmMade(String date, double kms){
        if(date.isEmpty() || kms < 0){
            System.out.println("Set Kms input error. Check your parameters");
            return;
        }

        distanceKms.put(date, kms);
    }

    @Override
    public double calcPaycheck() {
        double distance = distanceKms.get(Calendar.getInstance().get(Calendar.YEAR) + "-" +
                Methods.getFormattedMonth());

        double price = pricePerKm.get(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));

        return super.calcPaycheck() + distance * price;
    }

    //Getters and Setters
    public HashMap<String, Double> getDistanceKms() {
        return distanceKms;
    }

    public void setDistanceKms(HashMap<String, Double> distanceKms) {
        this.distanceKms = distanceKms;
    }

    public HashMap<String, Double> getPricePerKm() {
        return pricePerKm;
    }

    public void setPricePerKm(HashMap<String, Double> pricePerKm) {
        this.pricePerKm = pricePerKm;
    }
}
